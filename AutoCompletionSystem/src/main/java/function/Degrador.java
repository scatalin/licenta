package function;

import system.Properties;

/**
 * Created by Catalin on 6/22/2015 .
 */
public class Degrador {
    EulerDecay actualityDecay = new EulerDecay(Properties.DECAY_ALPHA_ACTUALITY);
    EulerDecay userDecay = new EulerDecay(Properties.DECAY_ALPHA_USER);

    public int degradeActuality(int initValue, double query, double word) {
        actualityDecay.setT((query - word) / Properties.DECAY_QUERIES_ACTUALITY);
        return (int) (actualityDecay.getValue() * (double) initValue);
    }

    public int degradeUser(int initValue, double query, double word) {
        userDecay.setT((query - word) / Properties.DECAY_QUERIES_USER);
        return (int) (userDecay.getValue() * (double) initValue);
    }
}
