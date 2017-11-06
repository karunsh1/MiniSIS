package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ViewCGPAController implements Initializable {
	
	@FXML
	private TextField txtSearchStudentID;
	@FXML
	private Button btnSearchViewCGPA;
	@FXML
	private HBox hBoxCGPADetail;
	@FXML
	private Label lblStudentName;
	@FXML
	private Label lblStudentCareer;
	@FXML
	private Label lblStudentProgram;
	@FXML
	private Label lblStudentCGP;
	
	@SuppressWarnings("unused")
	private void clickOnBtnSearchViewCGPA(){
		
	}
	
	
	public void initialize(URL location, ResourceBundle resources){
		
	}

}
