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
import javafx.scene.layout.BorderPane;
import model.DAO;
import util.Singleton;

public class AddSecurityQuestionController implements Initializable {

	@FXML
	private BorderPane bPaneSetSecurityQuestion;
	@FXML
	private PasswordField txtpwdOldField, txtQuePwdField;
	@FXML
	private ComboBox<String> cbSecQuestion;
	@FXML
	private Label lblError;
	@FXML
	private Button btnSetPwd, btnResetAll, btnUpdateSQ;
	public String email = Singleton.getInstance().getEmailID();
	public DAO dataAccess = new DAO();
	public String setSecQue = null;

	@FXML
	private void clickOnUpdateButton() {
		String secQueAns = txtQuePwdField.getText();
		String oldPwd = txtpwdOldField.getText();

		boolean statusSetSecQue = dataAccess.addSecurityQueAnswer(oldPwd, setSecQue, secQueAns, email);
		if (statusSetSecQue) {
			lblError.setText("Congrats! You have updated the security Question  and its answer Successfully");
		} else {
			lblError.setText("Please enter correct password to update");
		}

	}

	@FXML
	private void clickOnSaveButton() {

		String secQueAns = txtQuePwdField.getText();
		String oldPwd = txtpwdOldField.getText();

		boolean statusSetSecQue = dataAccess.addSecurityQueAnswer(oldPwd, setSecQue, secQueAns, email);
		if (statusSetSecQue) {
			reset();
			lblError.setText("Congrats! You have set the security Question Successfully");
			
		} else {
			lblError.setText("Please enter correct password");
		}

	}

	@FXML
	private void selectcbSecQuestion() {
		setSecQue = cbSecQuestion.getSelectionModel().getSelectedItem();

	}

	@FXML
	private void clickOnResetButton() {

		reset();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		ArrayList<String> sQueList = new ArrayList<>();
		sQueList = dataAccess.selectSecurityQue();
		ObservableList<String> obSQueList = FXCollections.observableArrayList(sQueList);
		cbSecQuestion.setItems(obSQueList);
		if (dataAccess.isEmailExistSecuritQue(email)) {
			btnSetPwd.visibleProperty().set(false);
			btnUpdateSQ.visibleProperty().set(true);

		} else {
			btnSetPwd.visibleProperty().set(true);
			btnUpdateSQ.visibleProperty().set(false);
		}

	}

	public void reset() {
		cbSecQuestion.getSelectionModel().clearSelection();
		txtpwdOldField.clear();
		txtQuePwdField.clear();
		lblError.setText("");
	}

}
