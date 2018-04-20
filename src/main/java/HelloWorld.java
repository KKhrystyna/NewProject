import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

public class HelloWorld {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        readFile();
    }

    public static void readFile() {

        try {

            FileInputStream excelFile = new FileInputStream(new File("expenses.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();

                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.printf("%s%s", currentCell.getStringCellValue(), " -- ");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        if (DateUtil.isCellDateFormatted(currentCell)) {
                            System.out.println();
                            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                            String date = sdf.format(currentCell.getDateCellValue());
                            System.out.print(date);
                            System.out.println();
                        } else {
                            System.out.printf("%s%s", currentCell.getNumericCellValue(), " --");
                        }
                    }
                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
