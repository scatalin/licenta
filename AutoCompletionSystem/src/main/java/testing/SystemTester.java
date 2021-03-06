package testing;

import algorithms.SearchTree;
import system.Properties;

import java.io.*;
import java.util.List;

/**
 * Created by Catalin on 3/30/2015 .
 */
public class SystemTester {

    private final File allReportFile;
    private final File testDir;
    private SearchTree segmentTree;

    public SystemTester(SearchTree segmentTree) {

        this.segmentTree = segmentTree;

        File reportFile = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.REPORT_OUTPUT_FILE_NAME);
        if (!reportFile.exists() && !reportFile.isFile()) {
            System.out.println("report file does not exist " + reportFile + ";");
        }

        allReportFile = new File(Properties.RESULT_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.MEASURES_OUTPUT_FILE_NAME);
        if (!allReportFile.exists() && !allReportFile.isFile()) {
            System.out.println("all report file does not exist " + allReportFile + ";");
        }

        String inputDirectory = Properties.TEST_FILES_DIRECTORY;

        testDir = new File(inputDirectory);
        if (!testDir.exists() && !testDir.isDirectory()) {
            System.out.println("test files directory does not exist: " + testDir + ";");
        }
    }

    public void testSystem() throws FileNotFoundException {
        File[] listFileNames = testDir.listFiles();

        if (listFileNames == null) {
            System.out.println("an exception occurred while reading files from " + testDir);
            return;
        }

        PrintWriter printWriter = new PrintWriter(allReportFile);

        //todo read all input files and hand over to delegates, word processor and phrase processor
        for (File file : listFileNames) {
            System.out.println("processing file " + file.getName());
            try {
                for (int currentRun = Properties.TEST_WORD_START; currentRun <= Properties.TEST_WORD_DEPTH; currentRun++) {

                    //todo insert here dictionary size
                    Statistics statistics = new Statistics(file.getName(), currentRun, 0, 0);

                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = reader.readLine();

                    while (line != null) {
                        String[] words = line.split(Properties.WORD_SEPARATION_REGEX);
                        for (String word : words) {
                            word = word.toLowerCase();
                            if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                                statistics.beginWordStatistics(word);
                                segmentTree.resetCompletion();
                                String prefix = word.substring(0, Math.min(currentRun, word.length()));
                                List<String> completedWords = segmentTree.getSuggestions(prefix);
                                int found = -1;
                                if (completedWords.isEmpty()) {
                                    statistics.interrogationStatistic(prefix.length(), found - 1);
                                }
                                for (int i = 0; i < completedWords.size(); i++) {
                                    String aWord = completedWords.get(i);
                                    if (aWord.equals(word)) {
                                        found = i;
                                        break;
                                    }
                                }
                                if (found != -1) {
                                    statistics.interrogationStatistic(prefix.length(), found + 1);
                                } else {
                                    statistics.interrogationStatistic(prefix.length(), found);
                                }
                            }
                        }
                        //for words
                        line = reader.readLine();
                    }
                    //while line

                    statistics.makeAverages();
                    printWriter.println(statistics.printStatistics(true));

                    reader.close();
                    System.out.println("file " + file.getName() + " was tested");
                }
                // for threshold
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        printWriter.flush();
        printWriter.close();
    }
}
