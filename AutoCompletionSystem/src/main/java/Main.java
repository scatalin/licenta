import algorithms.SearchTree;
import algorithms.SearchTreeFactory;
import algorithms.segmenttree.SegmentTreeLinear;
import algorithms.segmenttree.build.SegmentTstTreeBuilder;
import algorithms.segmenttree.printing.NestedTstPrettyPrinter;
import algorithms.tst.build.TernarySearchTreeFactory;
import algorithms.utils.FilePrinter;
import algorithms.utils.PrettyPrinter;
import dictionary.Dictionary;
import input.DictionaryProcessor;
import input.FilesProcessor;
import input.PropertiesParser;
import printing.Window;
import system.Properties;
import testing.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Main {

    private static final String RESET = "reset";
    private static final String DRAW = "draw";
    private static final String DRAW_ALL = "draw all";
    private static final String PROCESS_FILES = "input";
    private static final String TEST_SYSTEM_ROTATION = "test all";
    private static final String TEST_SYSTEM_ROTATION_IN_MEMORY = "test all m";
    private static final String TEST_SYSTEM_TEN_FOLD = "test ten fold";
    private static final String TEST_SYSTEM_TEN_FOLD_SYSTEM = "test tfs";
    private static final String TEST_SYSTEM_TEN_FOLD_SYSTEM_UPDATE = "test tfsu";
    private static final String TEST_SYSTEM_TEN_FOLD_SYSTEM_INSERTING = "test tfsi";
    private static final String TEST_SYSTEM_PERCENTAGES = "test p";
    private static final String DICTIONARY_IMPORT = "dict i";
    private static final String DICTIONARY_DISPLAY = "dict d";
    private static final String DICTIONARY_DISPLAY_WEIGHTED = "dict dw";
    private static final String TST_BUILD = "tst b";
    private static final String TST_WEIGHTED_BUILD = "tst wb";
    private static final String TST_PRINT = "tst p";
    private static final String TST_SET_K = "tst sk";
    private static final String TST_GET_NEXT_K = "tst gnk";
    private static final String TST_GET_K = "tst gk";
    private static final String TREE_SET_K = "tree sk";
    private static final String TREE_GET_NEXT_K = "tree gnk";
    private static final String TREE_GET_K = "tree gk";
    private static final String TREE_BUILD = "tree b";
    private static final String TREE_WEIGHTED_BUILD = "tree wb";
    private static final String TREE_PRINT = "tree p";

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
        SearchTree tree = SearchTreeFactory.createCompletionTree();
        SearchTree tst = SearchTreeFactory.createTst();
        Dictionary dictionary = new Dictionary();
        DictionaryProcessor dictionaryProcessor = new DictionaryProcessor(dictionary);
        dictionaryProcessor.readDictionary();

        Scanner scanner = new Scanner(System.in);

        String command = "";

        Window w = null;
        while (!command.equals("exit")) {
            System.out.println("enter command:");
            command = scanner.nextLine();
            if (command.equals(RESET)) {
                try {
                    PropertiesParser.propertiesFileRead();
                    tree = SearchTreeFactory.createCompletionTree();
                    tst = SearchTreeFactory.createTst();
                    dictionary = new Dictionary();
                    dictionaryProcessor = new DictionaryProcessor(dictionary);
                    dictionaryProcessor.readDictionary();
                    if (w != null) {
                        w.dispose();
                    }
                } catch (IOException e) {
                    System.out.println("System cannot restart");
                    e.printStackTrace();
                    System.exit(0);
                }
                continue;
            }
            if (command.equals(DRAW)) {
                w = new Window();
                w.save();
                continue;
            }
            if (command.equals(DRAW_ALL)) {
                PrintAlphas pa = new PrintAlphas();
                pa.printAlphas();
                continue;
            }
            if (command.equals(DICTIONARY_IMPORT)) {
                dictionaryProcessor.readDictionary();
                continue;
            }
            if (command.equals(PROCESS_FILES)) {
                FilesProcessor filesProcessor = new FilesProcessor(dictionaryProcessor);
                filesProcessor.processInputFiles(-1);
                continue;
            }
            if (command.equals(DICTIONARY_DISPLAY)) {
                System.out.println("dictionarul are " + dictionary.asList().size() + " cuvinte");
                System.out.println(dictionary.asList());
                continue;
            }
            if (command.equals(DICTIONARY_DISPLAY_WEIGHTED)) {
                System.out.println("dictionarul are " + dictionary.asList().size() + " cuvinte");
                System.out.println(dictionary.getWordsSortedByWeight());
                continue;
            }
            if (command.equals(TST_BUILD)) {
                tst = TernarySearchTreeFactory.buildTst(dictionary.asList());
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
                    tst.setNumberOfSuggestions(k);
                    tst.resetCompletion();
                } catch (NumberFormatException e) {
                    System.out.println("no integer provided");
                }
                continue;
            }
            if (command.startsWith(TST_GET_K)) {
                command = command.replace(TST_GET_K + " ", "");
                tst.resetCompletion();
                System.out.println(tst.getSuggestions(command));
                continue;
            }
            if (command.startsWith(TST_GET_NEXT_K)) {
                command = command.replace(TST_GET_NEXT_K + " ", "");
                System.out.println(tst.getSuggestions(command));
                continue;
            }
            if (command.equals(TREE_BUILD)) {
                tree = SegmentTstTreeBuilder.buildSegmentTst(dictionary);
                continue;
            }
            if (command.equals(TREE_WEIGHTED_BUILD)) {
                tree = SegmentTstTreeBuilder.buildWeightedSegmentTst(dictionary);
                tree.setNumberOfSuggestions(Properties.AUTOCOMPLETION_SUGGESTION_SIZE);
                continue;
            }
            if (command.equals(TREE_PRINT)) {
                PrettyPrinter printer = new NestedTstPrettyPrinter((SegmentTreeLinear) tree);
                FilePrinter.printTstToFile(FilePrinter.COMPLETION_TREE_FILE, printer.prettyPrint());
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
            if (command.equals(TEST_SYSTEM_TEN_FOLD_SYSTEM)) {
                RepeatedCompletionTenFoldingTesting tester = new RepeatedCompletionTenFoldingTesting();
                try {
                    tester.testSystemWithRotation();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.equals(TEST_SYSTEM_TEN_FOLD_SYSTEM_UPDATE)) {
                RepeatedCompletionTenFoldingTesting tester = new RepeatedCompletionTenFoldingTesting();
                try {
                    tester.testSystemWithUpdate();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.equals(TEST_SYSTEM_TEN_FOLD_SYSTEM_INSERTING)) {
                RepeatedCompletionTenFoldingTesting tester = new RepeatedCompletionTenFoldingTesting();
                try {
                    tester.testSystemWithoutUpdate();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.equals(TEST_SYSTEM_PERCENTAGES)) {
                SystemTester tester = new SystemTester(tree);
                try {
                    tester.testSystem();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.startsWith(TREE_SET_K)) {
                String number = command.replace(TREE_SET_K + " ", "");
                try {
                    int k = Integer.parseInt(number);
                    tree.setNumberOfSuggestions(k);
                    tree.resetCompletion();
                } catch (NumberFormatException e) {
                    System.out.println("no integer provided");
                }
                continue;
            }
            if (command.startsWith(TREE_GET_K)) {
                String word = command.replace(TREE_GET_K + " ", "");
                tree.resetCompletion();
                System.out.println(tree.getSuggestions(word));
                continue;
            }
            if (command.startsWith(TREE_GET_NEXT_K)) {
                String word = command.replace(TREE_GET_NEXT_K + " ", "");
                System.out.println(tree.getSuggestions(word));
                continue;
            }
            System.out.println("wrong use:");
        }
        System.out.println("bye bye");
    }
}

