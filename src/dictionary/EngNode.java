package dictionary;

import java.util.ArrayList;
import java.util.List;

class EngNode {
    EngNode[] arr;
    boolean isEnd;
    private List<String> translations;

    EngNode() {
        //26 bo tyle ma angielski alfabet
        arr = new EngNode[26];
        translations = new ArrayList<>();
    }

    String getTranslations(){
        StringBuilder translationsListed = new StringBuilder();
        int index = 1;
        for (String s: translations) {
            translationsListed.append(index);
            translationsListed.append(". ");
            translationsListed.append(s);
            translationsListed.append("\n");
            index++;
        }
        return translationsListed.toString();
    }

    void addTranslation(String translation){
        //TODO
        //Te tłumaczenia nie powinny być sortowane alfabetycznie? Na razie są w kolejności dodawania
        //Sprawdz czy nie ma juz na liscie
        if (! translations.contains(translation)) {
            //Dodaj do listy
            translations.add(translation);
        }
    }

}
