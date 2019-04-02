package search_window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    private void initialize() {
        returnButton.setOnAction(this::onReturnButton);
        quitMenu.setOnAction(this::onQuitMenu);
        aboutMenu.setOnAction(this::onAboutMenu);
        openDictionary.setOnAction(this::onopenDictionary);


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

}