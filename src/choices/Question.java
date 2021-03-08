package choices;

public class Question {

    String eventTitle, descriptionText, answer01Text, answer02Text, answer03Text, imagePath,currentSong;
    int eventID, answer01Destination, answer02Destination, answer03Destination;

    public Question(String eventTitle, int eventID, String descriptionText, String imagePath, String answer01Text, int answer01Destination, String answer02Text, int answer02Destination, String answer03Text, int answer03Destination,String currentSong){
        this.eventTitle = eventTitle;
        this.eventID = eventID;
        this.descriptionText = descriptionText;
        this.imagePath = imagePath;
        this.answer01Text = answer01Text;
        this.answer01Destination = answer01Destination;
        this.answer02Text = answer02Text;
        this.answer02Destination = answer02Destination;
        this.answer03Text = answer03Text;
        this.answer03Destination = answer03Destination;
        this.currentSong = currentSong;
    }

}
