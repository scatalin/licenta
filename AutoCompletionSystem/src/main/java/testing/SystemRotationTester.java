package testing;

import algorithms.SearchTree;
import algorithms.segmenttree.SegmentTree;
import input.DictionaryProcessor;
import input.FilesProcessor;
import model.dictionary.Dictionary;
import model.dictionary.Word;
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

        allReportFile = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.MEASURES_OUTPUT_FILE_NAME);
        if (!allReportFile.exists() && !allReportFile.isFile()) {
            System.out.println("all report file does not exist " + allReportFile + ";");
        }

        rotationTestDir = new File(Properties.TEST_ROTATION_DIRECTORY);
        if (!rotationTestDir.exists() && !rotationTestDir.isDirectory()) {
            System.out.println("test files directory does not exist: " + rotationTestDir + ";");
        }

        statisticsList = new ArrayList<List<Statistics>>(Properties.TEST_WORD_DEPTH+1);
        for (int currentRun = 0; currentRun <= Properties.TEST_WORD_DEPTH; currentRun++) {
            statisticsList.add(new ArrayList<Statistics>());
        }

        dictionaryProcessor = new DictionaryProcessor();
        filesProcessor = new FilesProcessor(dictionaryProcessor, Properties.TEST_ROTATION_DIRECTORY);
        numberOfRuns = filesProcessor.getNumberOfFiles();

        tree = new SegmentTree();
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

                    Statistics statistics = new Statistics(toTestFile.getName(), currentRun, dictionarySize);
                    statisticsList.get(currentRun).add(statistics);

                    BufferedReader reader = new BufferedReader(new FileReader(toTestFile));
                    String line = reader.readLine();

                    while (line != null) {
                        String[] words = line.split(WORD_SEPARATION_REGEX);
                        for (String word : words) {
                            word = word.toLowerCase();
                            if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                                statistics.beginWordStatistics(word);
                                tree.resetSearchK();
                                String prefix = word.substring(0, Math.min(currentRun, word.length()));
                                List<Word> completedWords = tree.getNextTopK(prefix);
                                fillStatistics(statistics, word, prefix.length(), completedWords);
                            }
                        }
                        //for words
                        line = reader.readLine();
                    }
                    //while line

                    statistics.makeAverages();
//                    printWriter.println(statistics.printStatistics(true));
//
                    reader.close();
                    System.out.println("file " + toTestFile.getName() + " was tested for threshold + " + currentRun);
                }
                // for threshold

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } // for all runs

        System.out.println("making averages");
        for (int currentRun = Properties.TEST_WORD_START; currentRun <= Properties.TEST_WORD_DEPTH; currentRun++) {
            List<Statistics> statistics = statisticsList.get(currentRun);
            Statistics averageStatistic = new Statistics("", currentRun, 0);
            Statistics minimumStatistic;
            Statistics maximumStatistic;
            long totalTotal = 0;
            long totalSuccessful = 0;
            long totalOutRange = 0;
            long totalNotInTree = 0;
            long totalNotFound = 0;
            double totalPrecision = 0.0;
            double totalRecall = 0.0;
            for(Statistics statistic : statistics){
                averageStatistic.setDictionarySize(statistic.getDictionarySize());
                totalTotal += statistic.getTotal();
                totalSuccessful += statistic.getSuccessful();
                totalNotFound += statistic.getNotFound();
                totalNotInTree += statistic.getNotInTree();
                totalOutRange += statistic.getOutRange();
                totalPrecision += statistic.getPrecision();
                totalRecall += statistic.getRecall();
            }
            averageStatistic.setNotFound(totalNotFound / statistics.size());
            averageStatistic.setNotInTree(totalNotInTree / statistics.size());
            averageStatistic.setOutRange(totalOutRange / statistics.size());
            averageStatistic.setPrecision(totalPrecision / statistics.size());
            averageStatistic.setSuccessful(totalSuccessful / statistics.size());
            averageStatistic.setTotal(totalTotal / statistics.size());
            averageStatistic.setRecall(totalRecall / statistics.size());
            printWriter.println(averageStatistic.printStatistics(false));
        }

        printWriter.flush();
        printWriter.close();

    }

    private void fillStatistics(Statistics statistics, String word, int prefixLength, List<Word> completedWords) {
        int found = -1;
        if (completedWords.isEmpty()) {
            statistics.interrogationStatistic(prefixLength, found - 1);
        }
        for (int i = 0; i < completedWords.size(); i++) {
            Word aWord = completedWords.get(i);
            if (aWord.getWord().equals(word)) {
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
        dictionaryProcessor.setDictionary(dictionary);

        filesProcessor.processInputFiles(false, run);

        tree.reset();
        tree.setK(Properties.AUTOCOMPLETION_K_SIZE);
        dictionary.sortDictionaryByWeight();
        tree.load(dictionary.getWords(), true);

        return dictionary.getNumberOfWords();
    }
}
