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

/**
 * Created by igor on 18.12.16.
 */
public class Controllerrr {

    public void handleClusterizationButtonAction(ActionEvent actionEvent) {
        List<double[]> firstCluster = getFirstCluster();
        //функции принадлежности
        double[] firstClusterAColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterAColumn[i] = firstCluster.get(i)[0];
        }
        LinguistikVar firstClusterAVar = new LinguistikVar(firstClusterAColumn);


       /* for(int i = 0; i < firstClusterAVar.normalizeFrequency.length; i ++) {
            System.out.println(String.valueOf(firstClusterAVar.normalizeFrequency[i]).replace(".", ","));
        }*/


        double[] firstClusterEColumn = new double[firstCluster.size()];
        for (int i = 0; i < firstCluster.size(); i++) {
            firstClusterEColumn[i] = firstCluster.get(i)[1];
        }
        LinguistikVar firstClusterEVar = new LinguistikVar(firstClusterEColumn);


        for(int i = 0; i < firstClusterEVar.normalizeFrequency.length; i ++) {
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
        });

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

}
