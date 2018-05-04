import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;

public class HelloWorld {

    private MySQLAccess dbManager = new MySQLAccess();

    public static void main(String[] args) {

        System.out.println("Hello World!");

        new HelloWorld().readFile();

    }

    public void readFile() {

        try {

            FileInputStream excelFile = new FileInputStream(new File("expenses.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet dataTypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = dataTypeSheet.iterator();

            Date currentDate = new Date(0);

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                String currentProduct = "null";
                double currentCost = 0.00;
                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();


                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        currentProduct = currentCell.getStringCellValue();
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        if (DateUtil.isCellDateFormatted(currentCell)) {
                            java.util.Date javaDate = currentCell.getDateCellValue();
                            currentDate = new Date(javaDate.getTime());
                        } else {
                            currentCost = currentCell.getNumericCellValue();
                        }
                    }
                }
                System.out.println(currentDate + currentProduct + currentCost);
                Record currentRecord = new Record(currentDate, currentProduct, currentCost);
                dbManager.insertInDataBase(currentRecord);

                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
