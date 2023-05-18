package Session5.Lab6.App2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {
    public static String[] readLines(String filePath, int numLines) throws IOException {
        return Files.lines(Paths.get(filePath))
                .skip(Math.max(0, Files.lines(Paths.get(filePath)).count() - numLines))
                .toArray(String[]::new);
    }
}