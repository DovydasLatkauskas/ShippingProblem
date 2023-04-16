import static runner.Constants.INPUT_FOLDER_PATH;
import static runner.Constants.OUTPUT_FOLDER_PATH;
import static runner.Runner.runApplication;

public class Main {

    public static void main(String[] args){
        System.out.println("application started");
        // args ideas: verbose, test
        runApplication(args, INPUT_FOLDER_PATH, OUTPUT_FOLDER_PATH);
    }
}
