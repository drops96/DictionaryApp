package dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

class TrieNode {
    TrieNode[] arr;
    boolean isEnd;
    private List<String> translations;

    TrieNode(int size) {
        arr = new TrieNode[size];
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

    void deleteAllTranslations(){
        translations.clear();
    }

    void deleteTranslation(String translation){
        if (!translations.remove(translation)){
            System.out.println("Błąd usuwania tłumaczenia!");
        }
    }

    ObservableList<String> getTranslationList(){
        return FXCollections.observableList(translations);
    }
}