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
import model.DAO;
import util.Validator;

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
	public DAO dataAccess = new DAO();
	public Validator emailValidator = new Validator();
	
		
	@FXML
	private void clickOnSaveButton(){
		String emailID = txtBoxEmailID.getText();
		String newPassword = txtpwdBoxNewPassword.getText();
		
		if(emailValidator.validateEmail(emailID)){	
		
		if(dataAccess.isUserValid(emailID)){
			
			
			dataAccess.resetPassword(emailID, newPassword);
			reset();
			lblError.setText("Password has been reset succesfully !");
			
			
			
		}else{
			
			lblError.setText("This email ID does not exist in the portal!");
		}
		}else{
			
			lblError.setText("Please correct the email ID !");
		}
		
		
	}
	@FXML
	private void clickOnResetButton(){
		reset();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public void reset(){
		txtBoxEmailID.clear();
		txtpwdBoxNewPassword.clear();
		lblError.setText("");
	}

}
