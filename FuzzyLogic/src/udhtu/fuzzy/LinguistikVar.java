package udhtu.fuzzy;

import java.util.Arrays;

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

        double prevMu = 0.0;
        for (int i = 0; i < normalizeFrequency.length; i++) {
            System.out.println(normalizeFrequency[i] + " ||| " + edgesOfIntervals[i]);


            if(ks[0] == 0.0) {
                if (normalizeFrequency[i] == 1.0) {
                    ks[0] = (normalizeFrequency[i] - normalizeFrequency[0]) / (edgesOfIntervals[i] - edgesOfIntervals[0]);
                    bs[0] = normalizeFrequency[0] - ((edgesOfIntervals[i]*(normalizeFrequency[i] - normalizeFrequency[0]))/ (edgesOfIntervals[i] - edgesOfIntervals[0]));
                }
            }

        }

        System.out.println("k: " + ks[0]);
        System.out.println("b: " + bs[0]);

        System.out.println();


    }

    private int min(int [] array) {
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if(min > array[i]) {
                min = array[i];
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
