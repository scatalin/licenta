package dictionary.validators;

/**
 * Created by Catalin on 5/11/2015 .
 */
public class EmptyOrNullValidator implements Validator {
    @Override
    public boolean isValid(String word) {
        return !(word.isEmpty() || word.equals(" "));
    }
}
