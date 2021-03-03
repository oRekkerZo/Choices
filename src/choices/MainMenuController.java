package choices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    Text titleText;

    @FXML
    Text authorText;

    @FXML
    Button button;

    @FXML
    Button exitButton;

    @FXML
    Pane backgroundColour;

    @FXML
    CheckBox checkBox;

    @FXML
    Slider slider;

    @FXML
    Text volumeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleText.setText("Choices To Get To 7-11");
        authorText.setText("By: Ricky");
        button.setText("Start Game");
        exitButton.setText("Exit Game");

        Main.songPlayer.stop();

        try {
            Main.mainSong = new Media(getClass().getResource("/resources/Main Menu.wav").toURI().toString());
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

    }

    public void ExitGame(){
        System.exit(1);
    }

    public void EasterEggHover(){
        backgroundColour.setStyle("-fx-background-color: red");
        Main.player.setVolume(0.15);
        Main.player.play();
    }
    public void EasterEggUnhover(){
        backgroundColour.setStyle("-fx-background-color: white");
        Main.player.pause();
    }
    public void EasterEggColourChanges(){
        String[] colours = {"violet", "indigo", "blue", "green", "yellow", "orange", "red"};
        int random = new Random().nextInt(colours.length);
        String selectedColour = colours[random];

        backgroundColour.setStyle("-fx-background-color: "+selectedColour);
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

    @FXML
    private void StartGame() throws IOException {
        Main.sevenElevenSound.seek(Duration.ZERO);
        Main.sevenElevenSound.setVolume(0.15);
        Main.sevenElevenSound.play();

        AnchorPane gamePane = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        backgroundColour.getChildren().setAll(gamePane);

    }
}
