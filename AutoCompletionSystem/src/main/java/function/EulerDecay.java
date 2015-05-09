package function;

/**
 * Created by Catalin on 5/9/2015 .
 */
public class EulerDecay extends AbstractExponential {

    private static final double euler = 2.71828d;
    private double alpha;
    private double t;

    public EulerDecay(double alpha) {
        this(1, euler, alpha);
    }

    private EulerDecay(double coeficient, double base, double alpha) {
        super(coeficient, base, alpha);
        this.alpha = alpha;
    }

    public double getValue(){
        super.setExponent(-alpha*t);
        return super.calculateValue();
    }

    public void setT(double t){
        this.t = t;
    }


}
