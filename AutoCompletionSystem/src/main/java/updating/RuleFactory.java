package updating;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class RuleFactory {
    public static final String USER = "user";
    public static final String ACTUALITY = "actu";

    Rule createFilter(String mode){
        if(mode.equals(USER)){
            return new UserWordRule();
        }
        if(mode.equals(ACTUALITY)){
            return new ActualityRule();
        }
        return null;
    }
}
