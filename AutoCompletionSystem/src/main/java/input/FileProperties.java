package input;

/**
 * Created by Catalin on 2/21/2015 .
 */
public enum FileProperties {
    INPUT_FILES_DIRECTORY_WINDOWS("windows.file.directory.input"),
    PROCESSED_FILES_DIRECTORY_WINDOWS("windows.file.directory.processed"),
    DICTIONARY_DIRECTORY_WINDOWS("windows.dictionary.directory"),
    TEST_FILES_DIRECTORY_WINDOWS("windows.file.directory.testing"),
    TEST_ROTATION_DIRECTORY_WINDOWS("windows.test.rotation.directory"),
    INPUT_FILES_DIRECTORY_LINUX("linux.file.directory.input"),
    PROCESSED_FILES_DIRECTORY_LINUX("linux.file.directory.processed"),
    DICTIONARY_DIRECTORY_LINUX("linux.dictionary.directory"),
    TEST_FILES_DIRECTORY_LINUX("linux.file.directory.testing"),
    TEST_ROTATION_DIRECTORY_LINUX("linux.test.rotation.directory"),
    DICTIONARY_FILE_NAME("dictionary.name"),
    DICTIONARY_TEST_FILE_NAME("dictionary.test.name"),
    TST_OUTPUT_FILE_NAME("tst.file.output.name"),
    REPORT_OUTPUT_FILE_NAME("test.report.file.name"),
    MEASURES_OUTPUT_FILE_NAME("test.measures.file.name"),
    SUCCESSFUL_THRESHOLD("autocompletion.success.threshold"),
    TEST_WORD_DEPTH("test.run.word.depth"),
    TEST_WORD_START("test.run.word.start"),
    FORCE_SUPPORT_OS("force.supported"),
    FORCE_SYSTEM_PATH_SEPARATOR("system.separator"),
    SEGMENT_SIZE("segment.size"),
    AUTOCOMPLETION_THRESHOLD("autocompletion.minimum.characters"),
    AUTOCOMPLETION_K_SIZE("autocompletion.k.size"),
    SEGMENT_OUTPUT_FILE_NAME("segment.file.output.name"),
    SEGMENT_TREE_OUTPUT_FILE_NAME("segment.tree.file.output.name"),
    WEIGHT_FREQUENCY("weight.frequency.rate"),
    WEIGHT_FREQUENCY_USER("weight.frequency.user.rate"),
    WEIGHT_ACTUALITY_USER("weight.actuality.user.rate");


    private final String value;

    FileProperties(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
