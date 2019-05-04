package dictionary;

public class Dictionary {
    //Na razie niech będzie jako prawie-singleton
    private static Trie engDictionary;

    public static Trie getEngDictionary(){
        if (engDictionary == null) engDictionary = new Trie(EnglishAlphabet.getInstance());
        return engDictionary;
    }
}
