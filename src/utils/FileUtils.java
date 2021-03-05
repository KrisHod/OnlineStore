package utils;

import entities.Item;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readFromFile(String path) {
        List<String> dataFromFile = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
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

    public void writeItems(List<Item> items, String fileName) {

        try (FileWriter csvWriter = new FileWriter(fileName)) {
            csvWriter.append("id");
            csvWriter.append(";");
            csvWriter.append("title");
            csvWriter.append(";");
            csvWriter.append("code");
            csvWriter.append(";");
            csvWriter.append("producer");
            csvWriter.append(";");
            csvWriter.append("dateOfLastUpdate");
            csvWriter.append(";");
            csvWriter.append("\n");

            for (Item row : items) {
                csvWriter.append(String.join(";", String.valueOf(row.getId()), row.getTitle(),
                        String.valueOf(row.getCode()), row.getProducer(),
                        row.getDateOfLastUpdate().format(DateTimeFormatter.ofPattern("dd.MMMM yyyy"))));
                csvWriter.append("\n");
            }
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
