package controller.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class WordsFilter {

    private List<Filter> filterList;

    public WordsFilter(){
        filterList = new ArrayList<>();
        FilterFactory factory = new FilterFactory();
        for(String mode : FilterFactory.modes){
            filterList.add(factory.createFilter(mode));
        }
    }

    public boolean filterWord(String word){
        boolean flag = true;
        for(Filter filter : filterList){
            flag &= filter.filterWord(word);
        }
        return flag;
    }
}
