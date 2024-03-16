package org.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class CSVManager implements FileManager {
    private final CSVService CSVservice;

    @Autowired
    public CSVManager(CSVService CSVservice) {
        this.CSVservice = CSVservice;
    }

    @Override
    public void writeFile(String filePath, Object content) {
        List<String> contentList = CSVservice.objectToList(content);
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (String item : contentList) {
                writer.write(item);
                writer.write(",");
            }
            writer.newLine();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String readFile(String filePath, int lineNumber) {
        if (lineNumber < 0) {
            throw new IndexOutOfBoundsException("Line number must be a positive value");
        }
        Path path = Paths.get(filePath);
        try (Stream<String> stream = Files.lines(path)) {
            return stream.skip(lineNumber).findFirst().orElse(null);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String readFile(String filePath) {
        Path path = Paths.get(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int lineCount = (int) reader.lines().count();
            int randomNumber = new Random().nextInt(lineCount);
            try (Stream<String> stream = Files.lines(path)) {
            return stream.skip(randomNumber).findFirst().orElse(null);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
