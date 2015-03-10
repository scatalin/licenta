import algorithms.tst.TernarySearchTree;
import algorithms.tst.extras.FilePrinter;
import algorithms.tst.extras.TernarySearchTreeFactory;
import input.FileProperties;
import input.InputFilesProcessor;
import input.Properties;
import model.dictionary.Dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Main {

    private static String RESET = "reset";
    private static String IMPORT = "import";
    private static String DISPLAY = "display d";
    private static String BUILD_TST = "tst b";
    private static String RANDOM_BUILD_TST = "tst rb";
    private static String PRINT_BUILD_TST = "tst p";
    private static String TEST = "test";

    public static void main(String[] args) {
        try {
            propertiesFileRead();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Main main = new Main();
        main.start();
    }

    private static void propertiesFileRead() throws IOException {
        System.out.println("Hello world");
//        File file = new File("Main.class");
//        System.out.println(file.exists());
//        System.out.println(file.getAbsoluteFile());
//        System.out.println(fteile.getAbsolutePath());
//        System.out.println(file.getAbsoluteFile().getParentFile());
//        file = file.getAbsoluteFile().getParentFile();
//        String[] listFileNames = file.list();
//        for (String fileName : listFileNames) {
//            System.out.println(fileName);
//        }

        File propertiesFile = new File("src\\main\\resources\\autocompletionsystem.properties");
        FileReader fReader = new FileReader(propertiesFile);
        BufferedReader reader = new BufferedReader(fReader);
        System.out.println("Initialize properties file");
        String line = reader.readLine();
        while (line != null) {
            String[] tokens = line.split("=");
            if (tokens.length == 2) {
                if (tokens[0].equals(FileProperties.INPUT_FILES_DIRECTORY.getValue())) {
                    Properties.INPUT_FILES_DIRECTORY = tokens[1];
                }
                if (tokens[0].equals(FileProperties.PROCESSED_FILES_DIRECTORY.getValue())) {
                    Properties.PROCESSED_FILES_DIRECTORY = tokens[1];
                }
                if (tokens[0].equals(FileProperties.DICTIONARY_DIRECTORY.getValue())) {
                    Properties.DICTIONARY_DIRECTORY = tokens[1];
                }
                if (tokens[0].equals(FileProperties.DICTIONARY_NAME.getValue())) {
                    Properties.DICTIONARY_FILE_NAME = tokens[1];
                }
                if (tokens[0].equals(FileProperties.TST_OUTPUT_NAME.getValue())) {
                    Properties.TST_OUTPUT_FILE_NAME = tokens[1];
                }
            }
            line = reader.readLine();
        }
    }

    public void start() {
        TernarySearchTree tst = null;
        Dictionary dictionary = new Dictionary();

        Scanner scanner = new Scanner(System.in);

        String command = "";

        while (!command.equals("exit")) {
            System.out.println("enter command:");
            command = scanner.nextLine();
            if (command.equals(RESET)) {
                continue;
            }
            if (command.equals(IMPORT)) {
                continue;
            }
            if (command.equals(DISPLAY)) {
                continue;
            }
            if (command.equals(BUILD_TST)) {
                tst = TernarySearchTreeFactory.buildTst(dictionary);
                continue;
            }
            if (command.equals(RANDOM_BUILD_TST)) {
                tst = TernarySearchTreeFactory.buildRandomTst(dictionary);
                continue;
            }
            if (command.equals(PRINT_BUILD_TST)) {
                FilePrinter filePrinter = new FilePrinter();
                File file = new File(Properties.DICTIONARY_DIRECTORY + "\\" + Properties.TST_OUTPUT_FILE_NAME);
                filePrinter.printTstToFile(file, tst.print());
                continue;
            }
            if (command.equals(TEST)) {
                InputFilesProcessor ifp = new InputFilesProcessor(dictionary);
                ifp.processInputFiles();
                continue;
            }
            System.out.println("use:");
            System.out.println(RESET);
            System.out.println(IMPORT);
            System.out.println(DISPLAY);
        }
    }
}
