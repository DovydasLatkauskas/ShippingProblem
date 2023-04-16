package runner;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static runner.Runner.getFileNamesTest;
import static runner.Runner.runApplication;

public class RunnerTest {
    @Test
    void emptyRunnerWorks(){
        runApplication(new String[0], "input/", "output/");
    }
    @Test
    void testWithInput1() throws IOException {
        testRunner("test1.txt", "output_of_test1.txt");
    }
    @Test
    void testWithInput2() throws IOException {
        testRunner("test2.txt", "output_of_test2.txt");
    }
    @Test
    void testWithInput3() throws IOException {
        testRunner("test3.txt", "output_of_test3.txt");
    }

    private void testRunner(String inputFileName, String outputFileName) throws IOException {
        File inputFile = new File("src/test/input/" + inputFileName);
        File tempDir = new File("src/test/output/temp");
        tempDir.mkdirs();

        runApplication(new String[0], inputFile.getParent(), tempDir.getPath() + "/");

        File expectedOutputFile = new File("src/test/output/" + outputFileName);
        File actualOutputFile = new File(tempDir.getPath(), outputFileName);
        assertTrue(Files.mismatch(expectedOutputFile.toPath(), actualOutputFile.toPath()) == -1);

        deleteFolder("src/test/output/temp");
    }


    @Test
    void testGetFileNames() {
        // Create a temporary directory for testing
        File tempDir = new File("temp/");
        tempDir.mkdirs();
        File file1 = new File("temp/test1.txt");
        File file2 = new File("temp/test2.txt");
        File file3 = new File("temp/test3.doc");
        try {
            file1.createNewFile();
            file2.createNewFile();
            file3.createNewFile();
        } catch (Exception e) {
            fail("Failed to create test files: " + e.getMessage());
        }

        List<String> fileNames = getFileNamesTest(tempDir.getPath());

        assertTrue(fileNames.contains("test1.txt"));
        assertTrue(fileNames.contains("test2.txt"));
        assertFalse(fileNames.contains("test3.doc"));

        file1.delete();
        file2.delete();
        file3.delete();
        tempDir.delete();
    }

    public void deleteFolder(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file.getAbsolutePath());
                    } else {
                        file.delete();
                    }
                }
            }
            folder.delete();
        }
    }
}
