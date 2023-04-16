package reader;

import models.DeliveryMethod;
import models.PackageSize;
import models.Transaction;
import models.TransactionFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static runner.Constants.INPUT_FOLDER_PATH;

public class FileReader {
    public static TransactionFile readFile(String fileName) {

        try ( final Scanner sc = new Scanner(new File(INPUT_FOLDER_PATH + fileName)) ) {
            List<Transaction> transactionList = new ArrayList<>();
            while ( sc.hasNextLine() ) {
                String line = sc.nextLine();
                transactionList.add(stringToTransaction(line));
            }
            return new TransactionFile(transactionList);
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        }
    }

    private static Transaction stringToTransaction(String line) {
        String[] parts = line.split(" ");
        if(parts.length == 3){ // for input transactions
            try{
                LocalDate date = LocalDate.parse(parts[0]);
                PackageSize packageSize = PackageSize.valueOf(parts[1]);
                DeliveryMethod deliveryMethod = DeliveryMethod.valueOf(parts[2]);

                return new Transaction(date, packageSize, deliveryMethod);
            } catch (Exception e){ // to catch invalid transactions with 3 parts
                return new Transaction(line);
            }
        } else if(parts.length == 5){ // for output transactions
            try{
                LocalDate date = LocalDate.parse(parts[0]);
                PackageSize packageSize = PackageSize.valueOf(parts[1]);
                DeliveryMethod deliveryMethod = DeliveryMethod.valueOf(parts[2]);
                Integer shipmentCostCents = moneyStringToCents(parts[3]);
                Integer discountCostCents = moneyStringToCents(parts[4]);

                return new Transaction(date, packageSize, deliveryMethod, shipmentCostCents, discountCostCents);
            } catch (Exception e){
                return new Transaction(line);
            }
        } else{return new Transaction(line);} // for invalid inputs
    }

    private static Integer moneyStringToCents(String input) {
        if(input.equals("-")){return null;} // for discountCostCents
        String[] parts = input.split("\\.");
        return Integer.parseInt(parts[0]) * 100 + Integer.parseInt(parts[1]);
    }
    public static Transaction stringToTransactionTest(String line) {
        return stringToTransaction(line);
    }

}
