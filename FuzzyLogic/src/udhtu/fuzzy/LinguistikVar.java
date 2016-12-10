package udhtu.fuzzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by igor on 04.12.16.
 */
public class LinguistikVar {

    public final double [] column;

    public final double ks [] = new double[2];

    public final double bs [] = new double[2];

    public LinguistikVar(double[] column) {
        this.column = column;

        Arrays.sort(column);
        double min = column[0];
        double max = column[column.length - 1];
        int columnsFromGraph = 10;

        double[] edgesOfIntervals = new double[columnsFromGraph];
        for (int i = 1, j = 0; i <= columnsFromGraph; i ++, j ++) {
            edgesOfIntervals[j] = min + ((max - min) / columnsFromGraph) * i;
        }

        int[] frequency = new int[columnsFromGraph];
        double prevEnd = 0;

        for (int i = 0; i < frequency.length; i++) {
            double start = prevEnd;
            double end = edgesOfIntervals[i];
            prevEnd = end;
            frequency[i] = getCountOfNumbersFromInterval(start, end, column);
        }


        int minFrequency = min(frequency);
        int maxFrequency = max(frequency);

        double normalizeFrequency [] = new double[frequency.length];

        for (int i = 0; i < normalizeFrequency.length; i++) {
            normalizeFrequency[i] = (((double)frequency[i]) - ((double)minFrequency)) / (((double)maxFrequency) - ((double)minFrequency));
        }


       /* for (int i = 0; i < frequency.length; i++) {
            System.out.println(frequency[i]);
        }*/

        System.out.println();

        int indexOfFirstTop = 0;
        int indexOfSecondTop = 0;
        double top1 = normalizeFrequency[indexOfFirstTop];
        double top2 = normalizeFrequency[indexOfSecondTop];
        for (int i = 0; i < normalizeFrequency.length; i++) {
            if(top1 < normalizeFrequency[i]) {
                top1 = normalizeFrequency[i];
                indexOfFirstTop = i;
            }
        }
        for (int i = 0; i < normalizeFrequency.length; i++) {
            if(i == indexOfFirstTop) continue;
            if(top2 < normalizeFrequency[i]) {
                top2 = normalizeFrequency[i];
                indexOfSecondTop = i;
            }
        }
        System.out.println("top1: " + top1);
        System.out.println("top2: " + top2);
        int indexOfLow1 = -1;
        int indexOfLow2 = -1;
        double low1 = 1;
        double low2 = 1;
        if(indexOfFirstTop < indexOfSecondTop) {
            for(int i = indexOfFirstTop + 1; i < indexOfSecondTop; i ++) {
                if(top1 - normalizeFrequency[i] > 0.5 && top2 - normalizeFrequency[i] > 0.35) {
                    if(normalizeFrequency[i] < low1) {
                        indexOfLow1 = i;
                        low1 = normalizeFrequency[i];
                    }
                }
            }
        } else {
            for(int i = indexOfSecondTop + 1; i < indexOfFirstTop; i ++) {
                if(top1 - normalizeFrequency[i] > 0.5 && top2 - normalizeFrequency[i] > 0.35) {
                    if(normalizeFrequency[i] < low2) {
                        indexOfLow2 = i;
                        low2 = normalizeFrequency[i];
                    }
                }
            }
        }

        boolean isTwoTerms = false;

        if(indexOfLow1 != -1 || indexOfLow2 != -1) {
            isTwoTerms = true;
        }

        if(!isTwoTerms) {
            Term term = new Term();
            term.left = normalizeFrequency[0];
            term.leftValue = edgesOfIntervals[0];
            term.right = normalizeFrequency[normalizeFrequency.length - 1];
            term.rightValue = edgesOfIntervals[normalizeFrequency.length - 1];
            term.top = top1;
            term.topValue = edgesOfIntervals[indexOfFirstTop];
            terms.add(term);
        } else {
            if(indexOfFirstTop < indexOfSecondTop) {
                Term term1 = new Term();
                term1.left = normalizeFrequency[0];
                term1.leftValue = edgesOfIntervals[0];
                term1.right = normalizeFrequency[indexOfLow1];
                term1.rightValue = edgesOfIntervals[indexOfLow1];
                term1.top = top1;
                term1.topValue = edgesOfIntervals[indexOfFirstTop];

                Term term2 = new Term();
                term2.left = normalizeFrequency[indexOfLow1];
                term2.leftValue = edgesOfIntervals[indexOfLow1];
                term2.right = normalizeFrequency[normalizeFrequency.length - 1];
                term2.rightValue = edgesOfIntervals[normalizeFrequency.length - 1];
                term2.top = top2;
                term2.topValue = edgesOfIntervals[indexOfSecondTop];
                terms.add(term1);
                terms.add(term2);
            } else {
                Term term1 = new Term();
                term1.left = normalizeFrequency[0];
                term1.leftValue = edgesOfIntervals[0];
                term1.right = normalizeFrequency[indexOfLow2];
                term1.rightValue = edgesOfIntervals[indexOfLow2];
                term1.top = top2;
                term1.topValue = edgesOfIntervals[indexOfSecondTop];

                Term term2 = new Term();
                term2.left = normalizeFrequency[indexOfLow2];
                term2.leftValue = edgesOfIntervals[indexOfLow2];
                term2.right = normalizeFrequency[normalizeFrequency.length - 1];
                term2.topValue = edgesOfIntervals[normalizeFrequency.length - 1];
                term2.top = top1;
                term2.topValue = edgesOfIntervals[indexOfFirstTop];
                terms.add(term1);
                terms.add(term2);
            }
        }

        System.out.println("terms: ");
        terms.forEach(item -> {
            System.out.println("left " + item.left + " top " + item.top + " right " + item.right + " k: " + item.getK() + " b: " + item.getB());
        });


       // System.out.println("low1: " + low1);
       // System.out.println("low2: " + low2);




       /* for (int i = 0; i < normalizeFrequency.length; i++) {
            System.out.println(normalizeFrequency[i] + " ||| " + edgesOfIntervals[i]);
            if(ks[0] == 0.0) {
                if (normalizeFrequency[i] == 1.0) {
                    ks[0] = (normalizeFrequency[i] - normalizeFrequency[0]) / (edgesOfIntervals[i] - edgesOfIntervals[0]);
                    bs[0] = normalizeFrequency[0] - ((edgesOfIntervals[i]*(normalizeFrequency[i] - normalizeFrequency[0]))/ (edgesOfIntervals[i] - edgesOfIntervals[0]));
                }
            }
        }

        System.out.println("k: " + ks[0]);
        System.out.println("b: " + bs[0]);**/

        System.out.println();


    }

    public List<Term> terms = new ArrayList<>();

    public class Term {

        double left;
        double leftValue;
        double top;
        double topValue;
        double right;
        double rightValue;

        public double getK() {
            return (top - left) / (topValue - leftValue);
        }

        public double getB() {
            return left - ((topValue*(top - left))/ (topValue - leftValue));
        }

    }

    private int min(int [] array) {
        int min = array[0];
        for (int anArray : array) {
            if (min > anArray) {
                min = anArray;
            }
        }
        return min;
    }

    private int max(int [] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if(max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    private int getCountOfNumbersFromInterval(double start, double end, double[] data) {
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            double item = data[i];
            if(item > start && item <= end) {
                count ++;
            }
        }
        return count;
    }

}
