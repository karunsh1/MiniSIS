package controller;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.Users;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DAO;
import util.PasswordValidator;
import util.Singleton;

public class ChangePasswordController implements Initializable {

	private final ObjectProperty<Users> user = new SimpleObjectProperty<>();

	public final ObjectProperty<Users> userProperty() {
		return this.user;
	}

	public final Users getUser() {
		return this.userProperty().get();
	}

	public final void setUser(final Users user) {
		this.userProperty().set(user);

	}

	@FXML
	private BorderPane bPaneChangePassword;
	@FXML
	private PasswordField txtpwdOldField, txtnewPwdField;
	@FXML
	private Button btnSavePassword, btnChangePWDclose;
	@FXML
	private Label lblError;
	public String email = Singleton.getInstance().getEmailID();
	public DAO dataAccess = new DAO();
	public PasswordValidator validatePWD = new PasswordValidator();

	@FXML
	private void clickOnSaveButton() {
		String oldPasswword = txtpwdOldField.getText();
		String newPassword = txtnewPwdField.getText();
		if(validatePWD.validate(newPassword)){
			String alertText = dataAccess.changeUserPassword(email, oldPasswword, newPassword);
			lblError.setText(alertText);
		}else{
			lblError.setText("Password must contain one (special char from (@!$%) and alpha numeric with numeric ,small letter and capital letter and length 6-8)  ");
		}
		

		System.out.println("result " + email);

	}

	@FXML
	private void clickOnCloseButton() {
		Stage stage = (Stage) btnChangePWDclose.getScene().getWindow();
		stage.close();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
