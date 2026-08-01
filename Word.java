package dictionary;

public class Word {
    private String term;
    private String definition;

    public Word(String term, String definition) {
        this.term = term.trim();
        this.definition = definition.trim();
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public String toFileString() {
        return term + ";" + definition;
    }

    //Burayı araştırr
    public static Word fromFileString(String line) {
        String[] parts = line.split(";");
        if (parts.length == 2) {
            return new Word(parts[0], parts[1]);
        }
        return null;
    }
}
