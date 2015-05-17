package algorithms;

import system.Properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by gstan on 16.03.2015.
 */
public class Data {

    private static final String ALPHABET = Properties.ALPHABET;
    private final Map<String, Integer> data;

    public Data() {
        data = new HashMap<>();
        parseInterval();
    }

    public void parseInterval() {
        for (int i = 0; i < ALPHABET.length(); i++) {
            data.put(String.valueOf(ALPHABET.charAt(i)), i);
        }
    }

    public int getPosition(String s) {
        return data.get(s);
    }

    public Set<String> getAlphabet() {
        return data.keySet();
    }

    public int getSize() {
        return ALPHABET.length();
    }
}
