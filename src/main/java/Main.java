import models.TransactionFile;

import java.util.ArrayList;
import java.util.List;

import static reader.FileReader.readFile;

public class Main {
    public static void main(String[] args){
        System.out.println("application started");
        // args ideas: verbose, test
        runApplication(args);
    }
}
