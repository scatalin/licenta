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

        File propertiesFile = new File(AUTO_COMPLETION_SYSTEM_PROPERTIES_LOCATION);
        FileReader fReader = new FileReader(propertiesFile);
        BufferedReader reader = new BufferedReader(fReader);
        System.out.println("Initialize properties file");
        String line = reader.readLine();
        while (line != null) {
            String[] tokens = line.split("=");
            if (tokens.length == 2) {
                if (tokens[0].equals(FileProperties.INPUT_FILES_DIRECTORY_WINDOWS.getValue()) && Properties.SYSTEM.equals(WINDOWS)) {
                    Properties.INPUT_FILES_DIRECTORY = tokens[1];
                }
                if (tokens[0].equals(FileProperties.PROCESSED_FILES_DIRECTORY_WINDOWS.getValue()) && Properties.SYSTEM.equals(WINDOWS)) {
                    Properties.PROCESSED_FILES_DIRECTORY = tokens[1];
                }
                if (tokens[0].equals(FileProperties.DICTIONARY_DIRECTORY_WINDOWS.getValue()) && Properties.SYSTEM.equals(WINDOWS)) {
                    Properties.DICTIONARY_DIRECTORY = tokens[1];
                }
                if (tokens[0].equals(FileProperties.INPUT_FILES_DIRECTORY_LINUX.getValue()) && Properties.SYSTEM.equals(LINUX)) {
                    Properties.INPUT_FILES_DIRECTORY = tokens[1];
                }
                if (tokens[0].equals(FileProperties.PROCESSED_FILES_DIRECTORY_LINUX.getValue()) && Properties.SYSTEM.equals(LINUX)) {
                    Properties.PROCESSED_FILES_DIRECTORY = tokens[1];
                }
                if (tokens[0].equals(FileProperties.DICTIONARY_DIRECTORY_LINUX.getValue()) && Properties.SYSTEM.equals(LINUX)) {
                    Properties.DICTIONARY_DIRECTORY = tokens[1];
                }
                if (tokens[0].equals(FileProperties.DICTIONARY_FILE_NAME.getValue())) {
                    Properties.DICTIONARY_FILE_NAME = tokens[1];
                }
                if (tokens[0].equals(FileProperties.TST_OUTPUT_FILE_NAME.getValue())) {
                    Properties.TST_OUTPUT_FILE_NAME = tokens[1];
                }
                if (tokens[0].equals(FileProperties.FORCE_SUPPORT_OS.getValue())) {
                    Properties.IS_FORCED_SUPPORTED_OS = Boolean.parseBoolean(tokens[1]);
                }
                if (tokens[0].equals(FileProperties.FORCE_SYSTEM_PATH_SEPARATOR.getValue())) {
                    Properties.FORCED_SYSTEM_PATH_SEPARATOR = tokens[1];
                }
                if (tokens[0].equals(FileProperties.SEGMENT_SIZE.getValue())) {
                    Properties.SEGMENT_SIZE = Integer.parseInt(tokens[1]);
                }
                if (tokens[0].equals(FileProperties.SEGMENT_OUTPUT_FILE_NAME.getValue())) {
                    Properties.SEGMENT_OUTPUT_FILE_NAME = tokens[1];
                }
                if (tokens[0].equals(FileProperties.SEGMENT_TREE_OUTPUT_FILE_NAME.getValue())) {
                    Properties.SEGMENT_TREE_OUTPUT_FILE_NAME = tokens[1];
                }
            }
            line = reader.readLine();
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
