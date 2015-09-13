package system;

import controller.FrazeController;
import controller.FrazeQuery;
import controller.WordQuery;
import controller.WordsController;
import dictionary.Dictionary;
import dictionary.Word;
import input.DictionaryProcessor;
import input.FilesProcessor;

import java.util.List;

/**
 * Created by Catalin on 5/16/2015 .
 */
public class AutoCompletionSystemImpl implements AutoCompletionSystem {

    private WordQuery wordQuery;
    private WordsController wordsController;

    private Dictionary backup;
    private String currentDictionaryName;
    private FrazeQuery frazeQuery;
    private FrazeController frazeController;

    public AutoCompletionSystemImpl() {
        wordQuery = new WordQuery();
        frazeController = new FrazeController();
        wordsController = new WordsController();
        frazeQuery = new FrazeQuery();
    }

    public void readDictionary() {
        DictionaryProcessor dictionaryProcessor = new DictionaryProcessor(ServiceLocator.getDictionary());
        dictionaryProcessor.readDictionary();
    }

    public void loadCustomDictionary(Dictionary dictionary) {
        ServiceLocator.setDictionary(dictionary);
    }


    @Override
    public List<String> getWordCompletion(String prefix) {
        return wordQuery.getSuggestions(prefix);
    }

    @Override
    public void selectCompletionWord(String word) {
        wordsController.handleWord(word);
        ServiceLocator.getModelConstructor().constructModel();
    }

    @Override
    public List<String> getFrazeCompletion(String word) {
        return frazeQuery.getSuggestions(word);
    }

    @Override
    public void inputCharacter(Character character) {
        frazeController.handleCharacter(character);
    }

    @Override
    public void loadTextFilesInDictionary() {
        saveDictionary();
        FilesProcessor filesProcessor = new FilesProcessor(new DictionaryProcessor(ServiceLocator.getDictionary()));
        filesProcessor.processInputFiles();
    }

    @Override
    public void saveDictionary() {
        new DictionaryProcessor(ServiceLocator.getDictionary(), currentDictionaryName).saveDictionary();
    }

    @Override
    public void readDictionary(String dictionaryName) {
        saveDictionary();
        ServiceLocator.createDictionary(dictionaryName);
        new DictionaryProcessor(ServiceLocator.getDictionary(), dictionaryName).readDictionary();
        ServiceLocator.getModelConstructor().constructModel();
    }

    @Override
    public void start() {

        currentDictionaryName = Properties.DICTIONARY_FILE_NAME;
        ServiceLocator.createDictionary(currentDictionaryName);
        new DictionaryProcessor(ServiceLocator.getDictionary(), currentDictionaryName).readDictionary();
        ServiceLocator.getModelConstructor().constructModel();
        ServiceLocator.constructModelChangeChecker();
    }

    @Override
    public void stop() {
        wordQuery = null;
        new DictionaryProcessor(ServiceLocator.getDictionary()).saveDictionary();
    }


    public void saveState() {
        backup = ServiceLocator.getDictionary().clone();
    }

    public void printDiff() {
        List<Word> backupWords = backup.asList();
        StringBuilder diff = new StringBuilder();
        for (Word word : backupWords) {
            Word w = ServiceLocator.getDictionary().getWord(word.getWord());
            if (!w.toString().equals(word.toString())) {
                diff.append("before: ").append(word.toStringCustom()).append(" after: ").append(w.toStringCustom()).append("\n");
            }
        }
        System.out.println(diff);
    }
}
