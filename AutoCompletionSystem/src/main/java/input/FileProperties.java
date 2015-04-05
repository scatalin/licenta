package input;

/**
 * Created by Catalin on 2/21/2015 .
 */
public enum FileProperties {
    INPUT_FILES_DIRECTORY_WINDOWS("windows.file.directory.input"),
    PROCESSED_FILES_DIRECTORY_WINDOWS("windows.file.directory.processed"),
    DICTIONARY_DIRECTORY_WINDOWS("windows.dictionary.directory"),
    TEST_FILES_DIRECTORY_WINDOWS("windows.file.directory.testing"),
    INPUT_FILES_DIRECTORY_LINUX("linux.file.directory.input"),
    PROCESSED_FILES_DIRECTORY_LINUX("linux.file.directory.processed"),
    DICTIONARY_DIRECTORY_LINUX("linux.dictionary.directory"),
    TEST_FILES_DIRECTORY_LINUX("linux.file.directory.testing"),
    DICTIONARY_FILE_NAME("dictionary.name"),
    TST_OUTPUT_FILE_NAME("tst.file.output.name"),
    REPORT_OUTPUT_FILE_NAME("test.report.file.name"),
    REPORT_ALL_OUTPUT_FILE_NAME("test.all.file.name"),
    SUCCESSFUL_THRESHOLD("autocompletion.success.threshold"),
    FORCE_SUPPORT_OS("force.supported"),
    FORCE_SYSTEM_PATH_SEPARATOR("system.separator"),
    SEGMENT_SIZE("segment.size"),
    AUTOCOMPLETION_THRESHOLD("autocompletion.minimum.characters"),
    AUTOCOMPLETION_K_SIZE("autocompletion.k.size"),
    SEGMENT_OUTPUT_FILE_NAME("segment.file.output.name"),
    SEGMENT_TREE_OUTPUT_FILE_NAME("segment.tree.file.output.name");

    private final String value;

    FileProperties(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
