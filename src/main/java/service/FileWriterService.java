package service;

import java.util.List;

public interface FileWriterService {
    void writeFile(String fileName, List<String> data);
}
