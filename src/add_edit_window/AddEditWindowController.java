package add_edit_window;

import dictionary.Dictionary;
import file.FileOperations;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddEditWindowController {
    @FXML
    private Button searchButton;
    @FXML
    private ListView translationListView;
    @FXML
    private Button deleteTranslationButton;
    @FXML
    private Button deleteWordButton;
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
    private ChoiceBox deleteDictionary;
    @FXML
    private TextField wordToDelete;

    @FXML
    private void initialize() {
        returnButton.setOnAction(this::onReturnButton);
        quitMenu.setOnAction(this::onQuitMenu);
        aboutMenu.setOnAction(this::onAboutMenu);
        addWordButton.setOnAction(this::onAddWordButton);
        openDictionary.setOnAction(this::onopenDictionary);
        deleteWordButton.setOnAction(this::onDeleteButton);
        searchButton.setOnAction(this::onSearchButton);
        deleteTranslationButton.setOnAction(this::onDeleteTranslationButton);
        sourceLang.getSelectionModel().select(0);
        resultLang.getSelectionModel().select(1);
        deleteDictionary.getSelectionModel().select(0);
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
        //0-1: ang-pol
        if (wordText.getText().isEmpty() || translationText.getText().isEmpty()){
            alert.setHeaderText("Puste pole!");
        }
        else if (sourceLang.getSelectionModel().isSelected(0) && resultLang.getSelectionModel().isSelected(1)){
            boolean result1 = Dictionary.getEngDictionary().insert(wordText.getText().toLowerCase(), translationText.getText().toLowerCase());
            boolean result2;
            //Jeśli pierwszy wyraz nieprawidłowy, nie próbuj dodać w drugą stronę (bo mogłoby zadziałać - tłumaczenia to Stringi niekontrolowane)
            if (result1) {
                result2 = Dictionary.getPolishDictionary().insert(translationText.getText().toLowerCase(), wordText.getText().toLowerCase());
            } else result2 = false;
            if (result1 && result2){
                try{
                FileOperations.saveDicitionary(wordText.getText().toLowerCase(),translationText.getText().toLowerCase());
            }
                catch (IOException f){
                f.printStackTrace();
            }
                alert.setHeaderText("Dodano do słownika!");
            } else alert.setHeaderText("Błąd dodawania do słownika!");
            //1-0: pol-ang
        } else if (sourceLang.getSelectionModel().isSelected(1) && resultLang.getSelectionModel().isSelected(0)){
            boolean result1 = Dictionary.getPolishDictionary().insert(wordText.getText().toLowerCase(), translationText.getText().toLowerCase());
            boolean result2;
            if (result1){
                //Jak wyżej
                result2 = Dictionary.getEngDictionary().insert(translationText.getText().toLowerCase(), wordText.getText().toLowerCase());
            } else result2 = false;
            if (result1 && result2){
                try {
                    FileOperations.saveDicitionary(translationText.getText().toLowerCase(), wordText.getText().toLowerCase());
                }
                catch (IOException g){
                    g.printStackTrace();
                }
                alert.setHeaderText("Dodano do słownika!");
            } else alert.setHeaderText("Błąd dodawania do słownika!");
        } else {
            alert.setHeaderText("Brak wsparcia dla wybranego słownika");
        }
        alert.showAndWait();
    }

    private void onDeleteButton(ActionEvent e){
        //TODO
        //To trzeba też symetrycznie usuwać - tzn. z listy tłumaczeń w przeciwnym języku usunięte słowo
        try {
            String word = wordToDelete.getText().toLowerCase();
            if (deleteDictionary.getSelectionModel().isSelected(0)){
                //Znajdź wszystkie wyrazy których ten wyraz jest tłumaczeniem i usuń go z list
                for (String s: Dictionary.getEngDictionary().getTranslationsAsList(word)) {
                    Dictionary.getPolishDictionary().deleteTranslation(s, word);
                    //Co wtedy jeśli dany wyraz był jedynym tłumaczeniem któregoś? Jego też usuwamy?
                    //Ale co wtedy z polityką zostawiania ostatniego tłumaczenia?
                    //Na razie uznajemy że usuwamy to słowo bez tłumaczeń
                    if (Dictionary.getPolishDictionary().getTranslationsAsList(s).size() == 0){
                        Dictionary.getPolishDictionary().delete(s);
                    }
                }
                Dictionary.getEngDictionary().delete(word);

                FileOperations.removeFromDictionary(word, 0);
            } else {
                for (String s: Dictionary.getPolishDictionary().getTranslationsAsList(word)) {
                    if (Dictionary.getEngDictionary().getTranslationsAsList(s).size() == 0){
                        Dictionary.getEngDictionary().delete(s);
                    }
                    Dictionary.getEngDictionary().deleteTranslation(s, word);
                }
                Dictionary.getPolishDictionary().delete(word);
                FileOperations.removeFromDictionary(word, 1);
            }
            translationListView.getItems().clear();
            wordToDelete.setText("");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void loadTranslations(){
        ObservableList<String> result;
        if (deleteDictionary.getSelectionModel().isSelected(0)){
            result = Dictionary.getEngDictionary().getTranslationsAsList(wordToDelete.getText().toLowerCase());
        } else result = Dictionary.getPolishDictionary().getTranslationsAsList(wordToDelete.getText().toLowerCase());
        translationListView.getItems().clear();
        if (result != null){
            translationListView.getItems().addAll(result);
            translationListView.getSelectionModel().select(0);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat");
            alert.setHeaderText("Nie znaleziono słowa!");
            alert.showAndWait();
        }
    }

    private void onSearchButton(ActionEvent e){
        loadTranslations();
    }

    private void onDeleteTranslationButton(ActionEvent e){
        boolean result;
        String word = wordToDelete.getText().toLowerCase();
        String selected = (String)translationListView.getSelectionModel().getSelectedItem();
        if (translationListView.getItems().size() == 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Komunikat");
            alert.setHeaderText("Nie możesz usunąć jedynego tłumaczenia!");
            alert.showAndWait();
            return;
        }
        if (deleteDictionary.getSelectionModel().isSelected(0)){
            result = Dictionary.getEngDictionary().deleteTranslation(word, selected);
            //Co ze słowem, którego to mogło być jedyne tłumaczenie
            //TODO
            //Powinno usunąć to słowo, jeżeli usuneliśmy z listy to jako jego jedyne tłumaczenie
            if (Dictionary.getPolishDictionary().getTranslationsAsList(selected).size() == 1){
                Dictionary.getPolishDictionary().delete(selected);
            } else Dictionary.getPolishDictionary().deleteTranslation(selected, word);
            //Usuń z pliku konkretny rekord
            FileOperations.removeFromDictionary(word, selected);
        } else {
            result = Dictionary.getPolishDictionary().deleteTranslation(word, selected);
            if (Dictionary.getEngDictionary().getTranslationsAsList(selected).size() == 1){
                Dictionary.getEngDictionary().delete(selected);
            } else Dictionary.getEngDictionary().deleteTranslation(selected, word);
            //Tutaj na odwrót, bo w pliku zawsze ang-pol, a tu wybieramy ang wyraz jako tłumaczenie
            FileOperations.removeFromDictionary(selected, word);
        }
        if (!result){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Komunikat");
            alert.setHeaderText("Błąd usuwania tłumaczenia!");
            alert.showAndWait();
        } else loadTranslations();
    }
}

