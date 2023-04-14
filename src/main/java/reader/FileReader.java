package reader;

import models.DeliveryMethod;
import models.PackageSize;
import models.Transaction;
import models.TransactionFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class FileReader {
    public static void readFile(String fileName) {

        try ( final Scanner sc = new Scanner(new File(fileName)) ) {
            while ( sc.hasNextLine() ) {
                String line = sc.nextLine();
            }
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        }
    }
}
