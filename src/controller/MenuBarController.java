package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import DTO.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.MenuItem;
import javafx.scene.Node;
import javafx.scene.Parent;
public class MenuBarController implements Initializable  {
   
	@FXML
	private StackPane showItemPane;
   
    @FXML
	private MenuItem menuitemSearchCourse;
   
	@FXML
	private void onSearchCourse() {
		BorderPane newLoadedPane = null;
		try {
			
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/MenuBar.fxml"));
		} catch (IOException e) {
			System.out.println("in catch");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showItemPane.getChildren().add(newLoadedPane);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
} 