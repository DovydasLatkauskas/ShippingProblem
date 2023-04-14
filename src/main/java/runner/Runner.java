package runner;

import models.TransactionFile;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static reader.FileReader.readFile;
import static runner.Constants.INPUT_FOLDER_PATH;

public class Runner {
    /**
    Main function for running the program
     */
    public static void runApplication(String[] args){
        for (String fileName : getFileNames(INPUT_FOLDER_PATH)) {
            TransactionFile transactionFile = readFile(fileName);
            //transactionFile.calculateCosts();
            //transactionFile.createTextFile();
        }
    }

    /**
     Retrieves the names of all .txt files in the "input/" directory.
     @return A list of Strings representing the names of all .txt files in the "input/" directory.
     */
    private static List<String> getFileNames(String pathString){
        File directory = new File(pathString);
        File[] files = directory.listFiles();
        List<String> output = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    output.add(file.getName());
                }
            }
        } else{
            System.out.println("No files found in input/");
        }
        return output;
    }
    public static List<String> getFileNamesTest(String pathString) {
        return getFileNames(pathString);
    }
}
