package dictionary;

import java.util.HashMap;


public class PolishAlphabet implements Alphabet {
    private static Alphabet instance;
    private HashMap<Character, Integer> alphabetMapping;

    private PolishAlphabet() {
        alphabetMapping = new HashMap<>();
        alphabetMapping.put('a', 0);
        alphabetMapping.put('ą', 1);
        alphabetMapping.put('b', 2);
        alphabetMapping.put('c', 3);
        alphabetMapping.put('ć', 4);
        alphabetMapping.put('d', 5);
        alphabetMapping.put('e', 6);
        alphabetMapping.put('ę', 7);
        alphabetMapping.put('f', 8);
        alphabetMapping.put('g', 9);
        alphabetMapping.put('h', 10);
        alphabetMapping.put('i', 11);
        alphabetMapping.put('j', 12);
        alphabetMapping.put('k', 13);
        alphabetMapping.put('l', 14);
        alphabetMapping.put('ł', 15);
        alphabetMapping.put('m', 16);
        alphabetMapping.put('n', 17);
        alphabetMapping.put('ń', 18);
        alphabetMapping.put('o', 19);
        alphabetMapping.put('ó', 20);
        alphabetMapping.put('p', 21);
        alphabetMapping.put('q', 22);
        alphabetMapping.put('r', 23);
        alphabetMapping.put('s', 24);
        alphabetMapping.put('ś', 25);
        alphabetMapping.put('t', 26);
        alphabetMapping.put('u', 27);
        alphabetMapping.put('v', 28);
        alphabetMapping.put('w', 29);
        alphabetMapping.put('x', 30);
        alphabetMapping.put('y', 31);
        alphabetMapping.put('z', 32);
        alphabetMapping.put('ź', 33);
        alphabetMapping.put('ż', 34);
    }

    @Override
    public int getCharCount() {
        return alphabetMapping.size();
    }

    @Override
    public int getCharIndex(char c) throws IllegalArgumentException {
        if (alphabetMapping.containsKey(c)){
            return alphabetMapping.get(c);
        } else throw new IllegalArgumentException("To nie jest znak polskiego alfabetu!");
    }

    public static Alphabet getInstance(){
        if(instance == null){
            instance = new PolishAlphabet();
        }
        return instance;
    }
}
