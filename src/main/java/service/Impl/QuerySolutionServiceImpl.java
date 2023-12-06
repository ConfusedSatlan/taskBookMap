package service.Impl;

import java.util.List;
import model.ParsedObject;
import service.FileParseService;
import service.FileReaderService;
import service.FileWriterService;
import service.QuerySolutionService;

public class QuerySolutionServiceImpl implements QuerySolutionService {
    private final FileReaderService fileReader;
    private final FileWriterService fileWriter;
    private final FileParseService fileParse;

    public QuerySolutionServiceImpl(FileReaderService fileReader, FileWriterService fileWriter, FileParseService fileParse) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
        this.fileParse = fileParse;
    }

    @Override
    public void querySolution(String fileName) {
        List<String> readLines = fileReader.readFile(fileName);
        ParsedObject parsedObject = fileParse.parse(readLines);
        List<ParsedObject.Query> queries = parsedObject.getQueries();
        fileWriter.writeFile("output.txt",
                findMatch(queries, parsedObject.getLetters()));
    }

    private List<String> findMatch(List<ParsedObject.Query> queries, String letters) {
        char[] letterChar = letters.toCharArray();
        return queries.stream()
               .map(query -> String.valueOf(findMatchHandle(query, letterChar)))
               .toList();
    }

    private Integer findMatchHandle(ParsedObject.Query query, char[] letters) {
        int startIndex = query.l() - 1;
        char targetChar = letters[startIndex + query.k() - 1];
        char matchChar = (targetChar == 'A') ? 'B' : 'A';
        int count = 0;
        int matchIndex = 0;
        for (int i = startIndex; i < query.r(); ++i) {
            int currentIndex = i - startIndex;
            if (query.k() > currentIndex) {
                if (letters[i] == targetChar) {
                    ++count;
                }
            } else if (count == 0) {
                break;
            }
            if (letters[i] == matchChar) {
                --count;
                matchIndex = currentIndex + 1;
            }
        }
        return count == 0 ? matchIndex : -1;
    }
}
