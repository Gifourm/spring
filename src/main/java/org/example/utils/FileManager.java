package org.example.utils;

public interface FileManager {
    void writeFile(String filePath, Object content);

    String readFile(String filePath, int lineNumber);

    String readFile(String filePath);
}
