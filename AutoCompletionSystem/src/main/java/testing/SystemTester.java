package testing;

import algorithms.SearchTree;
import model.dictionary.Word;
import system.Properties;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/30/2015 .
 */
public class SystemTester {

    private final File reportFile;

    private static final String WORD_SEPARATION_REGEX = "[^a-zA-Z]";
    private final File testDir;

    private SearchTree segmentTree;

    private List<Statistics> statisticsList;

    public SystemTester(SearchTree segmentTree) {

        this.segmentTree = segmentTree;

        reportFile = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.REPORT_OUTPUT_FILE_NAME);
        if (!reportFile.exists() && !reportFile.isFile()) {
            System.out.println("dictionary file does not exist " + reportFile + ";");
        }

        String inputDirectory = Properties.TEST_FILES_DIRECTORY;

        testDir = new File(inputDirectory);
        if (!testDir.exists() && !testDir.isDirectory()) {
            System.out.println("test files directory does not exist: " + testDir + ";");
        }

        statisticsList = new ArrayList<Statistics>();
    }

    public void testSystem() throws FileNotFoundException {
        File[] listFileNames = testDir.listFiles();

        if (listFileNames == null) {
            System.out.println("an exception occurred while reading files from " + testDir);
            return;
        }

        PrintWriter printWriter = new PrintWriter(reportFile);

        //todo read all input files and hand over to delegates, word processor and phrase processor
        for (File file : listFileNames) {
            System.out.println("processing file " + file.getName());
            try {
                Statistics statistics = new Statistics(file.getName());
                statisticsList.add(statistics);

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();

                while (line != null) {
                    String[] words = line.split(WORD_SEPARATION_REGEX);
                    for (String word : words) {
                        word = word.toLowerCase();
                        statistics.beginWordStatistics(word);
                        if (word.length() > Properties.AUTOCOMPLETION_THRESHOLD) {
                            boolean done = false;
                            int index = 1;
                            segmentTree.resetSearchK();
                            while (!done) {
                                if (index >= word.length()) {
                                    break;
                                }
                                String prefix = word.substring(0, index);
                                List<Word> completedWords = segmentTree.getNextTopK(prefix);
                                if (completedWords.isEmpty()) {
                                    printWriter.println("prefix " + prefix + " from word " + word + " generated no output");
                                }
                                int found = -1;
                                for (int i = 0; i < completedWords.size(); i++) {
                                    Word aWord = completedWords.get(i);
                                    if (aWord.getWord().equals(word)) {
                                        found = i;
                                        break;
                                    }
                                }
                                if (found != -1) {
                                    statistics.interrogationStatistic(prefix.length(), found + 1);
                                }
                                if (found == 0) {
                                    done = true;
                                }
                                index++;
                            }
                        }
                    }
                    line = reader.readLine();
                }

                reader.close();
                System.out.println("file " + file.getName() + " was tested");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        printWriter.println("statistics are:");

        for (Statistics statistics : statisticsList) {
            printWriter.println(statistics);
        }


        printWriter.flush();
        printWriter.close();
    }

    public void testSystemWithPercentages() throws FileNotFoundException {
        File[] listFileNames = testDir.listFiles();

        if (listFileNames == null) {
            System.out.println("an exception occurred while reading files from " + testDir);
            return;
        }

        PrintWriter printWriter = new PrintWriter(reportFile);

        //todo read all input files and hand over to delegates, word processor and phrase processor
        for (File file : listFileNames) {
            System.out.println("processing file " + file.getName());
            try {
                Statistics statistics = new Statistics(file.getName());
                statisticsList.add(statistics);

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();

                while (line != null) {
                    String[] words = line.split(WORD_SEPARATION_REGEX);
                    for (String word : words) {
                        word = word.toLowerCase();
                        statistics.beginWordStatistics(word);
                        if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                            int index = Properties.AUTOCOMPLETION_THRESHOLD;
                            segmentTree.resetSearchK();
                            String prefix = word.substring(0, index);
                            List<Word> completedWords = segmentTree.getNextTopK(prefix);
                            if (completedWords.isEmpty()) {
                                printWriter.println("prefix " + prefix + " from word " + word + " generated no output");
                            }
                            int found = -1;
                            for (int i = 0; i < completedWords.size(); i++) {
                                Word aWord = completedWords.get(i);
                                if (aWord.getWord().equals(word)) {
                                    found = i;
                                    break;
                                }
                            }
                            if (found != -1) {
                                statistics.interrogationStatistic(prefix.length(), found + 1);
                            }
                            else {
                                statistics.interrogationStatistic(prefix.length(), found);
                            }
                        }
                    }
                    line = reader.readLine();
                }

                reader.close();
                System.out.println("file " + file.getName() + " was tested");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        printWriter.println("statistics are:");

        for (
                Statistics statistics
                : statisticsList)

        {
            printWriter.println(statistics);
        }


        printWriter.flush();
        printWriter.close();
    }
}
