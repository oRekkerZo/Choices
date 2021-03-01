package choices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static MediaPlayer player;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Media media = new Media(getClass().getResource("/resources/Nyan Cat [original].mp3").toURI().toString());
        player = new MediaPlayer(media);
        player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));

        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Choices");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
