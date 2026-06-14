package utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static String getData(
            String path,
            int rowNum,
            int colNum) {

        String data = "";

        try {

            FileInputStream fis =
                    new FileInputStream(path);

            XSSFWorkbook workbook =
                    new XSSFWorkbook(fis);

            XSSFSheet sheet =
                    workbook.getSheetAt(0);

            Row row =
                    sheet.getRow(rowNum);

            DataFormatter formatter =
                    new DataFormatter();

            data =
                    formatter.formatCellValue(
                            row.getCell(colNum));

            workbook.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return data;
    }

}