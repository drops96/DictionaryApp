package dictionary;

public interface Alphabet {
    int getCharCount();
    int getCharIndex(char c) throws IllegalArgumentException;
}
