package main_window;

import dictionary.Dictionary;
import file.GetFromFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindowController {
    @FXML
    private Button addButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button quitButton;
    @FXML
    private MenuItem quitMenu;
    @FXML
    private MenuItem openDictionary;
    @FXML
    private MenuItem aboutMenu;

    @FXML
    private void initialize() {
            addButton.setOnAction(this::onAddButton);
            searchButton.setOnAction(this::onSearchButton);
            quitButton.setOnAction(this::onQuitButton);
            quitMenu.setOnAction(this::onQuitMenu);
            aboutMenu.setOnAction(this::onAboutMenu);
            openDictionary.setOnAction(this::onopenDictionary);
            Dictionary.getEngDictionary().insert("mouse", "mysz");
            Dictionary.getEngDictionary().insert("car", "samochod");
            Dictionary.getEngDictionary().insert("crow", "wrona");
            Dictionary.getEngDictionary().insert("elephant", "slon");
            Dictionary.getEngDictionary().insert("yeti", "yeti");
            Dictionary.getEngDictionary().insert("cat", "kot");
            Dictionary.getPolishDictionary().insert("kot", "cat");
            Dictionary.getPolishDictionary().insert("kot", "kitten");
            GetFromFile.addAngToPol();
            GetFromFile.addPolToAng();


    }

    private void onAddButton(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../add_edit_window/AddEditWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void onSearchButton(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../search_window/SearchWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) searchButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void onQuitButton(ActionEvent e) {
       Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
    private void onQuitMenu(ActionEvent e) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    private void onopenDictionary(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Komunikat");
        alert.setHeaderText("Plik/pliki załadowane pomyślnie");
        alert.showAndWait();
    }
    private void onAboutMenu(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O programie");
        alert.setHeaderText("Program stworzony w ramach projektu zespołowego. Wersja 1.0a");
        alert.setContentText("Sebastian Turlej, Piotr Walczak");
        alert.showAndWait();
    }
}
