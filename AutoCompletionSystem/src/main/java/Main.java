import algorithms.SearchTree;
import algorithms.segmenttree.SegmentTree;
import algorithms.segmenttree.build.SegmentTstTreeFactory;
import algorithms.segmenttree.intern.SegmentNode;
import algorithms.segmenttree.parsing.SegmentTreeParser;
import algorithms.tst.TernarySearchTreeRecursive;
import algorithms.tst.build.SearchTreeFactory;
import algorithms.utils.FilePrinter;
import input.DictionaryProcessor;
import input.FilesProcessor;
import input.PropertiesParser;
import model.dictionary.Dictionary;
import system.Properties;
import testing.SystemRotationTester;
import testing.SystemTenFoldingTesting;
import testing.SystemTester;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Main {

    private static final String RESET = "reset";
    private static final String PROCESS_FILES_NO_MOVE = "input";
    private static final String PROCESS_FILES_MOVE = "input m";
    private static final String TEST_SYSTEM_ROTATION = "test all";
    private static final String TEST_SYSTEM_ROTATION_IN_MEMORY = "test all m";
    private static final String TEST_SYSTEM_TEN_FOLD = "test new";
    private static final String TEST_SYSTEM_PERCENTAGES_MOVE = "test p m";
    private static final String TEST_SYSTEM_PERCENTAGES_NO_MOVE = "test p";
    private static final String DICTIONARY_IMPORT = "dict i";
    private static final String DICTIONARY_DISPLAY = "dict d";
    private static final String TST_BUILD = "tst b";
    private static final String TST_RANDOM_BUILD = "tst rb";
    private static final String TST_WEIGHTED_BUILD = "tst wb";
    private static final String TST_PRINT = "tst p";
    private static final String TST_SET_K = "tst sk";
    private static final String TST_GET_NEXT_K = "tst gnk";
    private static final String TST_GET_K = "tst gk";
    private static final String TREE_SET_K = "tree sk";
    private static final String TREE_GET_NEXT_K = "tree gnk";
    private static final String TREE_GET_K = "tree gk";
    private static final String TREE_BUILD = "tree b";
    private static final String TREE_RANDOM_BUILD = "tree rb";
    private static final String TREE_WEIGHTED_BUILD = "tree wb";
    private static final String TREE_PRINT = "tree p";
    private static final String SEGMENT_BUILD = "sgm b";
    private static final String SEGMENT_PRINT = "sgm p";
    private static final String SEGMENT_COUNT = "sgm c";

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
        SegmentTree tree = new SegmentTree();
        SearchTree tst = new TernarySearchTreeRecursive();
        Dictionary dictionary = new Dictionary();
        DictionaryProcessor dictionaryProcessor = new DictionaryProcessor(dictionary);
        dictionaryProcessor.readDictionary();

        Scanner scanner = new Scanner(System.in);

        String command = "";

        while (!command.equals("exit")) {
            System.out.println("enter command:");
            command = scanner.nextLine();
            if (command.equals(RESET)) {
                try {
                    PropertiesParser.propertiesFileRead();
                    tree = new SegmentTree();
                    tst = new TernarySearchTreeRecursive();
                    dictionary = new Dictionary();
                    dictionaryProcessor = new DictionaryProcessor(dictionary);
                    dictionaryProcessor.readDictionary();
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
            if (command.equals(PROCESS_FILES_MOVE)) {
                FilesProcessor filesProcessor = new FilesProcessor(dictionaryProcessor);
                filesProcessor.processInputFiles(true,-1);
                continue;
            }
            if (command.equals(PROCESS_FILES_NO_MOVE)) {
                FilesProcessor filesProcessor = new FilesProcessor(dictionaryProcessor);
                filesProcessor.processInputFiles(false,-1);
                continue;
            }
            if (command.equals(DICTIONARY_DISPLAY)) {
                System.out.println("dictionarul are " + dictionary.getWords().size() + " cuvinte");
                System.out.println(dictionary.getWords());
                continue;
            }
            if (command.equals(TST_BUILD)) {
                tst = SearchTreeFactory.buildTst(dictionary);
                continue;
            }
            if (command.equals(TST_RANDOM_BUILD)) {
                tst = SearchTreeFactory.buildRandomTst(dictionary);
                continue;
            }
            if (command.equals(TST_WEIGHTED_BUILD)) {
                tst = SearchTreeFactory.buildWeightedTst(dictionary);
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
            if (command.equals(TREE_BUILD)) {
                SegmentTstTreeFactory.buildSegmentTst(tree, dictionary);
                continue;
            }
            if (command.equals(TREE_RANDOM_BUILD)) {
                SegmentTstTreeFactory.buildRandomSegmentTst(tree, dictionary);
                continue;
            }
            if (command.equals(TREE_WEIGHTED_BUILD)) {
                tree.buildSegmentTree();
                tree.setK(Properties.AUTOCOMPLETION_K_SIZE);
                SegmentTstTreeFactory.buildWeightedSegmentTst(tree, dictionary);
                continue;
            }
            if (command.equals(TREE_PRINT)) {
                FilePrinter.printTstToFile(FilePrinter.SEGMENT_TREE_FILE, tree.printSubtrees());
                continue;
            }
            if (command.equals(SEGMENT_BUILD)) {
                tree = new SegmentTree();
                tree.buildSegmentTree();
                continue;
            }
            if (command.equals(SEGMENT_PRINT)) {
                FilePrinter.printTstToFile(FilePrinter.SEGMENT_FILE, tree.printTree());
                continue;
            }
            if (command.equals(SEGMENT_COUNT)) {
                SegmentTreeParser parser = new SegmentTreeParser((SegmentNode) tree.getRoot());
                System.out.println(parser.countNodes());
                continue;
            }
            if (command.equals(TEST_SYSTEM_ROTATION)) {
                SystemRotationTester rotationTester = new SystemRotationTester();
                try {
                    rotationTester.testSystemByRotation();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.equals(TEST_SYSTEM_ROTATION_IN_MEMORY)) {
                SystemRotationTester rotationTester = new SystemRotationTester();
                try {
                    rotationTester.testSystemByRotationInMemory();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.equals(TEST_SYSTEM_TEN_FOLD)) {
                SystemTenFoldingTesting tester = new SystemTenFoldingTesting();
                try {
                    tester.testSystem();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.equals(TEST_SYSTEM_PERCENTAGES_MOVE)) {
                SystemTester tester = new SystemTester(tree);
                try {
                    tester.testSystem(true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.equals(TEST_SYSTEM_PERCENTAGES_NO_MOVE)) {
                SystemTester tester = new SystemTester(tree);
                try {
                    tester.testSystem(false);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.startsWith(TREE_SET_K)) {
                String number = command.replace(TREE_SET_K + " ", "");
                try {
                    int k = Integer.parseInt(number);
                    tree.setK(k);
                    tree.resetSearchK();
                } catch (NumberFormatException e) {
                    System.out.println("no integer provided");
                }
                continue;
            }
            if (command.startsWith(TREE_GET_K)) {
                String word = command.replace(TREE_GET_K + " ", "");
                tree.resetSearchK();
                System.out.println(tree.getNextTopK(word));
                continue;
            }
            if (command.startsWith(TREE_GET_NEXT_K)) {
                String word = command.replace(TREE_GET_NEXT_K + " ", "");
                System.out.println(tree.getNextTopK(word));
                continue;
            }
            System.out.println("wrong use:");
        }
        System.out.println("bye bye");
    }
}

