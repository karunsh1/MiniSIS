package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ResetPasswordController implements Initializable{
		
	@FXML
	private BorderPane bPaneForgetPassword;
	@FXML
	private Button btnSave,btnReset;
	@FXML
	private  TextField txtBoxEmailID;
	@FXML 
	private PasswordField txtpwdBoxNewPassword;
	@FXML	
	private Label lblError;
		
	@FXML
	private void clickOnSaveButton(){
		
	}
	@FXML
	private void clickOnResetButton(){
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
