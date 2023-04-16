package runner;

import models.TransactionFile;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static reader.FileReader.readFile;

public class Runner {
    /**
     Main function for running the program
     Separate from main, so that I can define a different input folder path for testing
     */
    public static void runApplication(String[] args, String input_folder_path, String output_folder_path){
        for (String fileName : getFileNames(input_folder_path)) {
            TransactionFile transactionFile = readFile(fileName, input_folder_path);
            transactionFile.calculateCosts();
            transactionFile.createTextFile(output_folder_path, fileName);
            transactionFile.printToConsole();
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
