import dictionary.Dictionary;

import java.util.List;

/**
 * Created by Catalin on 5/16/2015 .
 */
public interface AutoCompletionSystem {

    void loadDictionary();

    void readDictionary();

    void loadCustomDictionary(Dictionary dictionary);

    void constructSearchTree();

    void startCompletion();

    List<String> getCompletion(String prefix);

    void selectWord(String word);
}
