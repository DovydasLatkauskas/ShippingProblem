package runner;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RunnerTest {
    @Test
    void emptyRunnerWorks(){

    }
    @Test
    void testGetFileNames() {
        // Create a temporary directory for testing
        File tempDir = new File("temp/");
        tempDir.mkdirs();
        // Create some files with .txt extension in the directory
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

        // Call the function to get file names
        List<String> fileNames = getFileNamesTest(tempDir.getPath());

        // Check that the expected files were found
        assertTrue(fileNames.contains("test1.txt"));
        assertTrue(fileNames.contains("test2.txt"));
        assertFalse(fileNames.contains("test3.doc"));

        // Clean up temporary files and directory
        file1.delete();
        file2.delete();
        file3.delete();
        tempDir.delete();
    }
}
