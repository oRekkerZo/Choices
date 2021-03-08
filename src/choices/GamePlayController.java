package choices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable {

    @FXML
    Pane gamePane;

    @FXML
    CheckBox checkBox;

    @FXML
    Slider slider;

    @FXML
    Text volumeText;

    @FXML
    Pane scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Main.songPlayer.stop();

        try {
            Main.mainSong = new Media(getClass().getResource("/resources/Game Scene.wav").toURI().toString());
            Main.songPlayer = new MediaPlayer(Main.mainSong);
            Main.songPlayer.setOnEndOfMedia(() -> Main.songPlayer.seek(Duration.ZERO));
            Main.songPlayer.setVolume(Main.volume);
            if(Main.musicIsPlaying){
                Main.songPlayer.play();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //mute checkbox
        if(!Main.musicIsPlaying){
            checkBox.setSelected(true);
        }else{
            checkBox.setSelected(false);
        }



        //volume slider
        slider.setValue(Main.songPlayer.getVolume());

        volumeText.setText(String.format("%.2f",slider.getValue()));
        slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                volumeText.setText(String.format("%.2f",slider.getValue()));

                Main.volume = slider.getValue();

                Main.songPlayer.setVolume(slider.getValue());
                Main.sevenElevenSound.setVolume(slider.getValue());
                Main.player.setVolume(slider.getValue());

            }
        });


        ChangingScenes();
    }

    public void Mute(){
        if(checkBox.isSelected()){
            Main.songPlayer.stop();
            Main.musicIsPlaying = false;
        }else{
            Main.songPlayer.play();
            Main.musicIsPlaying = true;
        }
    }

    public void ReturnMenu() throws IOException {
        AnchorPane menuPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        gamePane.getChildren().setAll(menuPane);

    }

    public void AboutPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane about = loader.load(getClass().getResource("About.fxml").openStream());

        Scene scene = new Scene(about);
        Stage newStage = new Stage();
        newStage.setTitle("About Choices");
        newStage.setScene(scene);
        newStage.setAlwaysOnTop(true);

        gamePane.setDisable(true);

        newStage.showAndWait();

        gamePane.setDisable(false);
    }

    public void ChangingScenes(){

        scene.getStylesheets().add(getClass().getResource("../resources/menu pictures.css").toExternalForm());
        scene.getStyleClass().add("gameplayScene");
        scene.setStyle("-fx-image: gameplayScene");

    }
}
