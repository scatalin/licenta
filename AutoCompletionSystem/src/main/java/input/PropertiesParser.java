package input;

import system.Properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by gstan on 10.03.2015.
 */
public class PropertiesParser {

    private static final String AUTO_COMPLETION_SYSTEM_PROPERTIES_LOCATION = "src/main/resources/autocompletionsystem.properties";
    private static final String WINDOWS = "WINDOWS";
    private static final String LINUX = "LINUX";

    public static void propertiesFileRead() throws IOException {
        File propertiesFile = new File(AUTO_COMPLETION_SYSTEM_PROPERTIES_LOCATION);
        FileReader fReader = new FileReader(propertiesFile);
        BufferedReader reader = new BufferedReader(fReader);
        System.out.println("Initialize properties file");
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            String[] tokens = line.split("=");
            if(tokens[0].startsWith("#")){
                continue;
            }
            if (tokens.length == 2) {
                if (tokens[0].equals(FileProperties.INPUT_FILES_DIRECTORY_WINDOWS.getValue()) && Properties.SYSTEM.equals(WINDOWS)) {
                    Properties.INPUT_FILES_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.DICTIONARY_DIRECTORY_WINDOWS.getValue()) && Properties.SYSTEM.equals(WINDOWS)) {
                    Properties.DICTIONARY_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.TEST_FILES_DIRECTORY_WINDOWS.getValue()) && Properties.SYSTEM.equals(WINDOWS)) {
                    Properties.TEST_FILES_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.TEST_ROTATION_DIRECTORY_WINDOWS.getValue()) && Properties.SYSTEM.equals(WINDOWS)) {
                    Properties.TEST_ROTATION_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.RESULT_FILES_DIRECTORY_WINDOWS.getValue()) && Properties.SYSTEM.equals(WINDOWS)) {
                    Properties.RESULT_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.INPUT_FILES_DIRECTORY_LINUX.getValue()) && Properties.SYSTEM.equals(LINUX)) {
                    Properties.INPUT_FILES_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.DICTIONARY_DIRECTORY_LINUX.getValue()) && Properties.SYSTEM.equals(LINUX)) {
                    Properties.DICTIONARY_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.TEST_FILES_DIRECTORY_LINUX.getValue()) && Properties.SYSTEM.equals(LINUX)) {
                    Properties.TEST_FILES_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.TEST_ROTATION_DIRECTORY_LINUX.getValue()) && Properties.SYSTEM.equals(LINUX)) {
                    Properties.TEST_ROTATION_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.RESULT_FILES_DIRECTORY_LINUX.getValue()) && Properties.SYSTEM.equals(LINUX)) {
                    Properties.RESULT_DIRECTORY = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.DICTIONARY_FILE_NAME.getValue())) {
                    Properties.DICTIONARY_FILE_NAME = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.STOP_WORDS_FILE.getValue())) {
                    Properties.STOP_WORDS_FILE = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.GRAPH_FILE.getValue())) {
                    Properties.GRAPH_FILE= tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.DICTIONARY_TEST_FILE_NAME.getValue())) {
                    Properties.DICTIONARY_TEST_FILE_NAME = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.TST_TREE_OUTPUT_FILE_NAME.getValue())) {
                    Properties.TST_TREE_OUTPUT_FILE_NAME = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.REPORT_OUTPUT_FILE_NAME.getValue())) {
                    Properties.REPORT_OUTPUT_FILE_NAME = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.MEASURES_OUTPUT_FILE_NAME.getValue())) {
                    Properties.MEASURES_OUTPUT_FILE_NAME = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.FORCE_SUPPORT_OS.getValue())) {
                    Properties.IS_FORCED_SUPPORTED_OS = Boolean.parseBoolean(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.FORCE_SYSTEM_PATH_SEPARATOR.getValue())) {
                    Properties.FORCED_SYSTEM_PATH_SEPARATOR = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.SYSTEM_ALPHABET.getValue())) {
                    Properties.ALPHABET = tokens[1];
                    Properties.SEGMENT_SIZE = Properties.ALPHABET.length();
                    continue;
                }
                if (tokens[0].equals(FileProperties.SYSTEM_DIACRITICS.getValue())) {
                    Properties.DIACRITICS = Boolean.valueOf(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.SUCCESSFUL_THRESHOLD.getValue())) {
                    Properties.SUCCESS_THRESHOLD = Integer.parseInt(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.TEST_WORD_DEPTH.getValue())) {
                    Properties.TEST_WORD_DEPTH = Integer.parseInt(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.TEST_WORD_START.getValue())) {
                    Properties.TEST_WORD_START = Integer.parseInt(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.TEST_DECAY_ALPHA_BEGIN.getValue())) {
                    Properties.TEST_DECAY_ALPHA_BEGIN = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.TEST_DECAY_ALPHA_END.getValue())) {
                    Properties.TEST_DECAY_ALPHA_END = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.TEST_DECAY_T_MAXIMUM.getValue())) {
                    Properties.TEST_DECAY_T_MAXIMUM = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.AUTOCOMPLETION_THRESHOLD.getValue())) {
                    Properties.AUTOCOMPLETION_THRESHOLD = Integer.parseInt(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.AUTOCOMPLETION_K_SIZE.getValue())) {
                    Properties.AUTOCOMPLETION_SUGGESTION_SIZE = Integer.parseInt(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.USER_WEIGHT.getValue())) {
                    Properties.USER_WEIGHT = Integer.parseInt(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.ACTUALITY_WEIGHT.getValue())) {
                    Properties.ACTUALITY_WEIGHT = Integer.parseInt(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.WEIGHT_CEILING.getValue())) {
                    Properties.WEIGHT_CEILING = Integer.parseInt(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.N_GRAM_DEPTH.getValue())) {
                    Properties.N_GRAM_DEPTH = Integer.parseInt(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.COMPLETION_TREE_OUTPUT_FILE_NAME.getValue())) {
                    Properties.COMPLETION_TREE_OUTPUT_FILE_NAME = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.WEIGHT_FREQUENCY.getValue())) {
                    Properties.WEIGHT_FREQUENCY = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.WEIGHT_FREQUENCY_USER.getValue())) {
                    Properties.WEIGHT_FREQUENCY_USER = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.WEIGHT_ACTUALITY_USER.getValue())) {
                    Properties.WEIGHT_ACTUALITY_USER = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.DECAY_ALPHA_ACTUALITY.getValue())) {
                    Properties.DECAY_ALPHA_ACTUALITY = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.DECAY_ALPHA_USER.getValue())) {
                    Properties.DECAY_ALPHA_USER = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.DECAY_QUERIES_ACTUALITY.getValue())) {
                    Properties.DECAY_QUERIES_ACTUALITY = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.DECAY_QUERIES_USER.getValue())) {
                    Properties.DECAY_QUERIES_USER = Double.parseDouble(tokens[1]);
                    continue;
                }
                if (tokens[0].equals(FileProperties.DECAY_FILE_OUTPUT_NAME.getValue())) {
                    Properties.DECAY_FILE_OUTPUT_NAME = tokens[1];
                    continue;
                }
                if (tokens[0].equals(FileProperties.DECAY_SUBDIRECTORY_OUTPUT_NAME.getValue())) {
                    Properties.DECAY_SUBDIRECTORY_OUTPUT_NAME = tokens[1];
                }
            }
        }
    }

    public static void validateOS() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (isWindows(OS)) {
            Properties.SYSTEM_PATH_SEPARATOR = "\\";
            Properties.SYSTEM = WINDOWS;
        } else if (isUnix(OS)) {
            Properties.SYSTEM_PATH_SEPARATOR = "/";
            Properties.SYSTEM = LINUX;
        } else if (Properties.IS_FORCED_SUPPORTED_OS) {
            Properties.SYSTEM_PATH_SEPARATOR = Properties.FORCED_SYSTEM_PATH_SEPARATOR;
            System.out.println("Operating system not supported. if you want this to be supported, set the force.supported property to true and write by hand the system separator and the paths");
            System.exit(1);
        }
    }

    private static boolean isWindows(String OS) {

        return (OS.contains("win"));

    }

    private static boolean isUnix(String OS) {

        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));

    }
}
