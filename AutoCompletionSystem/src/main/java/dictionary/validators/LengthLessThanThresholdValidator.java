package dictionary.validators;

/**
 * Created by Catalin on 5/11/2015 .
 */
public class LengthLessThanThresholdValidator implements Validator {
    @Override
    public boolean isValid(String word) {
        //todo: parametrise this
        return word.length() > 4;
    }
}
