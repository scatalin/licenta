package input;

/**
 * Created by Catalin on 2/21/2015 .
 */
public enum FileProperties {
    INPUT_FILES_DIRECTORY("file.directory.input"),
    PROCESSED_FILES_DIRECTORY("file.directory.processed"),
    DICTIONARY_DIRECTORY("dictionary.directory"),
    DICTIONARY_FILE_NAME("dictionary.name"),
    TST_OUTPUT_FILE_NAME("tst.file.output.name"),
    FORCE_SUPORT_OS("force.suported"),
    FORCE_SYSTEM_PATH_SEPARATOR("system.separator");

    private final String value;

    FileProperties(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
