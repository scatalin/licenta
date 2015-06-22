package controller.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Catalin on 5/11/2015 .
 */
public class StopWordsFilter implements Filter {

    private Map<String, Integer> stopWords;

    public StopWordsFilter(List<String> stopWords) {
        this.stopWords = new HashMap<>();
        for (String s : stopWords) {
            this.stopWords.put(s, 0);
        }
    }

    @Override
    public boolean filterWord(String word) {
        Integer result = stopWords.get(word);
        return (result == null);
    }
}
