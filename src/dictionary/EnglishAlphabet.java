package dictionary;


public class EnglishAlphabet implements Alphabet {
    private static EnglishAlphabet instance;

    @Override
    public int getCharCount() {
        return 26;
    }

    @Override
    public int getCharIndex(char c) throws IllegalArgumentException{
        //Specjalny przypadek tutaj
        if (c<'a' || c>'z') throw new IllegalArgumentException("To nie jest znak ang alfabetu!");
        return c-'a';
    }

    public static Alphabet getInstance(){
        if(instance == null){
            instance = new EnglishAlphabet();
        }
        return instance;
    }
}
