package choices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    ImageView sceneImage;

    @FXML
    Button answer01;

    @FXML
    Button answer02;

    @FXML
    Button answer03;

    @FXML
    Text questionTitle;

    @FXML
    TextArea questionDescription;


    Question[] questions = {
            new Question("Welcome to the Game",0,"Introduction to the game.","/resources/pictures/7-Eleven.jpg",
                    "1st Question",1,"2nd Question",2,"3rd Question",3),

            new Question("1st Question",1,"Introduction to the game.","/resources/pictures/7-Eleven.jpg",
                    "2nd Question",2,"3rd Question",3,"4th Question",4),

            new Question("2nd Question",2,"Introduction to the game.","/resources/pictures/7-Eleven.jpg",
                    "3rd Question",3,"4th Question",4,"Final Question",5),

            new Question("3rd Question",3,"Introduction to the game.","/resources/pictures/7-Eleven.jpg",
                    "4th Question",4,"Final Question",5,"Final Question",5),

            new Question("4th Question",4,"Introduction to the game.","/resources/pictures/7-Eleven.jpg",
                    "Final Question",5,"Final Question",5,"Final Question",5),

            new Question("Final Question",5,"Introduction to the game.","/resources/pictures/7-Eleven.jpg",
                    "The End",0,"The End",0,"The End",0)
    };

    Question currentQuestion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentQuestion = questions[0];

        SetDisplayQuestion(currentQuestion);



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


       // ChangingScenes();
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

    public void ChangeQuestion(Button button){
        int nextQuestionID = Integer.parseInt(button.getId());

        currentQuestion = questions[nextQuestionID];

        SetDisplayQuestion(currentQuestion);

    }

    public void SetDisplayQuestion(Question question){

        questionTitle.setText(question.eventTitle);
        questionDescription.setText(question.descriptionText);
        sceneImage.setImage(new Image(question.imagePath));

        answer01.setText(question.answer01Text);
        answer01.setId(String.valueOf(question.answer01Destination));
        answer01.setOnAction(actionEvent -> ChangeQuestion(answer01));

        answer02.setText(question.answer02Text);
        answer02.setId(String.valueOf(question.answer02Destination));
        answer02.setOnAction(actionEvent -> ChangeQuestion(answer02));

        answer03.setText(question.answer03Text);
        answer03.setId(String.valueOf(question.answer03Destination));
        answer03.setOnAction(actionEvent -> ChangeQuestion(answer03));
    }

//    public void ChangingScenes(){
////        sceneImage.getStylesheets().add(getClass().getResource("../resources/menu pictures.css").toExternalForm());
////
////        sceneImage.getStyleClass().add("gameplayScene");
////        sceneImage.setStyle("-fx-image: gameplayScene");
//
//          sceneImage.setImage(new Image("/resources/pictures/openDoor.png"));
//    }
}
