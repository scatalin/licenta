package system;

import dictionary.Dictionary;

import java.util.List;

/**
 * Created by Catalin on 5/16/2015 .
 */

public interface AutoCompletionSystem {

    List<String> getWordCompletion(String prefix);

    void selectCompletionWord(String word);

    List<String> getFrazeCompletion(String word);

    void inputCharacter(Character character);

    void loadTextFilesInDictionary();

    void saveDictionary();

    void readDictionary(String dictionaryName);

    void loadCustomDictionary(Dictionary dictionary);

    void start();

    void stop();

    public void saveState();

    public void printDiff();
}



