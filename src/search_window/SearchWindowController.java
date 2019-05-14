package search_window;

import dictionary.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SearchWindowController {
    @FXML
    private Button returnButton;
    @FXML
    private MenuItem quitMenu;
    @FXML
    private MenuItem aboutMenu;
    @FXML
    private MenuItem openDictionary;
    @FXML
    private Button searchButton;
    @FXML
    private TextField wordText;
    @FXML
    private TextArea translationArea;
    @FXML
    private ChoiceBox lang;

    @FXML
    private void initialize() {
        returnButton.setOnAction(this::onReturnButton);
        quitMenu.setOnAction(this::onQuitMenu);
        aboutMenu.setOnAction(this::onAboutMenu);
        openDictionary.setOnAction(this::onopenDictionary);
        searchButton.setOnAction(this::onSearch);
        lang.getSelectionModel().select(0);

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

    private void onSearch(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Komunikat");
        //0: ang-pol
        if (wordText.getText().isEmpty()){
            alert.setHeaderText("Puste pole");
            alert.showAndWait();
        }
        else if (lang.getSelectionModel().isSelected(0)){
            translationArea.setText(Dictionary.getEngDictionary().search(wordText.getText().toLowerCase()));
        }
        //1: pol-ang
        else if (lang.getSelectionModel().isSelected(1)){
            translationArea.setText(Dictionary.getPolishDictionary().search(wordText.getText().toLowerCase()));
        }else {
            alert.setHeaderText("Brak wsparcia dla wybranego słownika");
            alert.showAndWait();
        }
    }
}