package view.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {
    public PasswordField userPass;
    public TextField username;
    public Label error;

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

    public void makeNewUser() {
        String name;
        String password;
        name = username.getText();
        password = userPass.getText();


        Player player = Player.getPlayerByName(name);
        if (player != null) {
            System.out.println(player.getName());
            error.setStyle("-fx-text-fill: #ff0909;-fx-font-size: 14");
            error.setText(name + " used before try a new one");
            username.clear();
            userPass.clear();
            return;
        }
        if (!name.equals("") && !password.equals("")) {
            new Player(name, password);
            error.setStyle("-fx-text-fill: rgba(0,90,26,0.99); -fx-font-size: 14");
            error.setText("player registered successfully");
            username.clear();
            userPass.clear();
        } else {
            error.setStyle("-fx-text-fill: #ff0909; -fx-font-size: 14");
            error.setText("you must fill username and password !!!!");
        }
    }
}
