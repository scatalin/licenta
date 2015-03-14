package algorithms.utils;

/**
 * Created by Catalin on 3/13/2015 .
 */
public abstract class AbstractPrettyPrinter implements PrettyPrinter {
    @Override
    public String prettyPrint() {
        initPrint();
        StringBuilder stringBuilder = new StringBuilder();
        constructString(stringBuilder);
        return stringBuilder.toString();
    }

    protected abstract void constructString(StringBuilder stringBuilder);

    protected abstract void initPrint();

}
