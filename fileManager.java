package dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fileManager {
    private final String FILE_PATH = "dictionary.txt";

    public fileManager() {
        ensureFileExists();
    }

    private void ensureFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Could not create dictionary file: " + e.getMessage());
            }
        }
    }

    public List<Word> loadWords() {
        List<Word> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Word word = Word.fromFileString(line);
                if (word != null) {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return words;
    }

    public void saveWords(List<Word> words) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Word word : words) {
                writer.write(word.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}