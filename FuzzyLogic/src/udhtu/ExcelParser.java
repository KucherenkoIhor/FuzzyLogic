package udhtu;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igor on 21.11.16.
 */
public class ExcelParser {

    public static double[][] parse1() {
        List<double[]> list = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File("/home/igor/Загрузки/2.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            for(int i = 0; i < 1000; i ++) {
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
        double[][] result = new double[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static List<FourDPoint> parse() {
        List<FourDPoint> result = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File("/home/igor/Загрузки/2.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            for(int i = 0;i < 1000; i ++) {
                Row row = sheet.getRow(i);
                double x = row.getCell(0).getNumericCellValue();
                double y = row.getCell(4).getNumericCellValue();
                double z = row.getCell(7).getNumericCellValue();
                double j = row.getCell(5).getNumericCellValue();
                result.add(new FourDPoint(x, y, z, j));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
