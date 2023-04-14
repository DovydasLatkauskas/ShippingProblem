package runner;

import models.TransactionFile;


import static reader.FileReader.readFile;

public class Runner {
    public static void runApplication(String[] args){
        for (String fileName : getFileNames()) {
            TransactionFile transactionFile = readFile(fileName);
            transactionFile.calculateCosts();
            transactionFile.createTextFile();
        }
    }
}
