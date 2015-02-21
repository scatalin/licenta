package input;

/**
 * Created by Catalin on 2/21/2015 .
 */
public enum FileProperties {
    INPUT_FILES_DIRECTORY("file.directory.input"),
    PROCESSED_FILES_DIRECTORY("file.directory.processed"),
    DICTIONARY_DIRECTORY("dictionary.directory"),
    DICTIONARY_NAME("dictionary.name");

    private final String value;

    FileProperties(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
