package model;

import java.util.List;


public class ParsedObject {
    private final String letters;
    private final List<Query> queries;

    public ParsedObject(String letters, List<Query> queries) {
        this.letters = letters;
        this.queries = queries;
    }

    public String getLetters() {
        return letters;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public record Query(int l, int r, int k) {
        public static Query empty() {
            return new Query(0, 0, 0);
        }
    }
}
