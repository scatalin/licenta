import algorithms.segmenttree.SegmentTree;
import algorithms.segmenttree.build.SegmentTstTreeFactory;
import algorithms.segmenttree.printing.SegmentFilePrinter;
import algorithms.segmenttree.printing.SegmentTreeFilePrinter;
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
    private static String DICTIONARY_IMPORT = "dict i";
    private static String DICTIONARY_DISPLAY = "dict d";
    private static String TST_BUILD = "tst b";
    private static String TST_RANDOM_BUILD = "tst rb";
    private static String TST_WEIGHTED_BUILD = "tst wb";
    private static String TST_PRINT = "tst p";
    private static String SEGMENT_TST_BUILD = "st b";
    private static String SEGMENT_TST_RANDOM_BUILD = "st rb";
    private static String SEGMENT_TST_WEIGHTED_BUILD = "st wb";
    private static String SEGMENT_TST_PRINT = "st p";
    private static String TEST = "test";
    private static String SEGMENT_BUILD = "sgm b";
    private static String SEGMENT_PRINT = "sgm p";

    public static void main(String[] args) {
        try {
            PropertiesParser.validateOS();
            PropertiesParser.propertiesFileRead();
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
            if (command.equals(DICTIONARY_IMPORT)) {
                InputFilesProcessor ifp = new InputFilesProcessor(dictionary);
                ifp.processInputFiles();
                continue;
            }
            if (command.equals(DICTIONARY_DISPLAY)) {
                continue;
            }
            if (command.equals(TST_BUILD)) {
                tst = TernarySearchTreeFactory.buildTst(dictionary);
                continue;
            }
            if (command.equals(TST_RANDOM_BUILD)) {
                tst = TernarySearchTreeFactory.buildRandomTst(dictionary);
                continue;
            }
            if (command.equals(TST_WEIGHTED_BUILD)) {
                tst = TernarySearchTreeFactory.buildWeightedTst(dictionary);
                continue;
            }
            if (command.equals(TST_PRINT)) {
                FilePrinter.printTstToFile(TstFilePrinter.file, tst.print());
                continue;
            }
            if (command.equals(SEGMENT_TST_BUILD)) {
                SegmentTstTreeFactory.buildSegmentTst(segmentTree, dictionary);
                continue;
            }
            if (command.equals(SEGMENT_TST_RANDOM_BUILD)) {
                SegmentTstTreeFactory.buildRandomSegmentTst(segmentTree, dictionary);
                continue;
            }
            if (command.equals(SEGMENT_TST_WEIGHTED_BUILD)) {
                SegmentTstTreeFactory.buildWeightedSegmentTst(segmentTree, dictionary);
                continue;
            }
            if (command.equals(SEGMENT_TST_PRINT)) {
                FilePrinter.printTstToFile(SegmentTreeFilePrinter.file, segmentTree.printSubtrees());
                continue;
            }
            if (command.equals(SEGMENT_BUILD)) {
                segmentTree = new SegmentTree();
                segmentTree.setMaximumSize(Properties.SEGMENT_SIZE);
                segmentTree.buildSegmentTree();
                continue;
            }
            if (command.equals(SEGMENT_PRINT)) {
                FilePrinter.printTstToFile(SegmentFilePrinter.file, segmentTree.printTree());
                continue;
            }
            if (command.equals(TEST)) {
                continue;
            }
            System.out.println("wrong use:");
        }
        System.out.println("bye bye");
    }
}

