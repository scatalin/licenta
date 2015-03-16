package algorithms.segmenttree.intern;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gstan on 16.03.2015.
 */
public class SegmentTreeData {

    private Map<String, Integer> data;
    private static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private int size;

    public SegmentTreeData(int size) {
        this.size = size;
        data = new HashMap<String, Integer>();
    }

    public void parseInterval() {
        for(int i = 0; i < size; i++){
            data.put(String.valueOf(ALPHABET.charAt(i)),i);
        }
    }

    public int getInterval(String s){
        return data.get(s);
    }
}
