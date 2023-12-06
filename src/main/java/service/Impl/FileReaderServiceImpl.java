package service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import service.FileReaderService;

public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<String> readFile(String fileName) {
        try {
            List<String> strings = Files.readAllLines(new File(fileName).toPath());
            if (strings.isEmpty()) {
                throw new RuntimeException("File is empty!!!");
            }
            return strings;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }
}
