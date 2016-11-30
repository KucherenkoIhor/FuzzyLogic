package udhtu.fuzzy.variables;

import udhtu.fuzzy.numbers.TriangularNumber;

/**
 * Created by igor on 30.11.16.
 */
public class FirstColumn {

    public static final double MIN_VALUE = 359.08;
    public static final double MAX_VALUE = 1628.55;

    public static final TriangularNumber LOW = new TriangularNumber(MIN_VALUE, MAX_VALUE);
    public static final TriangularNumber HIGH = new TriangularNumber(MIN_VALUE, MAX_VALUE);

    static {
        final int leftValue  = 0;
        final int topValue     = 20;
        final int rightValue = 50;
        LOW.setValue(leftValue, topValue, rightValue);
    }
    static {
        final int leftValue  = 0;
        final int topValue     = 20;
        final int rightValue = 50;
        HIGH.setValue(leftValue, topValue, rightValue);
    }

}
