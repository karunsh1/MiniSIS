package controller;

import controller.LoginController;
import java.io.IOException;
import java.net.URL;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import model.User;

public class MainController {
    @FXML
    private BorderPane root;
    @FXML
    private Parent login;
    private Parent mainView;
    @FXML
    private LoginController loginController;

    public void initialize() {
        this.loginController.userProperty().addListener((obs, oldUser, newUser) -> {
            if (newUser == null) {
                this.root.setCenter((Node)this.login);
                this.root.getScene().getWindow().sizeToScene();
            } else {
                if (this.mainView == null) {
                    this.loadMainView();
                	 
                }
                this.root.setCenter((Node)this.mainView);
                this.root.getScene().getWindow().sizeToScene();
            }
        }
        );
    }

    private void loadMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/MenuBar.fxml"));
            this.mainView = (Parent)loader.load();
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}