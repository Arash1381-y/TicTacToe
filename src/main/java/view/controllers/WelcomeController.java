package view.controllers;


import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class WelcomeController extends Application {

    public Label Title;

    Parent parent;
    Scene scene;
    Stage stage;

    public void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/Welcome.fxml")));
            primaryStage.setTitle("Tic Tac Toe");
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/LoginUser.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRegisterScene(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/Register.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScoreBoard(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/ScoreBoard.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 700, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGameSetUp(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/GameSetUp.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent, 600, 500);
        stage.setScene(scene);
        stage.show();
    }


    public void exit() throws IOException {
        FileWriter writer = new FileWriter("Json.Json");
        ArrayList<Player> allPlayers = Player.getAllPlayer();
        writer.write(new Gson().toJson(allPlayers));
        writer.close();
        System.exit(0);
    }
}
