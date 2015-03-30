import algorithms.segmenttree.SegmentTree;
import algorithms.segmenttree.build.SegmentTstTreeFactory;
import algorithms.tst.TernarySearchTree;
import algorithms.tst.TernarySearchTreeRecursive;
import algorithms.tst.build.TernarySearchTreeFactory;
import algorithms.utils.FilePrinter;
import input.DictionaryProcessor;
import input.FilesProcessor;
import system.Properties;
import input.PropertiesParser;
import model.dictionary.Dictionary;
import testing.SystemTester;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Main {

    private static final String RESET = "reset";
    private static final String PROCESS_FILES = "input";
    private static final String TEST_SYSTEM = "test";
    private static final String DICTIONARY_IMPORT = "dict i";
    private static final String DICTIONARY_DISPLAY = "dict d";
    private static final String TST_BUILD = "tst b";
    private static final String TST_RANDOM_BUILD = "tst rb";
    private static final String TST_WEIGHTED_BUILD = "tst wb";
    private static final String TST_PRINT = "tst p";
    private static final String TST_SET_K = "tst sk";
    private static final String TST_GET_NEXT_K = "tst gnk";
    private static final String TST_GET_K = "tst gk";
    private static final String SEGMENT_TST_BUILD = "st b";
    private static final String SEGMENT_TST_RANDOM_BUILD = "st rb";
    private static final String SEGMENT_TST_WEIGHTED_BUILD = "st wb";
    private static final String SEGMENT_TST_PRINT = "st p";
    private static final String SEGMENT_BUILD = "sgm b";
    private static final String SEGMENT_PRINT = "sgm p";

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

    void start() {
        TernarySearchTree tst = new TernarySearchTreeRecursive();
        SegmentTree segmentTree = new SegmentTree();
        Dictionary dictionary = new Dictionary();
        DictionaryProcessor dictionaryProcessor = new DictionaryProcessor(dictionary);

        Scanner scanner = new Scanner(System.in);

        String command = "";

        while (!command.equals("exit")) {
            System.out.println("enter command:");
            command = scanner.nextLine();
            if (command.equals(RESET)) {
                try {
                    PropertiesParser.validateOS();
                    PropertiesParser.propertiesFileRead();
                    tst = new TernarySearchTreeRecursive();
                    segmentTree = new SegmentTree();
                    dictionary = new Dictionary();
                    dictionaryProcessor = new DictionaryProcessor(dictionary);
                } catch (IOException e) {
                    System.out.println("System cannot restart");
                    e.printStackTrace();
                    System.exit(0);
                }
                continue;
            }
            if (command.equals(DICTIONARY_IMPORT)) {
                dictionaryProcessor.readDictionary();
                continue;
            }
            if (command.equals(PROCESS_FILES)) {
                FilesProcessor filesProcessor = new FilesProcessor(dictionaryProcessor);
                filesProcessor.processInputFiles();
                continue;
            }
            if (command.equals(DICTIONARY_DISPLAY)) {
                System.out.println("dictionarul are " + dictionary.getWords().size() + " cuvinte");
                System.out.println(dictionary.getWords());
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
                FilePrinter.printTstToFile(FilePrinter.TST_FILE, tst.print());
                continue;
            }
            if (command.startsWith(TST_SET_K)) {
                command = command.replace(TST_SET_K + " ", "");
                try {
                    int k = Integer.parseInt(command);
                    tst.setK(k);
                    tst.resetSearchK();
                } catch (NumberFormatException e) {
                    System.out.println("no integer provided");
                }
                continue;
            }
            if (command.startsWith(TST_GET_K)) {
                command = command.replace(TST_GET_K + " ", "");
                tst.resetSearchK();
                System.out.println(tst.getNextTopK(command));
                continue;
            }
            if (command.startsWith(TST_GET_NEXT_K)) {
                command = command.replace(TST_GET_NEXT_K + " ", "");
                System.out.println(tst.getNextTopK(command));
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
                FilePrinter.printTstToFile(FilePrinter.SEGMENT_TREE_FILE, segmentTree.printSubtrees());
                continue;
            }
            if (command.equals(SEGMENT_BUILD)) {
                segmentTree = new SegmentTree();
                segmentTree.setMaximumSize(Properties.SEGMENT_SIZE);
                segmentTree.buildSegmentTree();
                continue;
            }
            if (command.equals(SEGMENT_PRINT)) {
                FilePrinter.printTstToFile(FilePrinter.SEGMENT_FILE, segmentTree.printTree());
                continue;
            }
            if (command.equals(TEST_SYSTEM)) {
                tst.setK(10);
                SystemTester tester = new SystemTester(tst);
                try {
                    tester.testSystem();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            System.out.println("wrong use:");
        }
        System.out.println("bye bye");
    }
}

