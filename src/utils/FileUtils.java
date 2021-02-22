package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readFromFile(String path) {
        List<String> dataFromFile = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            String line;
            while ((line = reader.readLine()) != null) {
                dataFromFile.add(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFromFile;
    }
}
