package controller.filter;

import input.StopWordsProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class FilterFactory {

    public static final String LENGTH = "length";
    public static final String STOP_WORDS = "stop_words";
    public static List<String> modes = new ArrayList<>();

    static {
        modes.add(LENGTH);
        modes.add(STOP_WORDS);
    }

    Filter createFilter(String mode){
        if(mode.equals(LENGTH)){
            return new LengthFilter();
        }
        if(mode.equals(STOP_WORDS)){
            StopWordsProcessor processor = new StopWordsProcessor();
            return new StopWordsFilter(processor.readFile());
        }
        return null;
    }
}
