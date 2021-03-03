package choices;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {

    @FXML
    TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String aboutText = "Game Name: Choices\n"+
                "Developer: RekkerZ\n"+
                "Github Link: https://github.com/oRekkerZo/Choices\n"+
                "Thank you for checking out this game!";

        textArea.setText(aboutText);
    }

    public void Close(){
        Stage stage = (Stage) textArea.getScene().getWindow();
        stage.close();
    }
}
