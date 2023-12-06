package service.Impl;

import java.util.List;
import model.ParsedObject;
import service.FileParseService;

public class FileParseServiceImpl implements FileParseService {
    @Override
    public ParsedObject parse(List<String> readLines) {
        String[] firstArguments = readLines.get(0).split(" ");
        String letters = readLines.get(1);
        validData(Integer.parseInt(firstArguments[0]),
                Integer.parseInt(firstArguments[1]), readLines, letters);
        return new ParsedObject(letters,
                parseLines(readLines));
    }

    private void validData(int lettersCount, int queriesCount, List<String> readLines, String letters) {
        if (readLines.size() - 2 != queriesCount) {
            throw new RuntimeException("Count queries and pairs is not equals!!!");
        }
        if (letters == null) {
            throw new RuntimeException("Letters can't be null!!!");
        }
        if (letters.length() != lettersCount) {
            throw new RuntimeException("Count letters: "
                    + lettersCount + " and string letter " + letters + " is not equals!!!");
        }
    }

    private List<ParsedObject.Query> parseLines(List<String> readLines) {
        return readLines.stream()
                .skip(2)
                .map(this::parseParts)
                .toList();
    }

    private ParsedObject.Query parseParts(String s) {
        String[] parts = s.split(" ");
        if (parts.length == 3) {
            return new ParsedObject.Query(Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]));
        }
        return ParsedObject.Query.empty();
    }
}
