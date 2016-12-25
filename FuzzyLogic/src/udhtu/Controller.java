package udhtu;

import javafx.event.ActionEvent;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import udhtu.fuzzy.LineChart_AWT;
import udhtu.fuzzy.LinguistikVar;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public void handleFirstTestButtonAction(ActionEvent actionEvent) {
        double aValue = 638.62499;
        double bValue = 0.09004;
        double hValue = 1.97671632379601;
        run(aValue, bValue, hValue);

    }

    public void handleSecondTestButtonAction(ActionEvent actionEvent) {

        double aValue = 1411.4841;
        double bValue = 0.09062;
        double hValue = 1.54453984693295;
        run(aValue, bValue, hValue);
    }

    public void handleThirdTestButtonAction(ActionEvent actionEvent) {
        double aValue = 1283.0245;
        double bValue = 0.09264;
        double hValue = 4.77014008083716;
        run(aValue, bValue, hValue);
    }

    public void handleFourthTestButtonAction(ActionEvent actionEvent) {
        double aValue = 526.84401;
        double bValue = 0.09135;
        double hValue = 1.29166595772918;
        run(aValue, bValue, hValue);
    }

    public void handleFifthTestButtonAction(ActionEvent actionEvent) {
        double aValue = 618.56039;
        double bValue = 0.09298;
        double hValue = 2.15742046276211;
        run(aValue, bValue, hValue);
    }

    private void run(double aValue, double bValue, double hValue) {
        List<double[]> firstCluster = getFirstCluster();
        List<double[]> secondCluster = getSecondCluster();
        List<double[]> thirdCluster = getThirdCluster();
        List<double[]> fourthCluster = getFourthCluster();
        List<double[]> fifthCluster = getFifthCluster();
        //функции принадлежности
        double[] firstClusterAColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterAColumn[i] = firstCluster.get(i)[0];
        }
        LinguistikVar firstClusterAVar = new LinguistikVar(firstClusterAColumn);

        double[] firstClusterBColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterBColumn[i] = firstCluster.get(i)[1];
        }
        LinguistikVar firstClusterBVar = new LinguistikVar(firstClusterBColumn);

        double[] firstClusterHColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterHColumn[i] = firstCluster.get(i)[2];
        }
        LinguistikVar firstClusterHVar = new LinguistikVar(firstClusterHColumn);
        double[] firstClusterFColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterFColumn[i] = firstCluster.get(i)[3];
        }
        LinguistikVar firstClusterFVar = new LinguistikVar(firstClusterFColumn);

        //2*****************************************
        double[] secondClusterAColumn = new double[secondCluster.size()];
        for (int i = 0; i < secondCluster.size(); i++) {
            secondClusterAColumn[i] = secondCluster.get(i)[0];
        }
        LinguistikVar secondClusterAVar = new LinguistikVar(secondClusterAColumn);
        double[] secondClusterBColumn = new double[secondCluster.size()];
        for (int i = 0; i < secondCluster.size(); i++) {
            secondClusterBColumn[i] = secondCluster.get(i)[1];
        }
        LinguistikVar secondClusterBVar = new LinguistikVar(secondClusterBColumn);
        double[] secondClusterHColumn = new double[secondCluster.size()];
        for (int i = 0; i < secondCluster.size(); i++) {
            secondClusterHColumn[i] = secondCluster.get(i)[2];
        }
        LinguistikVar secondClusterHVar = new LinguistikVar(secondClusterHColumn);
        double[] secondClusterFColumn = new double[secondCluster.size()];
        for (int i = 0; i < secondCluster.size(); i++) {
            secondClusterFColumn[i] = secondCluster.get(i)[3];
        }
        LinguistikVar secondClusterFVar = new LinguistikVar(secondClusterFColumn);

        //3***********************************************
        double[] thirdClusterAColumn = new double[thirdCluster.size()];
        for (int i = 0; i < thirdCluster.size(); i++) {
            thirdClusterAColumn[i] = thirdCluster.get(i)[0];
        }
        LinguistikVar thirdClusterAVar = new LinguistikVar(thirdClusterAColumn);
        double[] thirdClusterBColumn = new double[thirdCluster.size()];
        for (int i = 0; i < thirdCluster.size(); i++) {
            thirdClusterBColumn[i] = thirdCluster.get(i)[1];
        }
        LinguistikVar thirdClusterBVar = new LinguistikVar(thirdClusterBColumn);
        double[] thirdClusterHColumn = new double[thirdCluster.size()];
        for (int i = 0; i < thirdCluster.size(); i++) {
            thirdClusterHColumn[i] = thirdCluster.get(i)[2];
        }
        LinguistikVar thirdClusterHVar = new LinguistikVar(thirdClusterHColumn);
        double[] thirdClusterFColumn = new double[thirdCluster.size()];
        for (int i = 0; i < thirdCluster.size(); i++) {
            thirdClusterFColumn[i] = thirdCluster.get(i)[3];
        }
        LinguistikVar thirdClusterFVar = new LinguistikVar(thirdClusterFColumn);

        //4*****************************************************
        double[] fourthClusterAColumn = new double[fourthCluster.size()];
        for (int i = 0; i < fourthCluster.size(); i++) {
            fourthClusterAColumn[i] = fourthCluster.get(i)[0];
        }
        LinguistikVar fourthClusterAVar = new LinguistikVar(fourthClusterAColumn);
        double[] fourthClusterBColumn = new double[fourthCluster.size()];
        for (int i = 0; i < fourthCluster.size(); i++) {
            fourthClusterBColumn[i] = fourthCluster.get(i)[1];
        }
        LinguistikVar fourthClusterBVar = new LinguistikVar(fourthClusterBColumn);
        double[] fourthClusterHColumn = new double[fourthCluster.size()];
        for (int i = 0; i < fourthCluster.size(); i++) {
            fourthClusterHColumn[i] = fourthCluster.get(i)[2];
        }
        LinguistikVar fourthClusterHVar = new LinguistikVar(fourthClusterHColumn);
        double[] fourthClusterFColumn = new double[fourthCluster.size()];
        for (int i = 0; i < fourthCluster.size(); i++) {
            fourthClusterFColumn[i] = fourthCluster.get(i)[3];
        }
        LinguistikVar fourthClusterFVar = new LinguistikVar(fourthClusterFColumn);

        //5******************************************************
        double[] fifthClusterAColumn = new double[fifthCluster.size()];
        for (int i = 0; i < fifthCluster.size(); i++) {
            fifthClusterAColumn[i] = fifthCluster.get(i)[0];
        }
        LinguistikVar fifthClusterAVar = new LinguistikVar(fifthClusterAColumn);
        double[] fifthClusterBColumn = new double[fifthCluster.size()];
        for (int i = 0; i < fifthCluster.size(); i++) {
            fifthClusterBColumn[i] = fifthCluster.get(i)[1];
        }
        LinguistikVar fifthClusterBVar = new LinguistikVar(fifthClusterBColumn);
        double[] fifthClusterHColumn = new double[fifthCluster.size()];
        for (int i = 0; i < fifthCluster.size(); i++) {
            fifthClusterHColumn[i] = fifthCluster.get(i)[2];
        }
        LinguistikVar fifthClusterHVar = new LinguistikVar(fifthClusterHColumn);
        double[] fifthClusterFColumn = new double[fifthCluster.size()];
        for (int i = 0; i < fifthCluster.size(); i++) {
            fifthClusterFColumn[i] = fifthCluster.get(i)[3];
        }
        LinguistikVar fifthClusterFVar = new LinguistikVar(fifthClusterFColumn);

        //belong to
        boolean isBelongToFirstCluster =
                firstClusterAVar.isValueBelongToThis(aValue)
                        && firstClusterBVar.isValueBelongToThis(bValue)
                        && firstClusterHVar.isValueBelongToThis(hValue);

        boolean isBelongToSecondCluster =
                secondClusterAVar.isValueBelongToThis(aValue)
                        && secondClusterBVar.isValueBelongToThis(bValue)
                        && secondClusterHVar.isValueBelongToThis(hValue);

        boolean isBelongToThirdCluster =
                thirdClusterAVar.isValueBelongToThis(aValue)
                        && thirdClusterBVar.isValueBelongToThis(bValue)
                        && thirdClusterHVar.isValueBelongToThis(hValue);

        boolean isBelongToFourthCluster =
                fourthClusterAVar.isValueBelongToThis(aValue)
                        && fourthClusterBVar.isValueBelongToThis(bValue)
                        && fourthClusterHVar.isValueBelongToThis(hValue);

        boolean isBelongToFifthCluster =
                fifthClusterAVar.isValueBelongToThis(aValue)
                        && fifthClusterBVar.isValueBelongToThis(bValue)
                        && fifthClusterHVar.isValueBelongToThis(hValue);

        if(isBelongToFirstCluster) {
            double aK = firstClusterAVar.getK(aValue);
            double bK = firstClusterBVar.getK(bValue);
            double hK = firstClusterHVar.getK(hValue);

            double aB = firstClusterAVar.getB(aValue);
            double bB = firstClusterBVar.getB(bValue);
            double hB = firstClusterHVar.getB(hValue);

            double muA = aK * aValue + aB;
            double muB = bK * bValue + bB;
            double muH = hK * hValue + hB;

            double minMu = minFromArray(new double[]{muA, muB, muH});

            double approximation = 10;
            double integralStep = (firstClusterFVar.max - firstClusterFVar.min) / approximation;

            double valueInFirstStep = firstClusterFVar.normalizeFrequency[0];
            double valueInLastStep = firstClusterFVar.normalizeFrequency[firstClusterFVar.normalizeFrequency.length - 1];

            if(valueInFirstStep > minMu) {
                valueInFirstStep = minMu;
            }

            if(valueInLastStep > minMu) {
                valueInLastStep = minMu;
            }

            double denumSum = 0.0;
            double numSum = 0.0;

            for (int i = 0; i < firstClusterFVar.normalizeFrequency.length; i ++) {

                double resultMu = firstClusterFVar.normalizeFrequency[i];

                if(resultMu > minMu) {
                    resultMu = minMu;
                }

                double step = firstClusterFVar.edgesOfIntervals[i];

                numSum += step * resultMu;
                denumSum += resultMu;

            }

            double denumIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + denumSum);
            double numIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + numSum);

            LineChart_AWT.drawChart(
                    "Переменная A",
                    firstClusterAVar);

            LineChart_AWT.drawChart(
                    "Переменная B",
                    firstClusterBVar);

            LineChart_AWT.drawChart(
                    "Переменная H",
                    firstClusterHVar);

            LineChart_AWT.drawChart(
                    "Четкое число: " + String.valueOf(numIntegral / denumIntegral),
                    firstClusterFVar);

        } else if(isBelongToSecondCluster) {
            double aK = secondClusterAVar.getK(aValue);
            double eK = secondClusterBVar.getK(bValue);
            double fK = secondClusterHVar.getK(hValue);

            double aB = secondClusterAVar.getB(aValue);
            double eB = secondClusterBVar.getB(bValue);
            double fB = secondClusterHVar.getB(hValue);

            double muA = aK * aValue + aB;
            double muE = eK * bValue + eB;
            double muF = fK * hValue + fB;

            double minMu = minFromArray(new double[]{muA, muE, muF});

            double approximation = 10;
            double integralStep = (secondClusterFVar.max - secondClusterFVar.min) / approximation;

            double valueInFirstStep = secondClusterFVar.normalizeFrequency[0];
            double valueInLastStep = secondClusterFVar.normalizeFrequency[secondClusterFVar.normalizeFrequency.length - 1];

            if(valueInFirstStep > minMu) {
                valueInFirstStep = minMu;
            }

            if(valueInLastStep > minMu) {
                valueInLastStep = minMu;
            }

            double denumSum = 0.0;
            double numSum = 0.0;

            for (int i = 0; i < secondClusterFVar.normalizeFrequency.length; i ++) {

                double resultMu = secondClusterFVar.normalizeFrequency[i];

                if(resultMu > minMu) {
                    resultMu = minMu;
                }

                double step = secondClusterFVar.edgesOfIntervals[i];

                numSum += step * resultMu;
                denumSum += resultMu;

            }

            double denumIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + denumSum);
            double numIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + numSum);

            LineChart_AWT.drawChart(
                    "Переменная A",
                    secondClusterAVar);

            LineChart_AWT.drawChart(
                    "Переменная B",
                    secondClusterBVar);

            LineChart_AWT.drawChart(
                    "Переменная H",
                    secondClusterHVar);

            LineChart_AWT.drawChart(
                    "Четкое число: " + String.valueOf(numIntegral / denumIntegral),
                    secondClusterFVar);


        } else if(isBelongToThirdCluster) {
            double aK = thirdClusterAVar.getK(aValue);
            double eK = thirdClusterBVar.getK(bValue);
            double fK = thirdClusterHVar.getK(hValue);

            double aB = thirdClusterAVar.getB(aValue);
            double eB = thirdClusterBVar.getB(bValue);
            double fB = thirdClusterHVar.getB(hValue);

            double muA = aK * aValue + aB;
            double muE = eK * bValue + eB;
            double muF = fK * hValue + fB;

            double minMu = minFromArray(new double[]{muA, muE, muF});
            System.out.println("3 кластер мин мю " + minMu);

            double approximation = 10;
            double integralStep = (thirdClusterFVar.max - thirdClusterFVar.min) / approximation;

            double valueInFirstStep = thirdClusterFVar.normalizeFrequency[0];
            double valueInLastStep = thirdClusterFVar.normalizeFrequency[thirdClusterFVar.normalizeFrequency.length - 1];

            if(valueInFirstStep > minMu) {
                valueInFirstStep = minMu;
            }

            if(valueInLastStep > minMu) {
                valueInLastStep = minMu;
            }

            double denumSum = 0.0;
            double numSum = 0.0;

            for (int i = 0; i < thirdClusterFVar.normalizeFrequency.length; i ++) {

                double resultMu = thirdClusterFVar.normalizeFrequency[i];

                if(resultMu > minMu) {
                    resultMu = minMu;
                }

                double step = thirdClusterFVar.edgesOfIntervals[i];

                numSum += step * resultMu;
                denumSum += resultMu;

            }

            double denumIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + denumSum);
            double numIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + numSum);

            LineChart_AWT.drawChart(
                    "Переменная A",
                    thirdClusterAVar);

            LineChart_AWT.drawChart(
                    "Переменная B",
                    thirdClusterBVar);

            LineChart_AWT.drawChart(
                    "Переменная H",
                    thirdClusterHVar);

            LineChart_AWT.drawChart(
                    "Четкое число: " + String.valueOf(numIntegral / denumIntegral),
                    thirdClusterFVar);

        } else if(isBelongToFourthCluster) {
            double aK = fourthClusterAVar.getK(aValue);
            double eK = fourthClusterBVar.getK(bValue);
            double fK = fourthClusterHVar.getK(hValue);

            double aB = fourthClusterAVar.getB(aValue);
            double eB = fourthClusterBVar.getB(bValue);
            double fB = fourthClusterHVar.getB(hValue);

            double muA = aK * aValue + aB;
            double muE = eK * bValue + eB;
            double muF = fK * hValue + fB;

            double minMu = maxFromArray(new double[]{muA, muE, muF});
            System.out.println("4 кластер мин мю " + minMu);

            double approximation = 10;
            double integralStep = (fourthClusterFVar.max - fourthClusterFVar.min) / approximation;

            double valueInFirstStep = fourthClusterFVar.normalizeFrequency[0];
            double valueInLastStep = fourthClusterFVar.normalizeFrequency[fourthClusterFVar.normalizeFrequency.length - 1];

            if(valueInFirstStep > minMu) {
                valueInFirstStep = minMu;
            }

            if(valueInLastStep > minMu) {
                valueInLastStep = minMu;
            }

            double denumSum = 0.0;
            double numSum = 0.0;

            for (int i = 0; i < fourthClusterFVar.normalizeFrequency.length; i ++) {

                double resultMu = fourthClusterFVar.normalizeFrequency[i];

                if(resultMu > minMu) {
                    resultMu = minMu;
                }

                double step = fourthClusterFVar.edgesOfIntervals[i];

                numSum += step * resultMu;
                denumSum += resultMu;

            }

            double denumIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + denumSum);
            double numIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + numSum);

            LineChart_AWT.drawChart(
                    "Переменная A",
                    fourthClusterAVar);

            LineChart_AWT.drawChart(
                    "Переменная B",
                    fourthClusterBVar);

            LineChart_AWT.drawChart(
                    "Переменная H",
                    fourthClusterHVar);

            LineChart_AWT.drawChart(
                    "Четкое число: " + String.valueOf(numIntegral / denumIntegral),
                    fourthClusterFVar);

        } else if(isBelongToFifthCluster) {
            double aK = fifthClusterAVar.getK(aValue);
            double eK = fifthClusterBVar.getK(bValue);
            double fK = fifthClusterHVar.getK(hValue);

            double aB = fifthClusterAVar.getB(aValue);
            double eB = fifthClusterBVar.getB(bValue);
            double fB = fifthClusterHVar.getB(hValue);

            double muA = aK * aValue + aB;
            double muE = eK * bValue + eB;
            double muF = fK * hValue + fB;

            double minMu = minFromArray(new double[]{muA, muE, muF});
            System.out.println("5 кластер мин мю " + minMu);

            double approximation = 10;
            double integralStep = (fifthClusterFVar.max - fifthClusterFVar.min) / approximation;

            double valueInFirstStep = fifthClusterFVar.normalizeFrequency[0];
            double valueInLastStep = fifthClusterFVar.normalizeFrequency[fifthClusterFVar.normalizeFrequency.length - 1];

            if(valueInFirstStep > minMu) {
                valueInFirstStep = minMu;
            }

            if(valueInLastStep > minMu) {
                valueInLastStep = minMu;
            }

            double denumSum = 0.0;
            double numSum = 0.0;

            for (int i = 0; i < fifthClusterFVar.normalizeFrequency.length; i ++) {

                double resultMu = fifthClusterFVar.normalizeFrequency[i];

                if(resultMu > minMu) {
                    resultMu = minMu;
                }

                double step = fifthClusterFVar.edgesOfIntervals[i];

                numSum += step * resultMu;
                denumSum += resultMu;

            }

            double denumIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + denumSum);
            double numIntegral = integralStep *((valueInFirstStep + valueInLastStep) / 2 + numSum);

            LineChart_AWT.drawChart(
                    "Переменная A",
                    fifthClusterAVar);

            LineChart_AWT.drawChart(
                    "Переменная B",
                    fifthClusterBVar);

            LineChart_AWT.drawChart(
                    "Переменная H",
                    fifthClusterHVar);

            LineChart_AWT.drawChart(
                    "Четкое число: " + String.valueOf(numIntegral / denumIntegral),
                    fifthClusterFVar);

        }

    }

    public List<double[]> getFifthCluster() {
        return getCluster(7, 403);
    }

    public List<double[]> getFourthCluster() {
        return getCluster(6, 264);
    }

    public List<double[]> getThirdCluster() {
        return getCluster(5, 211);
    }

    public List<double[]> getSecondCluster() {
        return getCluster(4, 78);
    }

    public List<double[]> getFirstCluster() {
        return getCluster(3, 43);
    }

    public List<double[]> getCluster(int sheetIndex, int rowCount) {
        List<double[]> list = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File("2.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            for(int i = 0; i < rowCount; i ++) {
                Row row = sheet.getRow(i);
                double [] sample = new double[4];
                sample[0] = row.getCell(0).getNumericCellValue();
                sample[1] = row.getCell(1).getNumericCellValue();
                sample[2] = row.getCell(7).getNumericCellValue();
                sample[3] = row.getCell(5).getNumericCellValue();
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

    private double minFromArray(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private double maxFromArray(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] > min) {
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
