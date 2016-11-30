package udhtu.fuzzy.numbers;

import static udhtu.fuzzy.numbers.Constants.MAX_M_VALUE;
import static udhtu.fuzzy.numbers.Constants.MIN_M_VALUE;

public class TrapezoidalNumber extends FunctionalNumber {

    private double leftBottomValue;
    private double leftTopValue;
    private double rightBottomValue;
    private double rightTopValue;

    public TrapezoidalNumber(double minValue, double maxValue) {
        super(minValue, maxValue);
    }

    public void setValue(double leftBottomValue, double leftTopValue, double rightBottomValue, double rightTopValue) {
        this.leftBottomValue  = leftBottomValue;
        this.leftTopValue     = leftTopValue;
        this.rightBottomValue = rightBottomValue;
        this.rightTopValue    = rightTopValue;
    }

    @Override
    public double function(double x) {

        if(leftTopValue == minValue && leftBottomValue == minValue && x < rightTopValue) return MAX_M_VALUE;

        if(x <= leftBottomValue) return 0;

        if(leftBottomValue <= x && x <= leftTopValue) {
            double denominator = leftTopValue - leftBottomValue;
            if(denominator == 0) return MIN_M_VALUE;
            return ((x - leftBottomValue) / denominator);
        }

        if(leftTopValue <= x && x <= rightTopValue) {
            return MAX_M_VALUE;
        }

        if(rightTopValue <= x && x <= rightBottomValue) {
            double denominator = rightBottomValue - rightTopValue;
            if(denominator == 0) return MAX_M_VALUE;
            return ((rightBottomValue - x) / denominator);
        }

        if(x >= rightBottomValue) return 0;

        throw new IllegalArgumentException();
    }
}