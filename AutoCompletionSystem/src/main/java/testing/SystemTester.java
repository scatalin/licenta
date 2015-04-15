package testing;

import algorithms.SearchTree;
import model.dictionary.Word;
import system.Properties;
import utils.FileManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/30/2015 .
 */
public class SystemTester {

    private final File reportFile;
    private final File allReportFile;

    private static final String WORD_SEPARATION_REGEX = "[^a-zA-Z]";
    private final File testDir;
    private final String processedDirectory;

    private SearchTree segmentTree;

    private List<Statistics> statisticsList;

    private final FileManager manager;

    public SystemTester(SearchTree segmentTree) {

        this.segmentTree = segmentTree;

        reportFile = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.REPORT_OUTPUT_FILE_NAME);
        if (!reportFile.exists() && !reportFile.isFile()) {
            System.out.println("report file does not exist " + reportFile + ";");
        }

        allReportFile = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.MEASURES_OUTPUT_FILE_NAME);
        if (!allReportFile.exists() && !allReportFile.isFile()) {
            System.out.println("all report file does not exist " + allReportFile + ";");
        }

        processedDirectory = Properties.PROCESSED_FILES_DIRECTORY;

        File processedDir = new File(processedDirectory);
        if (!processedDir.exists() && !processedDir.isDirectory()) {
            System.out.println("processed files directory does not exist: " + processedDir + ";");
        }

        String inputDirectory = Properties.TEST_FILES_DIRECTORY;

        testDir = new File(inputDirectory);
        if (!testDir.exists() && !testDir.isDirectory()) {
            System.out.println("test files directory does not exist: " + testDir + ";");
        }

        statisticsList = new ArrayList<Statistics>();

        manager = new FileManager();
    }

    public void testSystem(boolean move) throws FileNotFoundException {
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
                    Statistics statistics = new Statistics(file.getName(), currentRun, 0);
                    statisticsList.add(statistics);

                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = reader.readLine();

                    while (line != null) {
                        String[] words = line.split(WORD_SEPARATION_REGEX);
                        for (String word : words) {
                            word = word.toLowerCase();
                            if (word.length() >= Properties.AUTOCOMPLETION_THRESHOLD) {
                                statistics.beginWordStatistics(word);
                                segmentTree.resetSearchK();
                                String prefix = word.substring(0, Math.min(currentRun, word.length()));
                                List<Word> completedWords = segmentTree.getNextTopK(prefix);
                                int found = -1;
                                if (completedWords.isEmpty()) {
                                    statistics.interrogationStatistic(prefix.length(), found - 1);
                                }
                                for (int i = 0; i < completedWords.size(); i++) {
                                    Word aWord = completedWords.get(i);
                                    if (aWord.getWord().equals(word)) {
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

                if(move){
                    manager.moveFile(file, processedDirectory);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        printWriter.flush();
        printWriter.close();
    }
}
