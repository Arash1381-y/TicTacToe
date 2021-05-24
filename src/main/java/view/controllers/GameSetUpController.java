package view.controllers;


import controller.GameWithFriend;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.GameInProgress;
import model.LoginUser;
import model.Player;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class GameSetUpController {


    public Label NoLoginPlayer;
    public Label opponentNotFound;
    public VBox chart;
    public TextField opponentName;


    private Parent parent;

    {
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/Welcome.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToWelcomeScene(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToGame(MouseEvent event) throws IOException {
        String opponent = opponentName.getText();
        Player opponentPlayer;
        if (opponent.equals("")) {
            opponentNotFound.setStyle("-fx-text-fill: #ff0320;-fx-font-size: 12");
            opponentNotFound.setText("fill the field please!");
            return;
        } else {
            opponentPlayer = Player.getPlayerByName(opponent);
            if (opponentPlayer == null) {
                opponentNotFound.setStyle("-fx-text-fill: #ff0320;-fx-font-size: 12");
                opponentNotFound.setText("no opponent with this name");
                return;
            }

            if (LoginUser.getPlayer() == null) {
                opponentNotFound.setStyle("-fx-text-fill: #ff0320;-fx-font-size: 12");
                NoLoginPlayer.setText("you haven't login yet !!!");
                return;
            }
            if (LoginUser.getPlayer() == opponentPlayer){
                opponentNotFound.setStyle("-fx-text-fill: #ff0320;-fx-font-size: 12");
                NoLoginPlayer.setText("can't play with yourself !!!");
                return;
            }
        }
        GameWithFriend game;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userInterface/fxml/GameWithFriend.fxml"));
        parent = loader.load();
        GameWithFriendController controller = loader.getController();
        Random random = new Random();
        int a = random.nextInt();


        controller.playerTwo.setStyle("  -fx-border-style: solid;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-background-color:#fa89f9 ;");

        controller.playerOne.setStyle("  -fx-border-style: solid;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-background-color:#fa89f9 ;");


        if (a % 2 == 0) {
            game = new GameWithFriend(LoginUser.getPlayer(), opponentPlayer);
            controller.playerOne.setText("Player TWO : " + LoginUser.getPlayer().getName());
            controller.playerTwo.setText("Player ONE : " + opponentPlayer.getName());
        } else {
            game = new GameWithFriend(opponentPlayer, LoginUser.getPlayer());
            controller.playerOne.setText("Player TWO : " + opponentPlayer.getName());
            controller.playerTwo.setText("Player ONE : " + LoginUser.getPlayer().getName());
        }

        GameInProgress.setGame(game);

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setStyle("-fx-stroke: #e400b7; -fx-border-width: 10");
                rectangle.setHeight(66.0);
                rectangle.setArcHeight(5.0);
                rectangle.setArcWidth(5.0);
                rectangle.setWidth(102.0);
                rectangle.setFill(Paint.valueOf("WHITE"));
                rectangle.setOnMouseClicked(controller::putSign);
                controller.gameChart.add(rectangle, j, i);
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void removeDemo() {
        opponentName.setText("");
    }
}
