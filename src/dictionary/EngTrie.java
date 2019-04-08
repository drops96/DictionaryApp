package dictionary;

public class EngTrie {
    EngNode root;

    EngTrie() {
        root = new EngNode();
    }

    public void insert(String word, String translation) {
        EngNode p = root;
        //Dla kazdej litery w wyrazie
        for(int i=0; i<word.length(); i++){
            //Weź obecną literę
            char c = word.charAt(i);
            //Oblicz jej indeks w tablicy referencji
            int index = c-'a';
            if(p.arr[index]==null){
                //Jeśli pusta referencja, utworz nowy obiekt i referencja na niego
                EngNode temp = new EngNode();
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
        p.translation = translation;
    }

    public String search(String word) {
        EngNode p = searchNode(word);
        if(p==null){
            return "Nie znaleziono!";
        }else{
            if(p.isEnd)
                return p.translation;
        }
        return null;
    }

    public EngNode searchNode(String s){
        EngNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
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
}
