package dictionary;

public class Dictionary {
    //Na razie niech będzie jako prawie-singleton
    private static EngTrie engDictionary;

    public static EngTrie getEngDictionary(){
        if (engDictionary == null) engDictionary = new EngTrie();
        return engDictionary;
    }
}
