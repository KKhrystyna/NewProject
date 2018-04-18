import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloWorld {
    private static Scanner input;

    public static void main(String[] args) {

        System.out.println("Hello World!");

        openFile();
        closeFile();
    }

    public static void openFile() {
        try {
            input = new Scanner(Paths.get("expenses.xlsx"));
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
