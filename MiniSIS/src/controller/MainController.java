package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import application.*;

public class MainController {

    @FXML
    private BorderPane root;

    @FXML
    private Parent login;

    private Parent mainView;

    @FXML
    private LoginController loginController;

    public void initialize() {
        loginController.userProperty().addListener((obs, oldUser, newUser) -> {
            if (newUser == null) {
                root.setCenter(login);
                root.getScene().getWindow().sizeToScene();
            } else {
                if (mainView == null) {
                    loadMainView();
                }
                root.setCenter(mainView);
                root.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void loadMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/view/MenuBar.fxml"));
            mainView = loader.load();           
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
