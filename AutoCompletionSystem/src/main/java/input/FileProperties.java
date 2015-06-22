package input;

/**
 * Created by Catalin on 2/21/2015 .
 */
public enum FileProperties {
    INPUT_FILES_DIRECTORY_WINDOWS("windows.file.directory.input"),
    DICTIONARY_DIRECTORY_WINDOWS("windows.dictionary.directory"),
    TEST_FILES_DIRECTORY_WINDOWS("windows.file.directory.testing"),
    RESULT_FILES_DIRECTORY_WINDOWS("windows.results.directory"),
    TEST_ROTATION_DIRECTORY_WINDOWS("windows.test.rotation.directory"),
    INPUT_FILES_DIRECTORY_LINUX("linux.file.directory.input"),
    DICTIONARY_DIRECTORY_LINUX("linux.dictionary.directory"),
    TEST_FILES_DIRECTORY_LINUX("linux.file.directory.testing"),
    RESULT_FILES_DIRECTORY_LINUX("linux.results.directory"),
    TEST_ROTATION_DIRECTORY_LINUX("linux.test.rotation.directory"),
    DICTIONARY_FILE_NAME("dictionary.name"),
    DICTIONARY_TEST_FILE_NAME("dictionary.test.name"),
    TST_TREE_OUTPUT_FILE_NAME("tst.tree.file.output.name"),
    REPORT_OUTPUT_FILE_NAME("test.report.file.name"),
    MEASURES_OUTPUT_FILE_NAME("test.measures.file.name"),
    SUCCESSFUL_THRESHOLD("autocompletion.suggestions.success.threshold"),
    TEST_WORD_DEPTH("test.run.word.depth"),
    TEST_WORD_START("test.run.word.start"),
    TEST_DECAY_ALPHA_BEGIN("test.decay.alpha.begin"),
    TEST_DECAY_ALPHA_END("test.decay.alpha.end"),
    TEST_DECAY_T_MAXIMUM("test.decay.t.maximum"),
    FORCE_SUPPORT_OS("force.supported"),
    FORCE_SYSTEM_PATH_SEPARATOR("system.separator"),
    SYSTEM_ALPHABET("system.alphabet"),
    SYSTEM_DIACRITICS("system.diacritics"),
    AUTOCOMPLETION_THRESHOLD("autocompletion.minimum.characters"),
    AUTOCOMPLETION_K_SIZE("autocompletion.suggestions.size"),
    USER_WEIGHT("user.weight"),
    ACTUALITY_WEIGHT("actuality.weight"),
    WEIGHT_CEILING("weight.ceiling"),
    N_GRAM_DEPTH("nGram.depth"),
    COMPLETION_TREE_OUTPUT_FILE_NAME("completion.tree.file.output.name"),
    WEIGHT_FREQUENCY("weight.frequency.rate"),
    WEIGHT_FREQUENCY_USER("weight.frequency.user.rate"),
    WEIGHT_ACTUALITY_USER("weight.actuality.user.rate"),
    DECAY_ALPHA("decay.alpha"),
    DECAY_FILE_OUTPUT_NAME("decay.file.output.name"),
    DECAY_SUBDIRECTORY_OUTPUT_NAME("decay.subdirectory.output.name"),
    STOP_WORDS_FILE("stop.words.name"),
    GRAPH_FILE("graph.name");


    private final String value;

    FileProperties(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
