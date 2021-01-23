import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readFromFile(Path path) {
        List<String> dataFromFile = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
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
