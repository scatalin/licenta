package testing;

import algorithms.tst.TernarySearchTree;
import system.Properties;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 3/30/2015 .
 */
public class SystemTester {

    private static final String WORD_SEPARATION_REGEX = "[^a-zA-Z]";
    private final File testDir;

    private TernarySearchTree tst;

    private List<Statistics> statisticsList;

    public SystemTester(TernarySearchTree tst) {

        this.tst = tst;

        String inputDirectory = Properties.TEST_FILES_DIRECTORY;

        testDir = new File(inputDirectory);
        if (!testDir.exists() && !testDir.isDirectory()) {
            System.out.println("test files directory does not exist: " + testDir + ";");
        }

        statisticsList = new ArrayList<Statistics>();
    }

    public void testSystem() {
        File[] listFileNames = testDir.listFiles();

        if (listFileNames == null) {
            System.out.println("an exception occurred while reading files from " + testDir);
            return;
        }
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
                        if(word.length()>1) {
                            boolean done = false;
                            while (!done){
                                //interogate the sistem
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

        System.out.println("statistics are:");

        for(Statistics statistics : statisticsList){
            System.out.println(statistics);
        }
    }
}
