import algorithms.SearchTree;
import algorithms.SearchTreeFactory;
import algorithms.segmenttree.SegmentTreeLinear;
import algorithms.segmenttree.build.SegmentTstTreeBuilder;
import algorithms.segmenttree.printing.NestedTstPrettyPrinter;
import algorithms.utils.FilePrinter;
import algorithms.utils.PrettyPrinter;
import dictionary.Dictionary;
import input.DictionaryProcessor;
import input.FilesProcessor;
import input.PropertiesParser;
import system.Properties;
import testing.SystemBigTester;
import testing.SystemTenFoldingTesting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Main {

    private static final String RESET = "reset";
    private static final String PROCESS_FILES = "input";
    private static final String TEST_SYSTEM_TEN_FOLD_DEFAULT = "test ten fold t";
    private static final String TEST_SYSTEM_TEN_FOLD = "test ten fold f";
    private static final String TEST_SYSTEM_FINAL = "test f";
    private static final String DICTIONARY_IMPORT = "dict i";
    private static final String DICTIONARY_DISPLAY = "dict d";
    private static final String DICTIONARY_DISPLAY_SIZE = "dict s";
    private static final String DICTIONARY_DISPLAY_WEIGHTED = "dict dw";
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
        Dictionary dictionary = new Dictionary();
        DictionaryProcessor dictionaryProcessor = new DictionaryProcessor(dictionary);
        dictionaryProcessor.readDictionary();

        Scanner scanner = new Scanner(System.in);

        String command = "";

        while (!"exit".equals(command)) {
            System.out.println("enter command:");
            command = scanner.nextLine();
            if (command.equals(RESET)) {
                try {
                    PropertiesParser.propertiesFileRead();
                    tree = SearchTreeFactory.createCompletionTree();
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
            if (command.equals(PROCESS_FILES)) {
                FilesProcessor filesProcessor = new FilesProcessor(dictionaryProcessor);
                filesProcessor.processInputFiles();
                continue;
            }
            if (command.equals(DICTIONARY_DISPLAY)) {
                System.out.println("dictionarul are " + dictionary.asList().size() + " cuvinte");
                System.out.println(dictionary.asList());
                continue;
            }
            if (command.equals(DICTIONARY_DISPLAY_SIZE)) {
                System.out.println("dictionarul are " + dictionary.asList().size() + " cuvinte");
                continue;
            }
            if (command.equals(DICTIONARY_DISPLAY_WEIGHTED)) {
                System.out.println("dictionarul are " + dictionary.asList().size() + " cuvinte");
                System.out.println(dictionary.getWordsSortedByWeight());
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
            if (command.equals(TEST_SYSTEM_TEN_FOLD_DEFAULT)) {
                SystemTenFoldingTesting tester = new SystemTenFoldingTesting();
                try {
                    tester.testSystem(dictionary,true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.equals(TEST_SYSTEM_TEN_FOLD)) {
                SystemTenFoldingTesting tester = new SystemTenFoldingTesting();
                try {
                    tester.testSystem(dictionary,false);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (command.equals(TEST_SYSTEM_FINAL)) {
                SystemBigTester tester = new SystemBigTester();
                try {
                    tester.testSystem(dictionary);
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

