package app;

import dictionary.Dictionary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//TODO
//Pierwsze podejście - operować na drzewie i zrzucić je do pliku
//1.Metody do usuwania słowa ze słownika i słowa z listy tłumaczeń danego słowa (String word)
//2.Test metod
//3.Interfejs do wyszukiwania i usuwania
//4.Podpięcie metod
//5.Obsługa błędów
//6.Test interfejsu
//7.Zabezpieczenie pliku przed edycją

//Drugie podejście - operować bezpośrednio na pliku i załadować go później do pamięci
//

public class DictionaryApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Słownik");
        Parent root = FXMLLoader.load(getClass().getResource("../main_window/MainWindow.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
