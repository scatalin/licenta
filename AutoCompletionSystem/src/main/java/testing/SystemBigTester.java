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
import java.util.List;

/**
 * Created by Catalin on 4/15/2015 .
 */
public class SystemBigTester {

    private final File reportFile1;
    private final File reportFile2;


    private AutoCompletionSystem system;
    private AutoCompletionSystem newSystem;

    private FilesProcessor filesProcessor;

    private int numberOfFiles;

    public SystemBigTester() {

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

        system.loadCustomDictionary(trainingDictionary);
        system.constructSearchTree();

        PrintWriter printWriter1 = new PrintWriter(reportFile1);
        PrintWriter printWriter2 = new PrintWriter(reportFile2);

        List<Dictionary> dictionaries = filesProcessor.createDictionariesFromFiles();

        //for all test files
        for (int testFileIndex = 0; testFileIndex < numberOfFiles; testFileIndex++) {

            newSystem.loadCustomDictionary(trainingDictionary);
            newSystem.constructSearchTree();

            int dictionarySize = trainingDictionary.getNumberOfWords();

            Dictionary testDictionary = dictionaries.get(testFileIndex);
            System.out.println("testing " + testDictionary.getFileName());

            Statistics statistics1 = new Statistics(testDictionary.getFileName(), 0, dictionarySize, 0);
            Statistics statistics2 = new Statistics(testDictionary.getFileName(), 0, dictionarySize, 0);

            for (Word w : testDictionary.asList()) {
                String word = w.getWord().toLowerCase();
                if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                    statistics1.beginWordStatistics(word);
                    system.startCompletion();
                    String prefix = word.substring(0, Math.min(Properties.AUTOCOMPLETION_THRESHOLD, word.length()));

                    List<String> completedWords = system.getCompletion(prefix);

                    fillStatistics(statistics1, word, w.getWeight(), prefix.length(), completedWords);

                    system.selectWord(word);

                    statistics2.beginWordStatistics(word);

                    newSystem.startCompletion();
                    prefix = word.substring(0, Math.min(Properties.AUTOCOMPLETION_THRESHOLD, word.length()));

                    completedWords = newSystem.getCompletion(prefix);

                    fillStatistics(statistics2, word, w.getWeight(), prefix.length(), completedWords);

                    newSystem.selectWord(word);
                }
            }

            System.out.println("making average for file " + trainingDictionary.getFileName());
            statistics1.makeAverages();
            statistics2.makeAverages();
            printWriter1.println(statistics1.printStatistics(false));
            printWriter2.println(statistics2.printStatistics(false));
            printWriter1.flush();
            printWriter2.flush();
        }
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

}
