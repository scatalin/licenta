package testing;

import dictionary.Dictionary;
import dictionary.Word;
import input.DictionaryProcessor;
import input.FilesProcessor;
import system.AutoCompletionSystem;
import system.AutoCompletionSystemImpl;
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

    private File reportFile1;
    private File reportFile2;

    private AutoCompletionSystem system;
    private AutoCompletionSystem systemNew;

    private FilesProcessor filesProcessor;

    private int numberOfFiles;
    private int dictionarySize;
    private int noTrainingFiles;

    public SystemTenFoldingTesting() {
        createReportFiles();

        File rotationTestDir = new File(Properties.TEST_ROTATION_DIRECTORY);
        if (!rotationTestDir.exists() && !rotationTestDir.isDirectory()) {
            System.out.println("test files directory does not exist: " + rotationTestDir + ";");
        }

        DictionaryProcessor dictionaryProcessor = new DictionaryProcessor();
        filesProcessor = new FilesProcessor(dictionaryProcessor, Properties.TEST_ROTATION_DIRECTORY);
        numberOfFiles = filesProcessor.getNumberOfFiles();

        system = new AutoCompletionSystemImpl();
        systemNew = new AutoCompletionSystemImpl();
    }

    private void createReportFiles() {
        reportFile1 = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.MEASURES_OUTPUT_FILE_NAME);
        if (!reportFile1.exists() && !reportFile1.isFile()) {
            System.out.println("all report file does not exist " + reportFile1 + ";");
        }

        reportFile2 = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.REPORT_OUTPUT_FILE_NAME);
        if (!reportFile2.exists() && !reportFile2.isFile()) {
            System.out.println("all report file does not exist " + reportFile2 + ";");
        }
    }

    public void testSystem(Dictionary dictionary, boolean loadDefault) throws FileNotFoundException {


        List<Dictionary> dictionaries = filesProcessor.createDictionariesFromFiles();

        //for all sizes of training domain
        for (noTrainingFiles = 0; noTrainingFiles < numberOfFiles; noTrainingFiles++) {

            int position = Properties.MEASURES_OUTPUT_FILE_NAME.lastIndexOf('-');
            Properties.MEASURES_OUTPUT_FILE_NAME = Properties.MEASURES_OUTPUT_FILE_NAME.substring(0, position + 1) + noTrainingFiles;

            position = Properties.REPORT_OUTPUT_FILE_NAME.lastIndexOf('-');
            Properties.REPORT_OUTPUT_FILE_NAME = Properties.REPORT_OUTPUT_FILE_NAME.substring(0, position + 1) + noTrainingFiles;

            createReportFiles();

            PrintWriter printWriter1 = new PrintWriter(reportFile1);
            PrintWriter printWriter2 = new PrintWriter(reportFile2);

            List<Statistics> stat0 = new ArrayList<>();
            List<Statistics> stat1 = new ArrayList<>();
            List<Statistics> stat2 = new ArrayList<>();
            List<Statistics> stat3 = new ArrayList<>();

            List<Statistics> statNew0 = new ArrayList<>();
            List<Statistics> statNew1 = new ArrayList<>();
            List<Statistics> statNew2 = new ArrayList<>();
            List<Statistics> statNew3 = new ArrayList<>();

            //todo read all input files and hand over to delegates, word processor and phrase processor

            //for all test files
            for (int testFileIndex = 0; testFileIndex < numberOfFiles; testFileIndex++) {

                //build train dictionary
                Dictionary trainingDictionary = buildTrainingDictionary(dictionaries, noTrainingFiles, testFileIndex);
                if (loadDefault) {
                    for (Word word : dictionary.asList()) {
                        trainingDictionary.addDefaultWord(word);
                    }
                }

                //construct the tree with the training Dictionary
                system.loadCustomDictionary(trainingDictionary);
                system.constructSearchTree();

                //construct the tree with the training Dictionary
                systemNew.loadCustomDictionary(trainingDictionary);
                systemNew.constructSearchTree();

                dictionarySize = trainingDictionary.getNumberOfWords();

                Dictionary testDictionary = dictionaries.get(testFileIndex);
                System.out.println("testing " + testDictionary.getFileName() + " for training size " + noTrainingFiles);

                ////////////////////////////////////////////////// 1
                Statistics statistics = testSystemWithDictionary(testDictionary, false, system);

                stat0.add(statistics);
//                printWriter1.println("run0");
//                printWriter1.println(statistics.printStatistics(true));
//                printWriter1.flush();

                statistics = testSystemWithDictionary(testDictionary, false, systemNew);

                statNew0.add(statistics);
//                printWriter2.println("run0");
//                printWriter2.println(statistics.printStatistics(true));
//                printWriter2.flush();

                ////////////////////////////////////////////////// 1
                statistics = testSystemWithDictionary(testDictionary, true, system);

                stat1.add(statistics);
//                printWriter1.println("run1");
//                printWriter1.println(statistics.printStatistics(true));
//                printWriter1.flush();

                statistics = testSystemWithDictionary(testDictionary, true, systemNew);

                statNew1.add(statistics);
//                printWriter2.println("run1");
//                printWriter2.println(statistics.printStatistics(true));
//                printWriter2.flush();

                ////////////////////////////////////////////////// 2
                statistics = testSystemWithDictionary(testDictionary, true, system);

                stat2.add(statistics);
//                printWriter1.println("run2");
//                printWriter1.println(statistics.printStatistics(true));
//                printWriter1.flush();

                statistics = testSystemWithDictionary(testDictionary, false, systemNew);

                statNew2.add(statistics);
//                printWriter2.println("run2");
//                printWriter2.println(statistics.printStatistics(true));
//                printWriter2.flush();

                ////////////////////////////////////////////////// 3
                statistics = testSystemWithDictionary(testDictionary, false, system);

                stat3.add(statistics);
//                printWriter1.println("run3");
//                printWriter1.println(statistics.printStatistics(true));
//                printWriter1.flush();

                statistics = testSystemWithDictionary(testDictionary, false, systemNew);

                statNew3.add(statistics);
//                printWriter2.println("run3");
//                printWriter2.println(statistics.printStatistics(true));
//                printWriter2.flush();

            }

            printAverageForList(printWriter1, stat0, "0");

            printAverageForList(printWriter1, stat1, "1");

            printAverageForList(printWriter1, stat2, "2");

            printAverageForList(printWriter1, stat3, "3");

            printAverageForList(printWriter2, statNew0, "0");

            printAverageForList(printWriter2, statNew1, "1");

            printAverageForList(printWriter2, statNew2, "2");

            printAverageForList(printWriter2, statNew3, "3");

            printWriter1.close();
            printWriter2.close();
        }
    }

    private Dictionary buildTrainingDictionary(List<Dictionary> dictionaries, int noTrainingFiles, int testFile) {
        Dictionary trainingDictionary = new Dictionary();

        //iterate all files
        int count = 0;
        int index = -1;
        while (count != noTrainingFiles) {
            index++;
            //skip the test file
            if (index == testFile) {
                continue;
            }
            //concatenate the rest of the dictionaries
            Dictionary addDictionary = dictionaries.get(index);
            for (Word word : addDictionary.asList()) {
                trainingDictionary.addDefaultWord(word);
            }

            count++;
        }
        return trainingDictionary;
    }

    private Statistics testSystemWithDictionary(Dictionary testDictionary, boolean learn, AutoCompletionSystem system) {
        Statistics statistics = new Statistics(testDictionary.getFileName(), Properties.AUTOCOMPLETION_THRESHOLD, dictionarySize, noTrainingFiles);
        for (Word w : testDictionary.asList()) {
            String word = w.getWord().toLowerCase();
            if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                statistics.beginWordStatistics(word);
                system.startCompletion();
                String prefix = word.substring(0, Properties.AUTOCOMPLETION_THRESHOLD);

                List<String> completedWords = system.getCompletion(prefix);
                fillStatistics(statistics, word, prefix.length(), completedWords);

                if (learn) {
                    system.selectWord(word);
                }
            }
        }
        statistics.makeAverages();
        return statistics;
    }

    private void printAverageForList(PrintWriter printWriter, List<Statistics> stat, String run) {
        printWriter.println("run " + run);
        System.out.println("making average for training size " + noTrainingFiles);
        Statistics averageStatistic = makeAverages(stat, 0, noTrainingFiles);
        printWriter.println(averageStatistic.printStatistics(false));
        printWriter.flush();
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
        int size = statistics.size();
        long totalTotal = 0;
        long totalSuccessful = 0;
        long totalOutRange = 0;
        long totalNotInTree = 0;
        long totalNotFound = 0;
        long totalDictionarySize = 0;
        double totalPrecision = 0.0;
        double totalRecall = 0.0;
        for (Statistics stat : statistics) {
            totalDictionarySize += stat.getDictionarySize();
            totalTotal += stat.getTotal();
            totalSuccessful += stat.getSuccessful();
            totalNotFound += stat.getWordNotInTree();
            totalNotInTree += stat.getNoSuggestionsForPrefix();
            totalOutRange += stat.getOutRange();
            totalPrecision += stat.getPrecision();
            totalRecall += stat.getRecall();
        }
        averageStatistic.setTotal(totalTotal / size);
        averageStatistic.setDictionarySize(totalDictionarySize / size);
        averageStatistic.setWordNotInTree(totalNotFound / size);
        averageStatistic.setNoSuggestionsForPrefix(totalNotInTree / size);
        averageStatistic.setOutRange(totalOutRange / size);
        averageStatistic.setPrecision(totalPrecision / size);
        averageStatistic.setSuccessful(totalSuccessful / size);
        averageStatistic.setRecall(totalRecall / size);

        return averageStatistic;
    }
}
