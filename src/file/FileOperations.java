package file;

import dictionary.Dictionary;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOperations {
    public static void addToDictionary() {
        try {
            File file = new File("dictionary.txt");
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                String word;
                String translationWord;
                word = in.nextLine();
                //Żeby uniknąć wyjątku wczytywania nieistniejącej następnej linii
                if (in.hasNextLine()) {
                    translationWord = in.nextLine();
                } else throw new IllegalArgumentException("Nie ma następnej linii!");
                System.out.println(word+" - "+translationWord);
                //Problem z dodawaniem słowa do Eng - paramter "word" powoduje "Niedozwolony znak w wyrazie"
                //Nawet przy parzystej liczbie wyrazów? Tak długo jak po usunięciu pliku?
                Dictionary.getEngDictionary().insert(word, translationWord);
                Dictionary.getPolishDictionary().insert(translationWord, word);
            }
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Komunikat");
            alert.setHeaderText("Brakujący wyraz w pliku słownika!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Komunikat");
            alert.setHeaderText("Błąd wczytywania pliku słownika!");
            alert.showAndWait();
        }
    }


    public static void saveDicitionary(String ang, String pol) throws IOException {

        FileWriter fileWriter = new FileWriter("dictionary.txt", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print("\n");
        printWriter.print(ang);
        printWriter.print("\n");
        printWriter.print(pol);
        printWriter.close();

    }


}



