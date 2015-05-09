package function;

/**
 * Created by Catalin on 5/9/2015 .
 */
public class AbstractExponential {

    double coeficient;
    double base;
    double exponent;

    public AbstractExponential(double coeficient, double base, double exponent) {
        this.coeficient = coeficient;
        this.base = base;
        this.exponent = exponent;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }

    public double calculateValue() {
        return coeficient * Math.pow(base, exponent);
    }
}
