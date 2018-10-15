import com.sun.org.apache.regexp.internal.RE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField SearchBarWord;

    @FXML
    private TextField SearchBarUrEngWord;

    @FXML
    private TextField SearchBarUrVieWord;

    @FXML
    public static final ObservableList<String> WordListobserList = FXCollections.observableArrayList();

    @FXML
    private Button AddToYourWord;

    @FXML
    ListView Wordlist = new ListView(WordListobserList);

    @FXML
    ArrayList<String> RecentWords = new ArrayList<>();

    @FXML
    public static final ObservableList<String> RecentListobserList = FXCollections.observableArrayList();

    @FXML
    ListView RecentList = new ListView(WordListobserList);

    @FXML
    private TextArea txtDefinition;

    /**
     * Hash map with keys as words, values as meaning
     */
    static HashMap<String, String> dictionary = new HashMap<>();

    public void SubmitUrEngWord(ActionEvent event) {
        String word = SearchBarUrEngWord.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Test text in Your Eng Word: " + word);
        alert.show();
    }

    public void SubmitUrVieWord(ActionEvent event) {
        String word = SearchBarUrVieWord.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Test text in your Vie Word: " + word);
        alert.show();
    }

    /**
     * Open an Alert when user type in an word that isn't in the dict
     */
    public void WordNotFound() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Word Not Found");
        alert.show();
    }

    /**
     * Open Alert to edit Meaning
     */
    public void EditMeaning(ActionEvent event){
        String wordToDefine = SearchBarWord.getText();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Thay đổi nghĩa của từ");
        dialog.setHeaderText("Bạn có chắc chắn muốn thay đổi không");
        dialog.setContentText("Nhập vào nghĩa mới: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String newMeaning =  result.get();
            dictionary.put(wordToDefine, newMeaning);
        }
    }

    @FXML
    void getDefinition() {
        try {
            txtDefinition.setWrapText(true);
            String wordToDefine;
            wordToDefine = SearchBarWord.getText();
            txtDefinition.setText("");
            txtDefinition.appendText(dictionary.get(wordToDefine));
            try {
                WriteData.WriteDataToRecentWord(wordToDefine);
            } catch (Exception e) {
                System.out.println("Word is already in the RecentWord file");
            }
        } catch (Exception e) {
            WordNotFound();
        }
    }

    @FXML
    void addRecentWord(String s) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readData.ReadDataFromFile(dictionary);
        readData.ReadDataFromRecent(RecentWords);
        for (String w : dictionary.keySet()) {
            WordListobserList.add(w);
        }
        SortedList<String> sortedList = new SortedList(WordListobserList);
        Wordlist.setItems(sortedList.sorted());

        for (String w : RecentWords)
            RecentListobserList.add(w);
        RecentList.setItems(RecentListobserList);
    }

    /**
     * Catch mouse clicked on an item of WordList
     */
    public void MouseClickOnWord() {
        Wordlist.setOnMouseClicked((EventHandler<? super MouseEvent>) arg0 -> {
            String w = Wordlist.getSelectionModel().getSelectedItem().toString();
            SearchBarWord.setText(w);
            getDefinition();
        });
    }

    public void MouseClickOnSearchBar() {
        SearchBarWord.setOnMouseClicked((EventHandler<? super MouseEvent>) arg0 -> {
            SearchBarWord.clear();
        });
    }
}