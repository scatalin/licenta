package controller.filter;

/**
 * Created by Catalin on 5/11/2015 .
 */
public class LengthFilter implements Filter {

    @Override
    public boolean filterWord(String word) {
        return word.length() > 4;
    }
}
