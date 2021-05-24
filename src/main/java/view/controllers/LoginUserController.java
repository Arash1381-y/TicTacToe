package view.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.LoginUser;
import model.Player;

import java.io.IOException;
import java.util.Objects;

public class LoginUserController {

    public AnchorPane anchorPane;
    public Label error;
    public VBox chart;
    public TextField userPass;
    public TextField username;
    private Parent parent;

    {
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/userInterface/fxml/Welcome.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToWelcomeScene(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void showUsers() {
        TextArea text = new TextArea();
        StringBuilder usersName = new StringBuilder();
        int itrator = 1;
        if (Player.getAllPlayer().size() == 0) {
            usersName.append("no player");
        } else {
            for (Player player : Player.getAllPlayer()) {
                usersName.append(itrator).append(") ").append(player.getName()).append("\n");
                itrator++;
            }
        }
        text.setText(usersName.toString());
        text.setStyle("-fx-fill: #060622");
        text.setStyle("-fx-font-size: 13");

        text.setPrefWidth(150);
        text.setEditable(false);
        anchorPane.getChildren().add(text);

    }


    public void makeNewUser() {
        String name;
        String password;
        name = username.getText();
        password = userPass.getText();
        if (name.equals("") || password.equals("")){
            error.setText("please fill the form first!!");
            return;
        }
        for (Player player : Player.getAllPlayer()) {
            if (player.getName().equals(name)) {
                if (player.isPasswordCorrect(password)) {
                    LoginUser.setCurrentPlayer(player);
                    error.setStyle("-fx-text-fill: #04ff00;-fx-font-size: 12");
                    error.setText("user login successfully !!!!");
                    username.clear();
                    userPass.clear();

                    return;
                } else {
                    error.setStyle("-fx-text-fill: #ff0909;-fx-font-size: 12");
                    error.setText("incorrect password !!!!");
                }
                return;
            }
        }
        error.setStyle("-fx-text-fill: #ff0909;-fx-font-size: 12");
        error.setText("no user find !!!");
        username.clear();
        userPass.clear();
    }
}
