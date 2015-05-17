package testing;

import algorithms.SearchTree;
import algorithms.segmenttree.SegmentTreeLinear;
import dictionary.Dictionary;
import dictionary.Word;
import input.DictionaryProcessor;
import input.FilesProcessor;
import system.Properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 4/15/2015 .
 */
public class SystemTenFoldingTesting {

    private final File allReportFile;

    private SearchTree tree;

    private FilesProcessor filesProcessor;

    private int numberOfFiles;

    public SystemTenFoldingTesting() {

        allReportFile = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.MEASURES_OUTPUT_FILE_NAME);
        if (!allReportFile.exists() && !allReportFile.isFile()) {
            System.out.println("all report file does not exist " + allReportFile + ";");
        }

        File rotationTestDir = new File(Properties.TEST_ROTATION_DIRECTORY);
        if (!rotationTestDir.exists() && !rotationTestDir.isDirectory()) {
            System.out.println("test files directory does not exist: " + rotationTestDir + ";");
        }

        DictionaryProcessor dictionaryProcessor = new DictionaryProcessor();
        filesProcessor = new FilesProcessor(dictionaryProcessor, Properties.TEST_ROTATION_DIRECTORY);
        numberOfFiles = filesProcessor.getNumberOfFiles();

        tree = new SegmentTreeLinear();
    }

    public void testSystem() throws FileNotFoundException {

        PrintWriter printWriter = new PrintWriter(allReportFile);

        List<Dictionary> dictionaries = filesProcessor.createDictionariesFromFiles();
        for (Dictionary dictionary : dictionaries) {

            System.out.println("dictionary for the file" + dictionary.getFileName() + " " + dictionary);
        }

        //for all sizes of training domain
        for (int noTrainingFiles = 1; noTrainingFiles < numberOfFiles; noTrainingFiles++) {

            List<List<Statistics>> trainingStatistics = new ArrayList<>();
            for (int i = 0; i <= Properties.TEST_WORD_DEPTH; i++) {
                trainingStatistics.add(new ArrayList<>());
            }

            //todo read all input files and hand over to delegates, word processor and phrase processor

            //for all test files
            for (int testFileIndex = 0; testFileIndex < numberOfFiles; testFileIndex++) {

                //build train dictionary
                Dictionary trainingDictionary = new Dictionary();

                //iterate all files
                int count = 0;
                int index = -1;
                while (count != noTrainingFiles) {
                    index++;
                    //skip the test file
                    if (index == testFileIndex) {
                        continue;
                    }
                    //concatenate the rest of the dictionaries
                    Dictionary addDictionary = dictionaries.get(index);
                    for (Word word : addDictionary.getWords()) {
                        trainingDictionary.addDictionaryWord(word);
                    }

                    count++;
                }

                //construct the tree with the training Dictionary

                tree.reset();
                tree.setNumberOfSuggestions(Properties.AUTOCOMPLETION_SUGGESTION_SIZE);
                tree.load(trainingDictionary.getWordsSortedByWeight(), true);
                int dictionarySize = trainingDictionary.getNumberOfWords();

                Dictionary testDictionary = dictionaries.get(testFileIndex);
                System.out.println("testing " + testDictionary.getFileName() + " for training size " + noTrainingFiles);


                for (int currentRun = Properties.TEST_WORD_START; currentRun <= Properties.TEST_WORD_DEPTH; currentRun++) {

                    Statistics statistics = new Statistics(testDictionary.getFileName(), currentRun, dictionarySize, noTrainingFiles);

                    for (Word w : testDictionary.getWords()) {
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
                    trainingStatistics.get(currentRun).add(statistics);

                }
            }
            for (int currentRun = Properties.TEST_WORD_START; currentRun <= Properties.TEST_WORD_DEPTH; currentRun++) {
                System.out.println("making average for training size " + noTrainingFiles + " and threshold " + currentRun);
                Statistics averageStatistic = makeAverages(trainingStatistics.get(currentRun), currentRun, noTrainingFiles);
                printWriter.println(averageStatistic.printStatistics(false));
                printWriter.flush();

            }
        }
    }

    private void fillStatistics(Statistics statistics, String word, int prefixLength, List<String> completedWords) {
        if (completedWords.isEmpty()) {
            statistics.interrogationStatistic(prefixLength, -2);
            return;
        }
        int found = -1;
        for (int i = 0; i < completedWords.size(); i++) {
            String aWord = completedWords.get(i);
            if (aWord.equals(word)) {
                found = i;
                break;
            }
        }
        if (found != -1) {
            statistics.interrogationStatistic(prefixLength, found + 1);
//            System.out.println("found prefix " + prefix + ":" + word + trainingDictionary.getWordsWithPrefix(prefix));
        } else {
            statistics.interrogationStatistic(prefixLength, -1);
//            System.out.println("not found sugestions for prefix " + prefix + ":" + word + trainingDictionary.getWordsWithPrefix(prefix));
        }
    }

    private Statistics makeAverages(List<Statistics> statistics, int currentRun, int noTrainingFiles) {
        Statistics averageStatistic = new Statistics("", currentRun, noTrainingFiles);
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
        averageStatistic.setDictionarySize(totalDictionarySize / statistics.size());
        averageStatistic.setWordNotInTree(totalNotFound / statistics.size());
        averageStatistic.setNoSuggestionsForPrefix(totalNotInTree / statistics.size());
        averageStatistic.setOutRange(totalOutRange / statistics.size());
        averageStatistic.setPrecision(totalPrecision / statistics.size());
        averageStatistic.setSuccessful(totalSuccessful / statistics.size());
        averageStatistic.setRecall(totalRecall / statistics.size());

        return averageStatistic;
    }
}
