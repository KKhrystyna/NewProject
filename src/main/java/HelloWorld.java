import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloWorld {
    private static Scanner input;

    public static void main(String[] args) {

        System.out.println("Hello World!");

        XSSFWorkbook workbook = new XSSFWorkbook();


        openFile();
        closeFile();
    }

    public static void openFile() {
        try {
            input = new Scanner(Paths.get("expenses.xlsx"));
            while (input.hasNextLine()){
                String s = input.nextLine();
                System.out.println(s);
            }
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }

    public static void closeFile() {
        if (input != null)
            input.close();
    }
}
