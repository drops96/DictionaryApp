package dictionary;

import javafx.scene.control.Alert;

public class Trie {
    private TrieNode root;
    private Alphabet alphabet;

    Trie(Alphabet a) {
        this.alphabet = a;
        root = new TrieNode(a.getCharCount());
    }

    public boolean insert(String word, String translation) {
        if (!checkAllowedChars(word)) return false;
        TrieNode p = root;
        //Dla kazdej litery w wyrazie
        for(int i=0; i<word.length(); i++){
            //Weź obecną literę
            char c = word.charAt(i);
            //Oblicz jej indeks w tablicy referencji
            //TODO
            //Dodać obsługę wyjątku który może tu być (nwm na razie jaką)
            int index = alphabet.getCharIndex(c);
            if(p.arr[index]==null){
                //Jeśli pusta referencja, utworz nowy obiekt i referencja na niego
                TrieNode temp = new TrieNode(alphabet.getCharCount());
                p.arr[index]=temp;
                p = temp;
            }else{
                //Jezeli istnieje obiekt, referencja na niego
                p=p.arr[index];
            }
        }
        //Oznacz koniec wyrazu
        p.isEnd=true;
        //Ustaw tlumaczenie
        p.addTranslation(translation);
        return true;
    }

    public String search(String word) {
        TrieNode p = searchNode(word);
        if(p==null){
            return "Nie znaleziono!";
        }else{
            if(p.isEnd)
                return p.getTranslations();
        }
        return null;
    }

    public TrieNode searchNode(String s){
        if (!checkAllowedChars(s)) return null;
        TrieNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = alphabet.getCharIndex(c);
            if(p.arr[index]!=null){
                p = p.arr[index];
            }else{
                return null;
            }
        }
        if(p==root)
            return null;
        return p;
    }

    boolean checkAllowedChars(String word) {
        try {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = alphabet.getCharIndex(c);
            }
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Komunikat");
            alert.setHeaderText("Niedozwolony znak w wyrazie!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
