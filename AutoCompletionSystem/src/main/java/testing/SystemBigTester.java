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
public class SystemBigTester {

    private File reportFile1;
    private File reportFile2;

    private AutoCompletionSystem system;
    private AutoCompletionSystem newSystem;

    private FilesProcessor filesProcessor;

    private int numberOfFiles;

    public SystemBigTester() {

        initSystem();
    }

    private void initSystem() {
        reportFile1 = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.MEASURES_OUTPUT_FILE_NAME);
        if (!reportFile1.exists() && !reportFile1.isFile()) {
            System.out.println("all report file does not exist " + reportFile1 + ";");
        }

        reportFile2 = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.REPORT_OUTPUT_FILE_NAME);
        if (!reportFile2.exists() && !reportFile2.isFile()) {
            System.out.println("all report file does not exist " + reportFile2 + ";");
        }

        File rotationTestDir = new File(Properties.TEST_FILES_DIRECTORY);
        if (!rotationTestDir.exists() && !rotationTestDir.isDirectory()) {
            System.out.println("test files directory does not exist: " + rotationTestDir + ";");
        }

        DictionaryProcessor dictionaryProcessor = new DictionaryProcessor();
        filesProcessor = new FilesProcessor(dictionaryProcessor, Properties.TEST_FILES_DIRECTORY);
        numberOfFiles = filesProcessor.getNumberOfFiles();

        system = new AutoCompletionSystemImpl();
        newSystem = new AutoCompletionSystemImpl();
    }

    public void testSystem(Dictionary trainingDictionary) throws FileNotFoundException {

        String beforeMeasures = Properties.MEASURES_OUTPUT_FILE_NAME;
        String beforeReport = Properties.REPORT_OUTPUT_FILE_NAME;

        Dictionary referenceDictionary = trainingDictionary.clone();

        List<Statistics> overall = new ArrayList<>();
        List<Statistics> overallSecond = new ArrayList<>();
        List<Statistics> overallNew = new ArrayList<>();

        int size = 10;
        for (int i = 1; i <= size; i++) {

            int position = Properties.MEASURES_OUTPUT_FILE_NAME.lastIndexOf('-');
            Properties.MEASURES_OUTPUT_FILE_NAME = Properties.MEASURES_OUTPUT_FILE_NAME.substring(0, position + 1) + i;

            position = Properties.REPORT_OUTPUT_FILE_NAME.lastIndexOf('-');
            Properties.REPORT_OUTPUT_FILE_NAME = Properties.REPORT_OUTPUT_FILE_NAME.substring(0, position + 1) + i;

            position = Properties.TEST_FILES_DIRECTORY.lastIndexOf('-');
            Properties.TEST_FILES_DIRECTORY = Properties.TEST_FILES_DIRECTORY.substring(0, position + 1) + i;

            initSystem();

            system.loadCustomDictionary(referenceDictionary.clone());

            PrintWriter printWriter1 = new PrintWriter(reportFile1);
            PrintWriter printWriter2 = new PrintWriter(reportFile2);

            List<Dictionary> dictionaries = filesProcessor.createDictionariesFromFiles();

            //for all test files
            for (int testFileIndex = 0; testFileIndex < numberOfFiles; testFileIndex++) {

                newSystem.loadCustomDictionary(trainingDictionary.clone());

                int dictionarySize = trainingDictionary.getNumberOfWords();

                Dictionary testDictionary = dictionaries.get(testFileIndex);
                System.out.println("testing " + testDictionary.getFileName());

                Statistics statistics1 = new Statistics(testDictionary.getFileName(), 0, dictionarySize, 0);
                Statistics statistics2 = new Statistics(testDictionary.getFileName(), 0, dictionarySize, 0);
                Statistics statistics3 = new Statistics(testDictionary.getFileName(), 0, dictionarySize, 0);

                for (Word w : testDictionary.asList()) {
                    String word = w.getWord().toLowerCase();
                    if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                        statistics1.beginWordStatistics(word);
                        String prefix = word.substring(0, Math.min(Properties.AUTOCOMPLETION_THRESHOLD, word.length()));

                        List<String> completedWords = system.getWordCompletion(prefix);

                        fillStatistics(statistics1, word, w.getWeight(), prefix.length(), completedWords);

                        system.selectCompletionWord(word);

                        statistics2.beginWordStatistics(word);

                        prefix = word.substring(0, Math.min(Properties.AUTOCOMPLETION_THRESHOLD, word.length()));

                        completedWords = newSystem.getWordCompletion(prefix);

                        fillStatistics(statistics2, word, w.getWeight(), prefix.length(), completedWords);

                        newSystem.selectCompletionWord(word);
                    }
                }

                for (Word w : testDictionary.asList()) {
                    String word = w.getWord().toLowerCase();
                    if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                        statistics3.beginWordStatistics(word);
                        String prefix = word.substring(0, Math.min(Properties.AUTOCOMPLETION_THRESHOLD, word.length()));

                        List<String> completedWords = system.getWordCompletion(prefix);

                        fillStatistics(statistics3, word, w.getWeight(), prefix.length(), completedWords);

                        system.selectCompletionWord(word);
                    }
                }

                System.out.println("making average for file " + trainingDictionary.getFileName());
                statistics1.makeAverages();
                statistics2.makeAverages();
                statistics3.makeAverages();
                overall.add(statistics1);
                overallSecond.add(statistics3);
                overallNew.add(statistics2);
                printWriter1.println(statistics1.printStatistics(true));
                printWriter2.println(statistics2.printStatistics(true));
                printWriter1.flush();
                printWriter2.flush();
            }
            printWriter1.close();
            printWriter2.close();
        }
        Statistics average1 = makeAverages(overall, trainingDictionary.getNumberOfWords());
        Statistics average2 = makeAverages(overallNew, trainingDictionary.getNumberOfWords());
        Statistics average3 = makeAverages(overallSecond, trainingDictionary.getNumberOfWords());

        int position = Properties.MEASURES_OUTPUT_FILE_NAME.lastIndexOf('-');
        Properties.MEASURES_OUTPUT_FILE_NAME = Properties.MEASURES_OUTPUT_FILE_NAME.substring(0, position + 1) + (size + 1);

        position = Properties.REPORT_OUTPUT_FILE_NAME.lastIndexOf('-');
        Properties.REPORT_OUTPUT_FILE_NAME = Properties.REPORT_OUTPUT_FILE_NAME.substring(0, position + 1) + (size + 1);

        initSystem();

        PrintWriter printWriter1 = new PrintWriter(reportFile1);
        PrintWriter printWriter2 = new PrintWriter(reportFile2);
        printWriter1.println(average1.printStatistics(false));
        printWriter1.println(average3.printStatistics(false));
        printWriter2.println(average2.printStatistics(false));
        printWriter1.flush();
        printWriter2.flush();
        printWriter1.close();
        printWriter2.close();

        Properties.MEASURES_OUTPUT_FILE_NAME = beforeMeasures;
        Properties.REPORT_OUTPUT_FILE_NAME = beforeReport;
    }

    private int fillStatistics(Statistics statistics, String word, int weight, int prefixLength, List<String> completedWords) {
        if (completedWords.isEmpty()) {
            statistics.interrogationStatistic(prefixLength, -2, weight);
            return -2;
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
            statistics.interrogationStatistic(prefixLength, found + 1, weight);
            return found + 1;
        } else {
            statistics.interrogationStatistic(prefixLength, -1, weight);
            return found - 1;
        }
    }

    private Statistics makeAverages(List<Statistics> statistics, int dictSize) {
        Statistics averageStatistic = new Statistics(dictSize);
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
//            averageStatistic.addOutOfRange(statistic.getOutOfRange());
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
