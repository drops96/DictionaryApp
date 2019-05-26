package dictionary;

public class Dictionary {
    //Na razie niech będzie jako prawie-singleton
    private static Trie engDictionary;
    private static Trie polishDictionary;
    private static boolean initialized = false;
    public static String dictionaryFileName = "dictionary.dic";

    //TODO
    //Podpiąć do wyboru polski słownik
    public static Trie getEngDictionary(){
        if (engDictionary == null) engDictionary = new Trie(EnglishAlphabet.getInstance());
        return engDictionary;
    }

    public static Trie getPolishDictionary(){
        if (polishDictionary == null) polishDictionary = new Trie(PolishAlphabet.getInstance());
        return polishDictionary;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void setInitialized(boolean initialized) {
        Dictionary.initialized = initialized;
    }
}
