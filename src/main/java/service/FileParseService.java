package service;

import java.util.List;
import model.ParsedObject;

public interface FileParseService {
    ParsedObject parse(List<String> readLines);
}
