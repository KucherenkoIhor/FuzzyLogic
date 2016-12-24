package udhtu.fuzzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by igor on 04.12.16.
 */
public class LinguistikVar {

    public final double [] column;

    public final double min;

    public final double max;

    public static final int COLUMNS_FROM_GRAPH = 20;

    public final double[] edgesOfIntervals = new double[COLUMNS_FROM_GRAPH];

    public final int[] frequency = new int[COLUMNS_FROM_GRAPH];

    public final double normalizeFrequency [] = new double[frequency.length];

    public LinguistikVar(double[] column) {
        this.column = column;

        Arrays.sort(column);
        min = column[0];
        max = column[column.length - 1];

        for (int i = 1, j = 0; i <= COLUMNS_FROM_GRAPH; i ++, j ++) {
            edgesOfIntervals[j] = min + ((max - min) / COLUMNS_FROM_GRAPH) * i;
        }

        double prevEnd = 0;

        for (int i = 0; i < frequency.length; i++) {
            double start = prevEnd;
            double end = edgesOfIntervals[i];
            prevEnd = end;
            frequency[i] = getCountOfNumbersFromInterval(start, end, column);
        }


        int minFrequency = min(frequency);
        int maxFrequency = max(frequency);

        for (int i = 0; i < normalizeFrequency.length; i++) {
            normalizeFrequency[i] = (((double)frequency[i]) - ((double)minFrequency)) / (((double)maxFrequency) - ((double)minFrequency));
        }


       /* for (int i = 0; i < frequency.length; i++) {
            System.out.println(frequency[i]);
        }*/

       // System.out.println();

        int indexOfFirstTop = 0;
        int indexOfSecondTop = 0;
        double top1 = normalizeFrequency[indexOfFirstTop];
        double top2 = -1;
        for (int i = 0; i < normalizeFrequency.length; i++) {
            if(top1 < normalizeFrequency[i]) {
                top1 = normalizeFrequency[i];
                indexOfFirstTop = i;
            }
        }
        for (int i = 0; i < normalizeFrequency.length; i++) {
            if(i == indexOfFirstTop) continue;
            if(top2 == -1) {
                top2 = normalizeFrequency[i];
                continue;
            }
            if(top2 < normalizeFrequency[i]) {
                top2 = normalizeFrequency[i];
                indexOfSecondTop = i;
            }
        }
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
            //term.left = normalizeFrequency[0];
            term.leftValue = edgesOfIntervals[0];
            //term.right = normalizeFrequency[normalizeFrequency.length - 1];
            term.rightValue = edgesOfIntervals[normalizeFrequency.length - 1];
           // term.top = top1;
            term.topValue = edgesOfIntervals[indexOfFirstTop];
            if(indexOfFirstTop == 0) {
                term.leftValue = 0;
            }
            terms.add(term);
        } else {
            if(indexOfFirstTop < indexOfSecondTop) {
                Term term1 = new Term();
                //term1.left = normalizeFrequency[0];
                term1.leftValue = edgesOfIntervals[0];
                //term1.right = normalizeFrequency[indexOfLow1];
                term1.rightValue = edgesOfIntervals[indexOfLow1];
                //term1.top = top1;
                term1.topValue = edgesOfIntervals[indexOfFirstTop];

                if(indexOfFirstTop == 0) {
                    term1.leftValue = 0;
                }

                Term term2 = new Term();
                //term2.left = normalizeFrequency[indexOfLow1];
                term2.leftValue = edgesOfIntervals[indexOfLow1];
                //term2.right = normalizeFrequency[normalizeFrequency.length - 1];
                term2.rightValue = edgesOfIntervals[normalizeFrequency.length - 1];
                //term2.top = top2;
                term2.topValue = edgesOfIntervals[indexOfSecondTop];

                terms.add(term1);
                terms.add(term2);
            } else {
                Term term1 = new Term();
                //term1.left = normalizeFrequency[0];
                term1.leftValue = edgesOfIntervals[0];
                //term1.right = normalizeFrequency[indexOfLow2];
                term1.rightValue = edgesOfIntervals[indexOfLow2];
               /// term1.top = top2;
                term1.topValue = edgesOfIntervals[indexOfSecondTop];

                if(indexOfSecondTop == 0) {
                    term1.leftValue = 0;
                }

                Term term2 = new Term();
                //term2.left = normalizeFrequency[indexOfLow2];
                term2.leftValue = edgesOfIntervals[indexOfLow2];
               // term2.right = normalizeFrequency[normalizeFrequency.length - 1];
                term2.rightValue = edgesOfIntervals[normalizeFrequency.length - 1];
               // term2.top = top1;
                term2.topValue = edgesOfIntervals[indexOfFirstTop];
                terms.add(term1);
                terms.add(term2);
            }
        }

       /* System.out.println();
        terms.forEach( term -> {
            System.out.println(
                    "left: " + term.leftValue
                            + " top: " + term.topValue
                            + " right: " + term.rightValue
                            + " leftK: " + term.getLeftK()
                            + " leftB: " + term.getLeftB()
                            + " rightK: " + term.getRightK()
                            + " rightB: " + term.getRightB());
        });
        System.out.println();
*/

        //System.out.println("terms: ");
        terms.forEach(item -> {
          //  System.out.println("left " + item.left + " top " + item.top + " right " + item.right + " k: " + item.getLeftK() + " b: " + item.getLeftB());
        });


       // System.out.println();


    }

    public double getK(double value) {
        for(Term item: terms) {
            if(item.leftValue < value && value < item.rightValue) {
                if(value < item.topValue) {
                    return item.getLeftK();
                } else {
                    return item.getRightK();
                }
            }
        }
        return -100;
    }

    public double getB(double value) {
        for(Term item: terms) {
            if(item.leftValue < value && value < item.rightValue) {
                if(value < item.topValue) {
                    return item.getLeftB();
                } else {
                    return item.getRightB();
                }
            }
        }
        return -100;
    }

    public Term getTerm(double value) {
        for(Term item: terms) {
            if(item.leftValue < value && value < item.rightValue) {
                return item;
            }
        }
        return null;
    }

    public double getLeftK(double value) {
        for(Term item: terms) {
        //    System.out.println(item.leftValue + " " + item.rightValue);
            if(item.leftValue < value && value < item.rightValue) {
                return item.getLeftK();
            }
        }
        return -100;
    }

    public double getLeftB(double value) {
        for(Term item: terms) {
            if(item.leftValue < value && value < item.rightValue) {
                return item.getLeftB();
            }
        }
        return -100;
    }

    public double getRightK(double value) {
        for(Term item: terms) {
            if(item.leftValue < value && value < item.rightValue) {
                return item.getRightK();
            }
        }
        return -100;
    }

    public double getRightB(double value) {
        for(Term item: terms) {
            if(item.leftValue < value && value < item.rightValue) {
                return item.getRightB();
            }
        }
        return -100;
    }

    public boolean isValueBelongToThis(double value) {
        return value > min && value < max;
    }

    public final List<Term> terms = new ArrayList<>();

    public class Term {

        public double left = 0;
        public double leftValue;
        public double top = 1;
        public double topValue;
        public double right = 0;
        public double rightValue;

        //623.2 650.65

        public double getLeftK() {
            return (top - left) / (topValue - leftValue);
        } // 1 / 27 = 0.029

        public double getLeftB() {
            return left - ((leftValue*(top - left))/ (topValue - leftValue));
        }//-17.964

        public double getRightK() {
            return (top - right) / (topValue - rightValue);
        }

        public double getRightB() {
            return top -((rightValue * (top - right)) / (topValue - rightValue));
        }

        public double function(double x) {
            if(leftValue == min && topValue == min && x < rightValue) return 1;

            if(x <= leftValue) return 0;
            if(leftValue <= x && x <= topValue) return ((x - leftValue) / (topValue - leftValue));
            if(topValue <= x && x <= rightValue) return ((rightValue - x) / (rightValue - topValue));
            if(rightValue <= x) return 0;
            throw new IllegalArgumentException();
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
