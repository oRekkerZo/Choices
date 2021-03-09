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
            new Question("The End",0,

                    "There's nothing else to do now. Have you beaten the game? Have you seen all the possible outcomes? Who knows?",

                    "/resources/pictures/harold.jpg",
                    "Aight bro",2,"Let's restart",2,"GET ME OFF THIS SCREEN!",2,"/resources/Game Scene.wav"),

            new Question("You're dead bro",1,

                    "Unlucky m8, you dead af. Choose wisely next time.",

                    "/resources/pictures/you died.gif",
                    "Oh no...",0,"Nice!",0,"What do?",0,"/resources/you died.mp3"),

            new Question("Welcome to the Game",2,

                    "This game is about making your way to go to 7-11. Your starting point is from your own home and you have to make choices on how you will get there. Enjoy, and good luck!",

                    "/resources/pictures/7-Eleven.jpg",
                    "Okay, let's go!",3,"No, I don't want to",0,"Whatever bro",3,"/resources/Game Scene.wav"),

            new Question("Leaving Home",3,

                    "You're at the front door of your house, about to leave, what method of transport are you using to get to 7-11?",

                    "/resources/pictures/openDoor.png",
                    "I can walk.",5,"I'll use my bicycle.",11,"I'll drive the car.",4,"/resources/Game Scene.wav"),

            new Question("The Car Isn't Here!",4,

                    "Seems like one of your family members have taken the car out for something... Either way, you can't use the car now!",

                    "/resources/pictures/dude.jpg",
                    "Oh well",3,"Guess I won't drive",3,"It is what it is",3,"/resources/Game Scene.wav"),

            new Question("You Begin Walking",5,

                    "You're leaving the house now by walking, but what path will you take to go to 7-11?",

                    "/resources/pictures/openDoor.png",
                    "Middle of the road",6,"Sidewalk",7,"Back roads from the house",8,"/resources/Game Scene.wav"),

            new Question("You got hit by a car!",6,

                    "Going in the middle of the road just to get to 711 was a terrible idea. Why did you think of this?",

                    "/resources/pictures/car hit.gif",
                    "Oh Shiiii",1,"Ouch",1,"So what?",1,"/resources/FourthQuestion.wav"),

            new Question("Got To 711 Using The Sidewalk",7,

                    "You walk on the sidewalks like the innocent person that you are. You take your time in the hot sun, walking without any shades and make it to 711 after 1 whole hour!",

                    "/resources/pictures/7-Eleven.jpg",
                    "Nice!",0,"I don't care",0,"Well at least I made it here safe and sound.",0,"/resources/Game Scene.wav"),

            new Question("You Encounter Angry Dogs!",8,

                    "Many dogs see you on the back roads and now wants to chase you down! What do you do!?!?",

                    "/resources/pictures/dog chase.jpg",
                    "Pet the dogs",1,"Run",1,"Chase them back with a stick",9,"/resources/FourthQuestion.wav"),

            new Question("It worked! Dogs Are Retreating!",9,

                    "Using a stick and chasing them back seem to have done the trick! The dogs are running away! Time to go back to what you were doing.",

                    "/resources/pictures/stick dog.jpg",
                    "Hooray!",10,"2 EZ",10,"Dayum I am smart",10,"/resources/FourthQuestion.wav"),

            new Question("You Made It To 711 In 30 Minutes!",10,

                    "It only took you 30 minutes to get to 711 by going on the back roads because of how good of a short-cut it is. Was risking your life with the dogs worth it? Probably!",

                    "/resources/pictures/7-Eleven.jpg",
                    "Now I go buy stuff",0,"Nice!",0,"I forgot what I was here for...",0,"/resources/Game Scene.wav"),

            new Question("Got On Bicycle",11,

                    "You got on your bicycle without checking its state if it's in a ride-able condition. Something happens as you start riding it...\nWhat do you do?!?!?!?",

                    "/resources/pictures/bicycle.gif",
                    "Try to land it",1,"Jump off!",3,"Raise it higher!",1,"/resources/FourthQuestion.wav"),
    };

    Question currentQuestion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        questionDescription.setWrapText(true);
        currentQuestion = questions[2];

        try {
            SetDisplayQuestion(currentQuestion);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        Main.songPlayer.stop();
        try {
            Main.mainSong = new Media(getClass().getResource("/resources/Game Scene.wav").toURI().toString());
            Main.currentPlayingSong = "/resources/Game Scene.wav";
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

        try {
            SetDisplayQuestion(currentQuestion);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void SetDisplayQuestion(Question question) throws URISyntaxException {

        if(!question.currentSong.equalsIgnoreCase(Main.currentPlayingSong)){
            Main.mainSong = new Media(getClass().getResource(question.currentSong).toURI().toString());
            Main.currentPlayingSong = question.currentSong;
            Main.songPlayer = new MediaPlayer(Main.mainSong);
            Main.songPlayer.setOnEndOfMedia(() -> Main.songPlayer.seek(Duration.ZERO));
            Main.songPlayer.setVolume(Main.volume);
            if(Main.musicIsPlaying){
                Main.songPlayer.play();
            }
        }

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

}
