package udhtu;

import javafx.event.ActionEvent;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import udhtu.fuzzy.LinguistikVar;

import java.io.File;
import java.io.FileInputStream;
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

        List<double[]> firstCluster = getFirstCluster();
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


        double aValue = 582.03391;
        double eValue = 0.08678;
        double fValue = 1.50268;

       // boolean isBelongToFirstCluster =
       //         firstClusterAVar.isValueBelongToThis(aValue)
       //                 && firstClusterEVar.isValueBelongToThis(eValue)
       //                 && firstClusterFVar.isValueBelongToThis(fValue);

        //System.out.println(isBelongToFirstCluster);

       // if(isBelongToFirstCluster) {
            double aK = firstClusterAVar.getK(aValue);
            double eK = firstClusterEVar.getK(eValue);
            double fK = firstClusterFVar.getK(fValue);

            double aB = firstClusterAVar.getB(aValue);
            double eB = firstClusterEVar.getB(eValue);
            double fB = firstClusterFVar.getB(fValue);

            double muA = aK * aValue + aB;
            double muE = eK * eValue + eB;
            double muF = fK * fValue + fB;

            double minMu = minFromArray(new double[]{muA, muE, muF});
            System.out.println("1 кластер мин мю " + minMu);
//        }


        /*double[][] samples = ExcelParser.parse();
        double[][] original = new double[samples.length][4];
        for (int i = 0; i < samples.length; i++) {
            double[] copy = new double[4];
            copy[0] = samples[i][0];
            copy[1] = samples[i][1];
            copy[2] = samples[i][2];
            copy[3] = samples[i][3];
            original[i] = copy;
        }

        scale(samples);

       /// System.out.println(Arrays.deepToString(samples));

        int coordinatesCount = 4;
        int clusterCount = 5;
        //double norma = 0.5;
        double wMin = -0.5;
        double wMax = 0.5;
        double [][] weightMatrix = new double[clusterCount][coordinatesCount];
        for (int i = 0; i < coordinatesCount; i++) {
            for (int j = 0; j < clusterCount; j++) {
                double norma = 2.7 + (Math.random() * (3.14 - 2.7));
                weightMatrix[j][i] = wMin + norma * (wMax - wMin);
            }
        }


        double kf = 0.5;

        int [] nObr = new int[samples.length];

        while(kf >= 0.1) {

            for (int i = 0; i < samples.length; i++) {
                //1. Извлечение образца из матрицы образцов
                double[] sample = samples[i];

                //2. Цикл по номерам кластеров
                double[] distance = new double[clusterCount];
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
         //           firstCluster.add(original[i]);
                    break;
                }
                case 1: {
      //              secondCluster.add(original[i]);
                    break;
                }
                case 2: {
      //              thirdCluster.add(original[i]);
                    break;
                }
                case 3: {
    //                fourthCluster.add(original[i]);
                    break;
                }
                case 4: {
      ///              fifthCluster.add(original[i]);
                    break;
                }
            }
        }

        firstCluster.forEach(item ->  {
            System.out.println(item[0]);
        });

      /*  System.out.println(firstCluster.size());
        System.out.println(secondCluster.size());
        System.out.println(thirdCluster.size());
        System.out.println(fourthCluster.size());
        System.out.println(fifthCluster.size());*/



/*        //функции принадлежности
        double[] firstClusterAColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterAColumn[i] = firstCluster.get(i)[0];
        }
        LinguistikVar firstClusterAVar = new LinguistikVar(firstClusterAColumn);


       /* for(int i = 0; i < firstClusterAVar.normalizeFrequency.length; i ++) {
            System.out.println(String.valueOf(firstClusterAVar.normalizeFrequency[i]).replace(".", ","));
        }*/


  /*      double[] firstClusterEColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterEColumn[i] = firstCluster.get(i)[1];
        }
        LinguistikVar firstClusterEVar = new LinguistikVar(firstClusterEColumn);


       /* for(int i = 0; i < firstClusterEVar.normalizeFrequency.length; i ++) {
            System.out.println(String.valueOf(firstClusterEVar.normalizeFrequency[i]).replace(".", ","));
        }

        firstClusterEVar.terms.forEach( term -> {
            System.out.println(
                    "left: " + term.leftValue
                            + " top: " + term.topValue
                            + " right: " + term.rightValue
                            + " leftK: " + term.getLeftK()
                            + " leftB: " + term.getLeftB()
                            + " rightK: " + term.getRightK()
                            + " rightB: " + term.getRightB());
        });*/

     /*   double[] firstClusterFColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterFColumn[i] = firstCluster.get(i)[2];
        }
        LinguistikVar firstClusterFVar = new LinguistikVar(firstClusterFColumn);
        double[] firstClusterHColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterHColumn[i] = firstCluster.get(i)[3];
        }
        LinguistikVar firstClusterHVar = new LinguistikVar(firstClusterHColumn);


      /*  final int[] g = {1};
        firstClusterAVar.terms.forEach(item -> {
            System.out.println("первая переменная  " + " терм: " + g[0] + " mu left: " + item.left + " value left: " + item.leftValue + " mu top: " + item.top + " value top: " + item.topValue + " mu right: " + item.right + " value right: " + item.rightValue + " k: " + item.getLeftK() + " b: " + item.getLeftB());
            g[0]++;
        });
        System.out.println();
        System.out.println();
        System.out.println();
        g[0] = 1;
        firstClusterEVar.terms.forEach(item -> {
            System.out.println("вторая переменная  " + " терм: " + g[0] + " mu left: " + item.left + " value left: " + item.leftValue + " mu top: " + item.top + " value top: " + item.topValue + " mu right: " + item.right + " value right: " + item.rightValue + " k: " + item.getLeftK() + " b: " + item.getLeftB());
            g[0]++;
        });
        System.out.println();
        System.out.println();
        System.out.println();
        g[0] = 1;
        firstClusterFVar.terms.forEach(item -> {
            System.out.println("третья переменная  " + " терм: " + g[0] + " mu left: " + item.left + " value left: " + item.leftValue + " mu top: " + item.top + " value top: " + item.topValue + " mu right: " + item.right + " value right: " + item.rightValue + " k: " + item.getLeftK() + " b: " + item.getLeftB());
            g[0]++;
        });
        System.out.println();
        System.out.println();
        System.out.println();
        g[0] = 1;
        firstClusterHVar.terms.forEach(item -> {
            System.out.println("четвертая переменная  " + " терм: " + g[0] + " mu left: " + item.left + " value left: " + item.leftValue + " mu top: " + item.top + " value top: " + item.topValue + " mu right: " + item.right + " value right: " + item.rightValue + " k: " + item.getLeftK() + " b: " + item.getLeftB());
            g[0]++;
        });*/


        //2
       /* double[] secondClusterAColumn = new double[secondCluster.size()];
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


       // System.out.println(Arrays.toString(firstCluster.get(0)));
        //[1250.68344, 0.02224, 1.88261, 3.7255654803350113]

        double aValue = 1250.68344;
        double eValue = 0.02224;
        double fValue = 1.88261;

        boolean isBelongToFirstCluster =
                        firstClusterAVar.isValueBelongToThis(aValue)
                        && firstClusterEVar.isValueBelongToThis(eValue)
                        && firstClusterFVar.isValueBelongToThis(fValue);

        boolean isBelongToSecondCluster =
                secondClusterAVar.isValueBelongToThis(aValue)
                        && secondClusterEVar.isValueBelongToThis(eValue)
                        && secondClusterFVar.isValueBelongToThis(fValue);

        boolean isBelongToThirdCluster =
                thirdClusterAVar.isValueBelongToThis(aValue)
                        && thirdClusterEVar.isValueBelongToThis(eValue)
                        && thirdClusterFVar.isValueBelongToThis(fValue);

        boolean isBelongToFourthCluster =
                fourthClusterAVar.isValueBelongToThis(aValue)
                        && fourthClusterEVar.isValueBelongToThis(eValue)
                        && fourthClusterFVar.isValueBelongToThis(fValue);

        boolean isBelongToFifthCluster =
                fifthClusterAVar.isValueBelongToThis(aValue)
                        && fifthClusterEVar.isValueBelongToThis(eValue)
                        && fifthClusterFVar.isValueBelongToThis(fValue);


        /*System.out.println(isBelongToFirstCluster);
        System.out.println(isBelongToSecondCluster);
        System.out.println(isBelongToThirdCluster);
        System.out.println(isBelongToFourthCluster);
        System.out.println(isBelongToFifthCluster);*/

        /*if(isBelongToFirstCluster) {
            double aK = firstClusterAVar.getK(aValue);
            double eK = firstClusterEVar.getK(eValue);
            double fK = firstClusterFVar.getK(fValue);

            double aB = firstClusterAVar.getB(aValue);
            double eB = firstClusterEVar.getB(eValue);
            double fB = firstClusterFVar.getB(fValue);

            double muA = aK * aValue + aB;
            double muE = eK * eValue + eB;
            double muF = fK * fValue + fB;

            double minMu = minFromArray(new double[]{muA, muE, muF});
            System.out.println("1 кластер мин мю " + minMu);
        } else if(isBelongToSecondCluster) {
            double aK = secondClusterAVar.getK(aValue);
            double eK = secondClusterEVar.getK(eValue);
            double fK = secondClusterFVar.getK(fValue);

            double aB = secondClusterAVar.getB(aValue);
            double eB = secondClusterEVar.getB(eValue);
            double fB = secondClusterFVar.getB(fValue);

            double muA = aK * aValue + aB;
            double muE = eK * eValue + eB;
            double muF = fK * fValue + fB;

            double minMu = minFromArray(new double[]{muA, muE, muF});
  //          System.out.println("2 кластер мин мю " + minMu);

        } else if(isBelongToThirdCluster) {
            double aK = thirdClusterAVar.getK(aValue);
            double eK = thirdClusterEVar.getK(eValue);
            double fK = thirdClusterFVar.getK(fValue);

            double aB = thirdClusterAVar.getB(aValue);
            double eB = thirdClusterEVar.getB(eValue);
            double fB = thirdClusterFVar.getB(fValue);

            double muA = aK * aValue + aB;
            double muE = eK * eValue + eB;
            double muF = fK * fValue + fB;

            double minMu = minFromArray(new double[]{muA, muE, muF});
    //        System.out.println("3 кластер мин мю " + minMu);
        } else if(isBelongToFourthCluster) {
            double aK = fourthClusterAVar.getK(aValue);
            double eK = fourthClusterEVar.getK(eValue);
            double fK = fourthClusterFVar.getK(fValue);

            double aB = fourthClusterAVar.getB(aValue);
            double eB = fourthClusterEVar.getB(eValue);
            double fB = fourthClusterFVar.getB(fValue);

            double muA = aK * aValue + aB;
            double muE = eK * eValue + eB;
            double muF = fK * fValue + fB;

            double minMu = maxFromArray(new double[]{muA, muE, muF});
    //        System.out.println("4 кластер мин мю " + minMu);
        } else if(isBelongToFifthCluster) {
            double aK = fifthClusterAVar.getK(aValue);
            double eK = fifthClusterEVar.getK(eValue);
            double fK = fifthClusterFVar.getK(fValue);

            double aB = fifthClusterAVar.getB(aValue);
            double eB = fifthClusterEVar.getB(eValue);
            double fB = fifthClusterFVar.getB(fValue);

            double muA = aK * aValue + aB;
            double muE = eK * eValue + eB;
            double muF = fK * fValue + fB;

            double minMu = minFromArray(new double[]{muA, muE, muF});
     //       System.out.println("5 кластер мин мю " + minMu);
        }

        //firstClusterAVar.isValueBelongToThis()




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

    public List<double[]> getFirstCluster() {
        return getCluster(3, 43);
    }

    public List<double[]> getCluster(int sheetIndex, int rowCount) {
        List<double[]> list = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File("/home/igor/Загрузки/2.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            for(int i = 0; i < rowCount; i ++) {
                Row row = sheet.getRow(i);
                double [] sample = new double[4];
                sample[0] = row.getCell(0).getNumericCellValue();
                sample[1] = row.getCell(4).getNumericCellValue();
                sample[2] = row.getCell(5).getNumericCellValue();
                sample[3] = row.getCell(7).getNumericCellValue();
                if(sample[0] <= 1500
                        && sample[0] > 0
                        && sample[1] > 0
                        && sample[1] <= 5
                        && sample[2] > 0
                        && sample[2] <= 5
                        && sample[3] > 0
                        && sample[3] <= 5) {
                    list.add(sample);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void handleKnowledgeDataBaseButtonAction(ActionEvent actionEvent) {

    }

    public void handleFuzzyConclusionButtonAction(ActionEvent actionEvent) {

    }

    private double minFromArray(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] > min) {
                min = array[i];
            }
        }
        return min;
    }

    private double maxFromArray(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private void scale(double [][] samples) {
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
