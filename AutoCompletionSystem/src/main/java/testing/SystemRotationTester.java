package testing;

import algorithms.SearchTree;
import algorithms.segmenttree.SegmentTreeLinear;
import dictionary.Dictionary;
import dictionary.Word;
import input.DictionaryProcessor;
import input.FilesProcessor;
import system.Properties;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 4/15/2015 .
 */
public class SystemRotationTester {

    private static final String WORD_SEPARATION_REGEX = "[^a-zA-Z]";
    private final File allReportFile;
    private final File rotationTestDir;

    private SearchTree tree;

    private List<List<Statistics>> statisticsList;

    private FilesProcessor filesProcessor;

    private int numberOfRuns;

    private DictionaryProcessor dictionaryProcessor;

    public SystemRotationTester() {

        allReportFile = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.MEASURES_OUTPUT_FILE_NAME);
        if (!allReportFile.exists() && !allReportFile.isFile()) {
            System.out.println("all report file does not exist " + allReportFile + ";");
        }

        rotationTestDir = new File(Properties.TEST_ROTATION_DIRECTORY);
        if (!rotationTestDir.exists() && !rotationTestDir.isDirectory()) {
            System.out.println("test files directory does not exist: " + rotationTestDir + ";");
        }

        statisticsList = new ArrayList<>(Properties.TEST_WORD_DEPTH + 1);
        for (int currentRun = 0; currentRun <= Properties.TEST_WORD_DEPTH; currentRun++) {
            statisticsList.add(new ArrayList<>());
        }

        dictionaryProcessor = new DictionaryProcessor();
        filesProcessor = new FilesProcessor(dictionaryProcessor, Properties.TEST_ROTATION_DIRECTORY);
        numberOfRuns = filesProcessor.getNumberOfFiles();

        tree = new SegmentTreeLinear();
    }

    public void testSystemByRotation() throws FileNotFoundException {

        PrintWriter printWriter = new PrintWriter(allReportFile);

        for (int run = 0; run < numberOfRuns; run++) {

            int dictionarySize = initTree(run);

            File[] listFileNames = rotationTestDir.listFiles();

            if (listFileNames == null) {
                System.out.println("an exception occurred while reading files from " + rotationTestDir);
                return;
            }


            File toTestFile = listFileNames[run];

            //todo read all input files and hand over to delegates, word processor and phrase processor

            System.out.println("testing " + toTestFile.getName());
            try {
                for (int currentRun = Properties.TEST_WORD_START; currentRun <= Properties.TEST_WORD_DEPTH; currentRun++) {
                    Dictionary dictionary = new Dictionary();
                    Statistics statistics = new Statistics(toTestFile.getName(), currentRun, dictionarySize, 0);
                    statisticsList.get(currentRun).add(statistics);

                    BufferedReader reader = new BufferedReader(new FileReader(toTestFile));
                    String line = reader.readLine();

                    while (line != null) {
                        String[] words = line.split(WORD_SEPARATION_REGEX);
                        for (String word : words) {
                            if (word.length() > 1) {
                                dictionary.addDictionaryWord(word);
                            }
                            word = word.toLowerCase();
                            if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                                statistics.beginWordStatistics(word);
                                tree.resetCompletion();
                                String prefix = word.substring(0, Math.min(currentRun, word.length()));
                                List<String> completedWords = tree.getSuggestions(prefix);
                                fillStatistics(statistics, word, prefix.length(), completedWords);
                            }
                        }
                        //for words
                        line = reader.readLine();
                    }
                    //while line

                    statistics.makeAverages();

                    System.out.println("dictionary of the file " + toTestFile.getName() + dictionary);
                    reader.close();
                    System.out.println("file " + toTestFile.getName() + " was tested for threshold + " + currentRun);
                }
                // for threshold

            } catch (IOException e) {
                e.printStackTrace();
            }

        } // for all runs

        makeAverages(printWriter);

        printWriter.flush();
        printWriter.close();
    }

    private void fillStatistics(Statistics statistics, String word, int prefixLength, List<String> completedWords) {
        int found = -1;
        if (completedWords.isEmpty()) {
            statistics.interrogationStatistic(prefixLength, found - 1);
        }
        for (int i = 0; i < completedWords.size(); i++) {
            String aWord = completedWords.get(i);
            if (aWord.equals(word)) {
                found = i;
                break;
            }
        }
        if (found != -1) {
            statistics.interrogationStatistic(prefixLength, found + 1);
        } else {
            statistics.interrogationStatistic(prefixLength, found);
        }
    }

    private int initTree(int run) {
        Dictionary dictionary = new Dictionary();
        dictionaryProcessor = new DictionaryProcessor(dictionary);

        filesProcessor.processInputFiles(run);

        tree.reset();
        tree.setNumberOfSuggestions(Properties.AUTOCOMPLETION_SUGGESTION_SIZE);
        tree.load(dictionary.getWordsSortedByWeight(), true);

        return dictionary.getNumberOfWords();
    }

    public void testSystemByRotationInMemory() throws FileNotFoundException {

        PrintWriter printWriter = new PrintWriter(allReportFile);

        List<Dictionary> dictionaries = filesProcessor.createDictionariesFromFiles();
        for (Dictionary dictionary : dictionaries) {

            System.out.println("dictionary for the file" + dictionary.getFileName() + " " + dictionary);
        }

        for (int run = 0; run < numberOfRuns; run++) {

            Dictionary toTestDictionary = dictionaries.get(run);

            int dictionarySize = initTreeInMemory(toTestDictionary);

            //todo read all input files and hand over to delegates, word processor and phrase processor

            System.out.println("testing " + toTestDictionary.getFileName());

            for (int currentRun = Properties.TEST_WORD_START; currentRun <= Properties.TEST_WORD_DEPTH; currentRun++) {

                Statistics statistics = new Statistics(toTestDictionary.getFileName(), currentRun, dictionarySize, 0);
                statisticsList.get(currentRun).add(statistics);

                for (Word w : toTestDictionary.getWords()) {
                    String word = w.getWord().toLowerCase();
                    if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                        statistics.beginWordStatistics(word);
                        tree.resetCompletion();
                        String prefix = word.substring(0, Math.min(currentRun, word.length()));
                        List<String> completedWords = tree.getSuggestions(prefix);
                        fillStatistics(statistics, word, prefix.length(), completedWords);
                    }
                }

                statistics.makeAverages();

                System.out.println("file " + toTestDictionary.getFileName() + " was tested for threshold + " + currentRun);
            }
            // for threshold
        } // for all runs

        makeAverages(printWriter);
        printWriter.flush();
        printWriter.close();

    }

    private int initTreeInMemory(Dictionary dictionary) {
        tree.reset();
        tree.setNumberOfSuggestions(Properties.AUTOCOMPLETION_SUGGESTION_SIZE);
        tree.load(dictionary.getWordsSortedByWeight(), true);
        return dictionary.getNumberOfWords();
    }

    private void makeAverages(PrintWriter printWriter) {
        System.out.println("making averages");
        for (int currentRun = Properties.TEST_WORD_START; currentRun <= Properties.TEST_WORD_DEPTH; currentRun++) {
            List<Statistics> statistics = statisticsList.get(currentRun);
            Statistics averageStatistic = new Statistics("", currentRun, 0, 0);
            long totalTotal = 0;
            long totalSuccessful = 0;
            long totalOutRange = 0;
            long totalNotInTree = 0;
            long totalNotFound = 0;
            long totalDictionarySize = 0;
            double totalPrecision = 0.0;
            double totalRecall = 0.0;
            for (Statistics statistic : statistics) {
                totalDictionarySize += statistic.getDictionarySize();
                totalTotal += statistic.getTotal();
                totalSuccessful += statistic.getSuccessful();
                totalNotFound += statistic.getWordNotInTree();
                totalNotInTree += statistic.getNoSuggestionsForPrefix();
                totalOutRange += statistic.getOutRange();
                totalPrecision += statistic.getPrecision();
                totalRecall += statistic.getRecall();
            }
            averageStatistic.setTotal(totalTotal / statistics.size());
            averageStatistic.setDictionarySize(totalDictionarySize - averageStatistic.getTotal());
            averageStatistic.setWordNotInTree(totalNotFound / statistics.size());
            averageStatistic.setNoSuggestionsForPrefix(totalNotInTree / statistics.size());
            averageStatistic.setOutRange(totalOutRange / statistics.size());
            averageStatistic.setPrecision(totalPrecision / statistics.size());
            averageStatistic.setSuccessful(totalSuccessful / statistics.size());
            averageStatistic.setRecall(totalRecall / statistics.size());
            printWriter.println(averageStatistic.printStatistics(false));
        }
    }
}
