package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO;
import util.Validator;

public class ForgetPasswordController implements Initializable {

	@FXML
	private TextField txtBoxEmailID;
	@FXML
	private ComboBox<String> cbSeqQuestion;
	@FXML
	private PasswordField txtQuePwdField, txtpwdBoxNewPassword;
	@FXML
	private Button btnSave, btnClose;
	@FXML
	private Label lblError;
	public String selectSecQue = null;
	public DAO dataAccess = new DAO();
	public Validator isValid = new Validator();
	public String newPassword = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<String> sQueList = new ArrayList<>();
		sQueList = dataAccess.selectSecurityQue();
		ObservableList<String> obSQueList = FXCollections.observableArrayList(sQueList);
		cbSeqQuestion.setItems(obSQueList);

	}

	@FXML
	private void clickOnCloseButton() {
		reset();
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();

	}

	@FXML
	private void clickOnSaveButton() {
		String email = txtBoxEmailID.getText();
		String secAnswer = txtQuePwdField.getText();
		newPassword = txtpwdBoxNewPassword.getText();

		if (dataAccess.isUserValid(email) && dataAccess.isEmailExistSecuritQue(email)) {
			if (isValid.validate(newPassword)) {
				if (isValid.validateEmail(email)) {
					boolean newPasswordStatus = dataAccess.forgetPassword(email, selectSecQue, secAnswer, newPassword);

					System.out.println("newPasswordStatus" + newPasswordStatus);
					if (newPasswordStatus) {
						reset();
						lblError.setText("");
						lblError.setText("Congratulation ! Passowrd has been changed. Please click on close");
					} else {
						lblError.setText("");
						lblError.setText("Security question and answer are not matched!");
						reset();

					}
				} else {
					lblError.setText("email ID format is not correct");
				}

			} else {
				reset();
				lblError.setText("");
				lblError.setText(
						"Password must contain one (special char from (@!$%) and alpha numeric with numeric ,small letter and capital letter and length 6-8)  ");
			}

		} else {
			lblError.setText("Sorry! email Id is not correct or security question is not set. Please contact admin!");

		}

	}

	@FXML
	private void selectcbSeqQuestion() {
		selectSecQue = cbSeqQuestion.getSelectionModel().getSelectedItem();

	}

	private void reset() {
		txtBoxEmailID.clear();
		txtpwdBoxNewPassword.clear();
		cbSeqQuestion.getSelectionModel().clearSelection();
		txtQuePwdField.clear();
	}

}
