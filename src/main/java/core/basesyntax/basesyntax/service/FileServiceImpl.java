package core.basesyntax.basesyntax.service;

import core.basesyntax.basesyntax.db.Storage;
import core.basesyntax.basesyntax.model.Fruit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileServiceImpl implements FileService {
    private static final String TITLE = "fruit,quantity";
    private static final String SEPARATOR = ",";

    @Override
    public void writeToFile(String fileName) {
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {

            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append(TITLE);

            Map<String, Fruit> fruits = Storage.getFruits();
            for (Fruit fruit : fruits.values()) {
                reportBuilder
                        .append(System.lineSeparator())
                        .append(fruit.getName())
                        .append(SEPARATOR)
                        .append(fruit.getQuantity());
            }

            byte[] bytes = reportBuilder.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(bytes);

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(getClass().getClassLoader()
                    .getResource(fileName).toURI()));
        } catch (Exception e) {
            throw new RuntimeException("Error reading file " + fileName, e);
        }
    }
}
