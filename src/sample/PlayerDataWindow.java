package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayerDataWindow {

    private static final int SCENE_WIDTH = 320;
    private static final int SCENE_HEIGHT = 240;

    private Stage playerDataWindow;
    private Scene playerDataScene;
    private TilePane playerDataLayout;

    private Button okButton;
    private Button cancelButton;

    private TextField nicknameField;

    public PlayerDataWindow(){
        playerDataWindow = new Stage();
        playerDataWindow.setResizable(false);

        okButton = new Button("Add and Start", ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/acceptIcon.png"),64,64));
        okButton.setStyle("-fx-background-color: #F9E79F");
        cancelButton = new Button("Cancel", ImageLoader.resizeImage(ImageLoader.loadImageFromFile("res/buttonIcons/cancelIcon.png"),64,64));
        cancelButton.setStyle("-fx-background-color: #F9E79F");

        okButton.setMinSize(64, 64);
        cancelButton.setMinSize(64, 64);

        playerDataLayout = new TilePane();
        playerDataLayout.styleProperty().set("-fx-background-color: #5D6D7E");

        nicknameField = new TextField("");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(okButton, cancelButton);
        hbox.setSpacing(10);

        Label nicknameLabel = new Label("Type nick");
        nicknameLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));

        nicknameField.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        nicknameLabel.setLabelFor(nicknameField);

        playerDataLayout.setAlignment(Pos.CENTER);
        playerDataLayout.setVgap(5);
        playerDataLayout.getChildren().addAll(nicknameLabel, nicknameField, hbox);

        cancelButton.setOnAction(actionEvent -> {
            playerDataWindow.close();
        });

        okButton.setOnAction(actionEvent -> {
            if(checkPlayerNicknameNotEmpty()){
                PlayerData playerData = new PlayerData();
                playerData.setPlayerNickname(nicknameField.getText());
                playerDataWindow.close();

                GameMainWindow gameMainWindow = new GameMainWindow();
                gameMainWindow.displayMainGameWindow();
            }
            else{
                nicknameField.setPromptText("Type in your nick!");
                nicknameField.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20));
                nicknameField.setStyle("-fx-background-color: #FF0000");
            }
        });

    }

    public void displayDataWindow(){
        playerDataScene = new Scene(playerDataLayout, SCENE_WIDTH, SCENE_HEIGHT);
        playerDataWindow.setScene(playerDataScene);
        playerDataWindow.initModality(Modality.APPLICATION_MODAL);

        playerDataWindow.showAndWait();
    }

    public boolean checkPlayerNicknameNotEmpty(){
        if(nicknameField.getText().equals("")){
            return false;
        }
        else{
            return true;
        }
    }
}
