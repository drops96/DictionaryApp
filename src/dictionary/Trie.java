package dictionary;

import javafx.collections.ObservableList;
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

    private TrieNode searchNode(String s){
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

    public ObservableList<String> getTranslationsAsList(String s){
        TrieNode p = searchNode(s);
        if (p != null && p.isEnd){
            return p.getTranslationList();
        }
        return null;
    }

    public boolean deleteTranslation(String word, String translation){
        TrieNode p = searchNode(word);
        if (p != null && p.isEnd){
            p.deleteTranslation(translation);
            return true;
        }
        return false;
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

    public void delete(String word) throws IllegalArgumentException{
        if (word == null) throw new IllegalArgumentException("Słowo do usunięcia nie może być puste!");
        delete(root, word, 0);
    }

    private TrieNode delete(TrieNode x, String word, int d){
        if (x==null) return null;
        if (d==word.length()){
//            if (x.val != null) n--;
//            x.val = null;
            x.deleteAllTranslations();
            x.isEnd = false;
        } else {
            System.out.println("delete("+x+", "+word+", "+d+")");
            System.out.println("Usuwanie "+word.charAt(d));
            char c = word.charAt(d);
            int index = alphabet.getCharIndex(c);
            x.arr[index] = delete(x.arr[index], word, d+1);
        }

        if (x.isEnd) return x;
        for (int i = 0; i<alphabet.getCharCount(); i++){
            if (x.arr[i] != null) return x;
        }
        return null;
    }
}
