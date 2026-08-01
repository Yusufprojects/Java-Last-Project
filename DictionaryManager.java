package dictionary;

import java.util.List;

public class DictionaryManager {
    private List<Word> wordList;
    private fileManager fileManager;

    public DictionaryManager() {
        this.fileManager = new fileManager();
        this.wordList = fileManager.loadWords();
    }

    public String searchWord(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return "Please enter a word to search.";
        }

        for (Word word : wordList) {
            if (word.getTerm().equalsIgnoreCase(searchTerm.trim())) {
                return word.getDefinition();
            }
        }
        return "Word not found.";
    }

    public boolean addWord(String term, String definition) {
        if (term.trim().isEmpty() || definition.trim().isEmpty()) {
            return false;
        }

        // Check if word already exists
        for (Word word : wordList) {
            if (word.getTerm().equalsIgnoreCase(term.trim())) {
                return false;
            }
        }

        wordList.add(new Word(term, definition));
        fileManager.saveWords(wordList);
        return true;
    }

    public List<Word> getWordList() {
        return wordList;
    }
}