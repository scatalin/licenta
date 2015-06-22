package updating;

import dictionary.Dictionary;
import dictionary.inserting.UserWeightUpdate;
import system.ServiceLocator;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class Updater {

    private Rule userRule;
    private Rule actualityRule;

    private static Updater instance;

    public static Updater getInstance(){
        if(instance == null){
            instance = new Updater();
        }
        return instance;
    }

    public Updater(){
        RuleFactory factory = new RuleFactory();
        userRule = factory.createFilter(RuleFactory.USER);
        actualityRule = factory.createFilter(RuleFactory.ACTUALITY);
    }

    public int update(String word){
        Dictionary dictionary = ServiceLocator.getDictionary();
        dictionary.setUpdater(UserWeightUpdate.instance());
        int ceilingWeight = dictionary.getMaximumWeightForWord(word);
        dictionary.updateUserWord(word, userRule.applyRule(ceilingWeight), actualityRule.applyRule(ceilingWeight));
        return dictionary.getWord(word).getWeight();
    }
}
