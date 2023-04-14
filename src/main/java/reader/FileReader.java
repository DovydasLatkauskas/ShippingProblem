package reader;

import models.TransactionFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static TransactionFile readFile(String fileName) {

        try ( final Scanner sc = new Scanner(new File(fileName)) ) {
            while ( sc.hasNextLine() ) {
                String line = sc.nextLine();
            }
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        }
    }
}
