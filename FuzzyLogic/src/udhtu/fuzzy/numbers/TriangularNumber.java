package udhtu.fuzzy.numbers;

import static udhtu.fuzzy.numbers.Constants.MAX_M_VALUE;

public class TriangularNumber extends FunctionalNumber {


    private double leftValue;
    private double middleValue;
    private double rightValue;

    public TriangularNumber(double minValue, double maxValue) {
        super(minValue, maxValue);
    }

    public void setValue(double leftValue, double  middleValue, double rightValue) {
        this.leftValue = leftValue;
        this.middleValue = middleValue;
        this.rightValue = rightValue;
    }

    @Override
    public double function(double x) {
        if(leftValue == minValue && middleValue == minValue && x < rightValue) return MAX_M_VALUE;

        if(x <= leftValue) return 0;
        if(leftValue <= x && x <= middleValue) return ((x - leftValue) / (middleValue - leftValue));
        if(middleValue <= x && x <= rightValue) return ((rightValue - x) / (rightValue - middleValue));
        if(rightValue <= x) return 0;
        throw new IllegalArgumentException();
    }
}