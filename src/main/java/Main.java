import service.FileParseService;
import service.FileReaderService;
import service.FileWriterService;
import service.Impl.FileParseServiceImpl;
import service.Impl.FileReaderServiceImpl;
import service.Impl.FileWriterServiceImpl;
import service.Impl.QuerySolutionServiceImpl;
import service.QuerySolutionService;

public class Main {
    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        FileWriterService fileWriterService = new FileWriterServiceImpl();
        FileParseService fileParseService = new FileParseServiceImpl();
        QuerySolutionService querySolutionService =
                new QuerySolutionServiceImpl(fileReaderService,
                                            fileWriterService,
                                            fileParseService);
        querySolutionService.querySolution("input.txt");
    }
}