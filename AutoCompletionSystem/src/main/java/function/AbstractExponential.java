package function;

/**
 * Created by Catalin on 5/9/2015 .
 */
public class AbstractExponential {

    double coefficient;
    double base;
    double exponent;

    public AbstractExponential(double coefficient, double base, double exponent) {
        this.coefficient = coefficient;
        this.base = base;
        this.exponent = exponent;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }

    public double calculateValue() {
        return coefficient * Math.pow(base, exponent);
    }
}
