package dictionary;

import system.ServiceLocator;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Catalin on 6/18/2015 .
 */
public class ModelChangeChecker implements Observer {

    ModelConstructor constructor;

    public ModelChangeChecker(Observable o) {
        constructor = ServiceLocator.getModelConstructor();
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        constructor.constructModel();
    }
}
