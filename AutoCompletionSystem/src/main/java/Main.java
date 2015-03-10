import algorithms.tst.TernarySearchTree;
import algorithms.tst.TernarySearchTreeRecursive;
import algorithms.tst.extras.FilePrinter;
import algorithms.tst.extras.TernarySearchTreeFactory;
import input.InputFilesProcessor;
import input.Properties;
import input.PropertiesParser;
import model.dictionary.Dictionary;

import java.io.File;
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
            PropertiesParser.propertiesFileRead();
            PropertiesParser.validateOS();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Main main = new Main();
        main.start();
    }

    public void start() {
        TernarySearchTree tst = new TernarySearchTreeRecursive();
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
                File file = new File(Properties.DICTIONARY_DIRECTORY + Properties.SYSTEM_PATH_SEPARATOR + Properties.TST_OUTPUT_FILE_NAME);
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
