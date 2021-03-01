package choices;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleText.setText("Choices");
        authorText.setText("By: Ricky");
        button.setText("Start Game");
        exitButton.setText("Exit Game");

    }

    public void ButtonClick(){
        button.setText("OUUUUU I got clicked");
    }

    public void ExitGame(){
        System.exit(1);
    }

    public void EasterEggHover(){
        backgroundColour.setStyle("-fx-background-color: red");
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

}
