package algorithms.segmenttree.intern;

import system.Properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by gstan on 16.03.2015.
 */
public class SegmentTreeData {

    private static final String ALPHABET = Properties.ALPHABET;
    private final Map<String, Integer> data;
    private final int size;

    public SegmentTreeData(int size) {
        this.size = size;
        data = new HashMap<>();
        parseInterval();
    }

    public void parseInterval() {
        for (int i = 0; i < size; i++) {
            data.put(String.valueOf(ALPHABET.charAt(i)), i);
        }
    }

    public int getPosition(String s) {
        return data.get(s);
    }

    public Set<String> getAlphabet(){
        return data.keySet();
    }
}
