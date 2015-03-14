import algorithms.segmenttree.SegmentTree;
import algorithms.tst.TernarySearchTree;
import algorithms.tst.build.TernarySearchTreeFactory;
import algorithms.tst.printing.TstFilePrinter;
import algorithms.utils.FilePrinter;
import input.InputFilesProcessor;
import input.Properties;
import input.PropertiesParser;
import model.dictionary.Dictionary;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Main {

    private static String RESET = "reset";
    private static String IMPORT = "import";
    private static String DISPLAY = "d d";
    private static String BUILD_TST = "tst b";
    private static String RANDOM_BUILD_TST = "tst rb";
    private static String PRINT_TST = "tst p";
    private static String TEST = "test";
    private static String BUILD_SEGMENT = "sgm b";
    private static String PRINT_SEGMENT = "sgm p";

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
        TernarySearchTree tst = null;
        SegmentTree segmentTree = null;
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
            if (command.equals(PRINT_TST)) {
                FilePrinter.printTstToFile(TstFilePrinter.file,tst.print());
                continue;
            }
            if (command.equals(BUILD_SEGMENT)) {
                segmentTree = new SegmentTree();
                segmentTree.setMaximumSize(Properties.SEGMENT_SIZE);
                segmentTree.buildSegmentTree();
                continue;
            }
            if (command.equals(PRINT_SEGMENT)) {
                System.out.println(segmentTree.print());
                continue;
            }
            if (command.equals(TEST)) {
                InputFilesProcessor ifp = new InputFilesProcessor(dictionary);
                ifp.processInputFiles();
                continue;
            }
            System.out.println("wrong use:");
        }
    }
}

