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
    private final Map<Character, Integer> data;

    public Data() {
        data = new HashMap<>();
        parseInterval();
    }

    public void parseInterval() {
        for (int i = 0; i < ALPHABET.length(); i++) {
            data.put(ALPHABET.charAt(i), i);
        }
    }

    public int getPosition(String s) {
        return data.get(s.charAt(0));
    }

    public Set<Character> getAlphabet() {
        return data.keySet();
    }

    public int getSize() {
        return ALPHABET.length();
    }
}
