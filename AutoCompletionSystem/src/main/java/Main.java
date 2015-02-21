import input.FileProperties;
import input.InputFilesProcessor;
import input.Properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Catalin on 2/21/2015 .
 */
public class Main {

    private static String RESET = "reset dictionary";
    private static String IMPORT = "import input files";
    private static String DISPLAY = "display dictionary";
    private static String TEST = "test";

    public static void main(String[] args) {
        try {
            propertiesFileRead();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

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
            if(command.equals(TEST)){
                InputFilesProcessor ifp = new InputFilesProcessor();
                ifp.processInputFiles();
                continue;
            }
            System.out.println("use:");
            System.out.println(RESET);
            System.out.println(IMPORT);
            System.out.println(DISPLAY);
        }
    }

    private static void propertiesFileRead() throws IOException {
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
            }
            line = reader.readLine();
        }
    }
}
