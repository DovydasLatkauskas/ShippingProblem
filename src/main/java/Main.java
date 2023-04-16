import static runner.Constants.INPUT_FOLDER_PATH;
import static runner.Constants.OUTPUT_FOLDER_PATH;
import static runner.Runner.runApplication;

public class Main {

    public static void main(String[] args){
        System.out.println("Application started");
        runApplication(args, INPUT_FOLDER_PATH, OUTPUT_FOLDER_PATH);
        System.out.println("Application finished");
    }
}
