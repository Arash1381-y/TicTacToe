package view.controllers;


import controller.GameWithFriend;
import javafx.animation.Transition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.GameInProgress;

import java.io.IOException;
import java.util.Objects;

public class GameWithFriendController {
    public GridPane gameChart;
    public Label playerTwo;
    public Label playerOne;
    public Label winner;
    int locationFinder = 0;
    Parent parent;


    public void putSign(MouseEvent event) {
        GameWithFriend game = GameInProgress.getGame();
        Rectangle rectangle = (Rectangle) event.getTarget();
        if (game.isHouseUsedBefore(rectangle))
            return;
        Image image = GameInProgress.getGame().getSign();
        ImageView imageView = new ImageView(image);
        rectangle.setFill(new ImagePattern(image));
        imageView.setFitHeight(69.0);
        imageView.setFitHeight(98.0);
        Transition transition = new Transition() {
            @Override
            protected void interpolate(double v) {
                v = 3;
                ((DropShadow) rectangle.getEffect()).setOffsetX(-10 * (1 - v));
                ((DropShadow) rectangle.getEffect()).setOffsetY(-10 * (1 - v));
                ((DropShadow) rectangle.getEffect()).setHeight(50 * (1 - v));
                ((DropShadow) rectangle.getEffect()).setWidth(50 * (1 - v));
                rectangle.setScaleX(v);
            }
        };
        transition.setOnFinished(actionEvent -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        });

        game.addRectangle(rectangle);
        game.setValueToHouse(GridPane.getRowIndex(rectangle), GridPane.getColumnIndex(rectangle));
        locationFinder++;
       if (game.checkForWinner()){
           winner.setStyle("   -fx-border-style: solid;\n" +
                   "    -fx-text-fill: black;\n" +
                   "    -fx-background-color:#fa89f9 ;");
           if (game.isPlayerOneWinner()){
               winner.setText(game.getPlayerOne().getName() + " WIN :)");
           }else{
               winner.setText(game.getPlayerTwo().getName() + " WIN :)");
           }
           gameChart.setDisable(true);
       }

       if (game.isDraw()){
           game.getPlayerOne().setDraws();
           game.getPlayerTwo().setDraws();;
           gameChart.setDisable(true);
           winner.setStyle(" fx-text-fill: blue; -fx-font-size: 15");
           winner.setText("we Have No Winner");
       }
    }


    public void switchToWelcomeScene(MouseEvent event) {
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/Welcome.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
