package add_edit_window;

import dictionary.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddEditWindowController {
    @FXML
    private Button returnButton;
    @FXML
    private MenuItem quitMenu;
    @FXML
    private MenuItem aboutMenu;
    @FXML
    private GridPane mainPane;
    @FXML
    private Button addWordButton;
    @FXML
    private MenuItem openDictionary;
    @FXML
    private TextField translationText;
    @FXML
    private TextField wordText;
    @FXML
    private ChoiceBox sourceLang;
    @FXML
    private ChoiceBox resultLang;

    @FXML
    private void initialize() {
        returnButton.setOnAction(this::onReturnButton);
        quitMenu.setOnAction(this::onQuitMenu);
        aboutMenu.setOnAction(this::onAboutMenu);
        addWordButton.setOnAction(this::onAddWordButton);
        openDictionary.setOnAction(this::onopenDictionary);
        sourceLang.getSelectionModel().select(0);
        resultLang.getSelectionModel().select(1);
    }


    private void onReturnButton(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../main_window/MainWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void onQuitMenu(ActionEvent e) {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }

    private void onAboutMenu(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O programie");
        alert.setHeaderText("Program stworzony w ramach projektu zespołowego. Wersja 1.0a");
        alert.setContentText("Sebastian Turlej, Piotr Walczak");
        alert.showAndWait();
    }

    private void onopenDictionary(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Komunikat");
        alert.setHeaderText("Plik/pliki załadowane pomyślnie");
        alert.showAndWait();
    }


    private void onAddWordButton(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Komunikat");
        //TODO
        //Jakaś lepsza metoda wyboru do czego dodajemy?
        //0-1: ang-pol


        if (sourceLang.getSelectionModel().isSelected(0) && resultLang.getSelectionModel().isSelected(1)){
            Dictionary.getEngDictionary().insert(wordText.getText().toLowerCase(), translationText.getText().toLowerCase());
            Dictionary.getPolishDictionary().insert(translationText.getText().toLowerCase(), wordText.getText().toLowerCase());
            alert.setHeaderText("Dodano do słownika");
            //1-0: pol-ang
        } else if (sourceLang.getSelectionModel().isSelected(1) && resultLang.getSelectionModel().isSelected(0)){
            Dictionary.getPolishDictionary().insert(wordText.getText().toLowerCase(), translationText.getText().toLowerCase());
            Dictionary.getEngDictionary().insert(translationText.getText().toLowerCase(), wordText.getText().toLowerCase());
            alert.setHeaderText("Dodano do słownika");
        } else {
            alert.setHeaderText("Brak wsparcia dla wybranego słownika");
        }
        alert.showAndWait();
    }

}

