package udhtu;

import javafx.event.ActionEvent;
import udhtu.fuzzy.LinguistikVar;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public void handlePreProcessButtonAction(ActionEvent actionEvent) {


    }

    private List<double[]> firstCluster = new ArrayList<>();
    private List<double[]> secondCluster = new ArrayList<>();
    private List<double[]> thirdCluster = new ArrayList<>();
    private List<double[]> fourthCluster = new ArrayList<>();
    private List<double[]> fifthCluster = new ArrayList<>();

    public void handleClusterizationButtonAction(ActionEvent actionEvent) {
        double[][] samples = ExcelParser.parse();
        double[][] original = new double[samples.length][4];
        for (int i = 0; i < samples.length; i++) {
            double[] copy = new double[4];
            copy[0] = samples[i][0];
            copy[1] = samples[i][1];
            copy[2] = samples[i][2];
            copy[3] = samples[i][3];
            original[i] = copy;
        }


        double[][] minMax = scale(samples);
        int coordinatesCount = 4;
        int clusterCount = 5;
        double norma = 0.9;
        double wMin = 0.09;
        double wMax = 0.6;
        double [][] weightMatrix = new double[clusterCount][coordinatesCount];
        for (int i = 0; i < coordinatesCount; i++) {
            for (int j = 0; j < clusterCount; j++) {
                weightMatrix[j][i] = wMin + norma * (wMax -wMin);
            }
        }

        double kf = 1.0;

        int [] nObr = new int[samples.length];

        while(kf >= 0.1) {

            double[] distance = new double[clusterCount];
            for (int i = 0; i < samples.length; i++) {
                //1. Извлечение образца из матрицы образцов
                double[] sample = samples[i];

                //2. Цикл по номерам кластеров
                for (int j = 0; j < clusterCount; j++) {
                    distance[j] = 0.0;
                    //2.1. Вычисление меры приближения
                    for (int k = 0; k < coordinatesCount; k++) {
                        distance[j] = distance[j] + Math.pow((sample[k] - weightMatrix[j][k]), 2);
                    }

                }

                //2.2. Определение номера кластера
                double distanceMin = minFromArray(distance);
                int index = 0;
                for (int j = 0; j < clusterCount; j++) {
                    if (distance[j] == distanceMin) {
                        index = j;
                    }
                }

                nObr[i] = index;

                //3. Пересчет координат центра кластера
                for (int j = 0; j < coordinatesCount; j++) {
                    weightMatrix[index][j] = weightMatrix[index][j] + kf * (sample[j] - weightMatrix[index][j]);
                }
            }

            kf = kf * 0.5;
        }

        for (int i = 0; i < nObr.length; i++) {
            switch (nObr[i]) {
                case 0: {
                    firstCluster.add(original[i]);
                    break;
                }
                case 1: {
                    secondCluster.add(original[i]);
                    break;
                }
                case 2: {
                    thirdCluster.add(original[i]);
                    break;
                }
                case 3: {
                    fourthCluster.add(original[i]);
                    break;
                }
                case 4: {
                    fifthCluster.add(original[i]);
                    break;
                }
            }
        }

        //функции принадлежности
        double[] firstClusterAColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterAColumn[i] = firstCluster.get(i)[0];
        }
        LinguistikVar firstClusterAVar = new LinguistikVar(firstClusterAColumn);
        double[] firstClusterEColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterEColumn[i] = firstCluster.get(i)[1];
        }
        LinguistikVar firstClusterEVar = new LinguistikVar(firstClusterEColumn);
        double[] firstClusterFColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterFColumn[i] = firstCluster.get(i)[2];
        }
        LinguistikVar firstClusterFVar = new LinguistikVar(firstClusterFColumn);
        double[] firstClusterHColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterHColumn[i] = firstCluster.get(i)[3];
        }
        LinguistikVar firstClusterHVar = new LinguistikVar(firstClusterHColumn);

        //2
        double[] secondClusterAColumn = new double[secondCluster.size()];
        for (int i = 0; i < secondCluster.size(); i++) {
            secondClusterAColumn[i] = secondCluster.get(i)[0];
        }
        LinguistikVar secondClusterAVar = new LinguistikVar(secondClusterAColumn);
        double[] secondClusterEColumn = new double[secondCluster.size()];
        for (int i = 0; i < secondCluster.size(); i++) {
            secondClusterEColumn[i] = secondCluster.get(i)[1];
        }
        LinguistikVar secondClusterEVar = new LinguistikVar(secondClusterEColumn);
        double[] secondClusterFColumn = new double[secondCluster.size()];
        for (int i = 0; i < secondCluster.size(); i++) {
            secondClusterFColumn[i] = secondCluster.get(i)[2];
        }
        LinguistikVar secondClusterFVar = new LinguistikVar(secondClusterFColumn);
        double[] secondClusterHColumn = new double[secondCluster.size()];
        for (int i = 0; i < secondCluster.size(); i++) {
            secondClusterHColumn[i] = secondCluster.get(i)[3];
        }
        LinguistikVar secondClusterHVar = new LinguistikVar(secondClusterHColumn);


        //3
        double[] thirdClusterAColumn = new double[thirdCluster.size()];
        for (int i = 0; i < thirdCluster.size(); i++) {
            thirdClusterAColumn[i] = thirdCluster.get(i)[0];
        }
        LinguistikVar thirdClusterAVar = new LinguistikVar(thirdClusterAColumn);
        double[] thirdClusterEColumn = new double[thirdCluster.size()];
        for (int i = 0; i < thirdCluster.size(); i++) {
            thirdClusterEColumn[i] = thirdCluster.get(i)[1];
        }
        LinguistikVar thirdClusterEVar = new LinguistikVar(thirdClusterEColumn);
        double[] thirdClusterFColumn = new double[thirdCluster.size()];
        for (int i = 0; i < thirdCluster.size(); i++) {
            thirdClusterFColumn[i] = thirdCluster.get(i)[2];
        }
        LinguistikVar thirdClusterFVar = new LinguistikVar(thirdClusterFColumn);
        double[] thirdClusterHColumn = new double[thirdCluster.size()];
        for (int i = 0; i < thirdCluster.size(); i++) {
            thirdClusterHColumn[i] = thirdCluster.get(i)[3];
        }
        LinguistikVar thirdClusterHVar = new LinguistikVar(thirdClusterHColumn);


        //4
        double[] fourthClusterAColumn = new double[fourthCluster.size()];
        for (int i = 0; i < fourthCluster.size(); i++) {
            fourthClusterAColumn[i] = fourthCluster.get(i)[0];
        }
        LinguistikVar fourthClusterAVar = new LinguistikVar(fourthClusterAColumn);
        double[] fourthClusterEColumn = new double[fourthCluster.size()];
        for (int i = 0; i < fourthCluster.size(); i++) {
            fourthClusterEColumn[i] = fourthCluster.get(i)[1];
        }
        LinguistikVar fourthClusterEVar = new LinguistikVar(fourthClusterEColumn);
        double[] fourthClusterFColumn = new double[fourthCluster.size()];
        for (int i = 0; i < fourthCluster.size(); i++) {
            fourthClusterFColumn[i] = fourthCluster.get(i)[2];
        }
        LinguistikVar fourthClusterFVar = new LinguistikVar(fourthClusterFColumn);
        double[] fourthClusterHColumn = new double[fourthCluster.size()];
        for (int i = 0; i < fourthCluster.size(); i++) {
            fourthClusterHColumn[i] = fourthCluster.get(i)[3];
        }
        LinguistikVar fourthClusterHVar = new LinguistikVar(fourthClusterHColumn);

        //5
        double[] fifthClusterAColumn = new double[fifthCluster.size()];
        for (int i = 0; i < fifthCluster.size(); i++) {
            fifthClusterAColumn[i] = fifthCluster.get(i)[0];
        }
        LinguistikVar fifthClusterAVar = new LinguistikVar(fifthClusterAColumn);
        double[] fifthClusterEColumn = new double[fifthCluster.size()];
        for (int i = 0; i < fifthCluster.size(); i++) {
            fifthClusterEColumn[i] = fifthCluster.get(i)[1];
        }
        LinguistikVar fifthClusterEVar = new LinguistikVar(fifthClusterEColumn);
        double[] fifthClusterFColumn = new double[fifthCluster.size()];
        for (int i = 0; i < fifthCluster.size(); i++) {
            fifthClusterFColumn[i] = fifthCluster.get(i)[2];
        }
        LinguistikVar fifthClusterFVar = new LinguistikVar(fifthClusterFColumn);
        double[] fifthClusterHColumn = new double[fifthCluster.size()];
        for (int i = 0; i < fifthCluster.size(); i++) {
            fifthClusterHColumn[i] = fifthCluster.get(i)[3];
        }
        LinguistikVar fifthClusterHVar = new LinguistikVar(fifthClusterHColumn);


        //   SigmaVariableGenerator.generate(firstClusterAColumn);


/*
        System.out.println("Первый кластер");
        System.out.println("Образцов: " + firstCluster.size());
        firstCluster.forEach(item -> {
            System.out.println(Arrays.toString(item));
        });
        System.out.println();
        System.out.println();
        System.out.println("Второй кластер");
        System.out.println("Образцов: " + secondCluster.size());
        secondCluster.forEach(item -> {
            System.out.println(Arrays.toString(item));
        });
        System.out.println();
        System.out.println();
        System.out.println("Третий кластер");
        System.out.println("Образцов: " + thirdCluster.size());
        thirdCluster.forEach(item -> {
            System.out.println(Arrays.toString(item));
        });
        System.out.println();
        System.out.println();
        System.out.println("Четвертый кластер");
        System.out.println("Образцов: " + fourthCluster.size());
        fourthCluster.forEach(item -> {
            System.out.println(Arrays.toString(item));
        });
        System.out.println();
        System.out.println();
        System.out.println("Пятый кластер");
        System.out.println("Образцов: " + fifthCluster.size());
        fifthCluster.forEach(item -> {
            System.out.println(Arrays.toString(item));
        });
*/

    }

    public void handleBuildFunctionButtonAction(ActionEvent actionEvent) {

    }

    public void handleKnowledgeDataBaseButtonAction(ActionEvent actionEvent) {

    }

    public void handleFuzzyConclusionButtonAction(ActionEvent actionEvent) {

    }

    private double minFromArray(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private double [][] scale(double [][] samples) {
        double [][] minMaxMatrix = new double[4][2];
        for (int i = 0; i < minMaxMatrix.length; i++) {
            minMaxMatrix[i][0] = findMinFromColumn(samples, i);
            minMaxMatrix[i][1] = findMaxFromColumn(samples, i);
        }
        for(int i = 0; i < samples.length; i ++ ) {
            for(int j = 0; j < samples[i].length; j ++ ) {
                samples[i][j] = (samples[i][j] - minMaxMatrix[j][0]) / (minMaxMatrix[j][1] - minMaxMatrix[j][0]);
            }
        }
        return minMaxMatrix;
    }

    private double findMinFromColumn(double [][] samples, int column) {
        double min = samples[0][column];
        for (int i = 1; i < samples.length; i++) {
            if(samples[i][column] < min) {
                min = samples[i][column];
            }
        }
        return min;
    }

    private double findMaxFromColumn(double [][] samples, int column) {
        double max = samples[0][column];
        for (int i = 1; i < samples.length; i++) {
            if(samples[i][column] > max) {
                max = samples[i][column];
            }
        }
        return max;
    }

}
