package controller;

import controller.LoginController;
import java.io.IOException;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


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
                this.root.setTop(this.login);
                this.root.getScene().getWindow().sizeToScene();
                
               
            } else {
                if (this.mainView == null ) {
                	
                   this.loadMainView();            
                	 
                }else{
                	
                	this.loadMainView();
                }
                this.root.setTop(this.mainView);
                this.root.getScene().getWindow().sizeToScene();
            }
        }
        );
    }

    private void loadMainView() {
        try {
        	FXMLLoader loader=null;
        	
        	loader= new FXMLLoader(this.getClass().getResource("/view/MenuBar.fxml"));        	
            this.mainView = (Parent)loader.load();    
            MenuBarController controller = loader.getController();             
            controller.userProperty().bindBidirectional(
                    loginController.userProperty());
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}