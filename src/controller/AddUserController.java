package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.DAO;
import util.Singleton;
import util.Validator;

public class AddUserController implements Initializable {

	@FXML
	private Button btnSave, btnReset;
	@FXML
	private BorderPane bPaneAddUser;
	@FXML
	private Label lblError, lblEmp_ID, lblProgram, lblMobiileNo, lblCareerName;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private TextField  txtEmailID, txtFirstName, txtLastName, txtEmpID, txtMobile;
	@FXML
	private TextArea TxtAreaAddress;
	@FXML
	private ComboBox<String> cbProgram, cbCareerName, cbUserType;
	public Validator validateEmail = new Validator();
	public DAO dataAccess = new DAO();
	public String userType = Singleton.getInstance().getUserType().getText();
	public String selectType = null;
	public String first_Name = null;
	public String last_Name = null;
	public String career_Name= null;
	public String programName = null;
	public String address = null;
	public String emp_ID = null;
	public String mobile_No = null;
	

	@FXML
	private void SelectCareerName() {
		career_Name = cbCareerName.getSelectionModel().getSelectedItem().toString();
		

	}

	@FXML
	private void selectProgram() {
		programName = cbProgram.getSelectionModel().getSelectedItem().toString();

	}

	@FXML
	private void selectUserType() {

		selectType = cbUserType.getSelectionModel().getSelectedItem().toString();
		selectType = selectType.substring(0,selectType.indexOf(","));		
		if (selectType.contains("1")) {
			lblEmp_ID.visibleProperty().set(false);
			txtEmpID.visibleProperty().set(false);
			cbProgram.visibleProperty().set(true);
			cbCareerName.visibleProperty().set(true);
			txtMobile.visibleProperty().set(true);
			lblMobiileNo.visibleProperty().set(true);
			lblCareerName.visibleProperty().set(true);
			lblProgram.visibleProperty().set(true);

		} else if (selectType.contains("3")) {
			lblEmp_ID.visibleProperty().set(true);
			txtEmpID.visibleProperty().set(true);
			cbProgram.visibleProperty().set(false);
			cbCareerName.visibleProperty().set(false);
			txtMobile.visibleProperty().set(false);
			lblMobiileNo.visibleProperty().set(false);
			lblCareerName.visibleProperty().set(false);
			lblProgram.visibleProperty().set(false);

		} else {
			lblEmp_ID.visibleProperty().set(true);
			txtEmpID.visibleProperty().set(true);
			cbProgram.visibleProperty().set(false);
			cbCareerName.visibleProperty().set(false);
			txtMobile.visibleProperty().set(true);
			lblMobiileNo.visibleProperty().set(true);
			lblCareerName.visibleProperty().set(false);
			lblProgram.visibleProperty().set(false);
		}

	}

	@FXML
	private void clickOnSaveButton() {

		String emailID = txtEmailID.getText();
		String password = txtPassword.getText();
		int userType = Integer.parseInt(selectType);
		first_Name = txtFirstName.getText();
		last_Name = txtLastName.getText();
		address = TxtAreaAddress.getText();
		emp_ID = txtEmpID.getText();
		mobile_No = txtMobile.getText();	
		
		
		
		if (validateEmail.validateEmail(emailID)) {
			if(userType==1){
				addUserDetail(emailID,password,userType,first_Name,last_Name,address,programName,mobile_No,career_Name,null);
				
			}else if(userType == 2){
				addUserDetail(emailID,password,userType,first_Name,last_Name,address,null,mobile_No,null,emp_ID);
				
			}else if (userType==3) {
				
				addUserDetail(emailID,password,userType,first_Name,last_Name,address,null,null,null,emp_ID);
				
				
			}else if(userType==4){
				addUserDetail(emailID,password,userType,first_Name,last_Name,address,null,mobile_No,null,emp_ID);
				
			}
			
		//	addUserDetail(emailID, password, userType);

		} else {
			lblError.setText(null);
			lblError.setText("Please! verify email");
		}

	}

	public void addUserDetail(String emailID, String password, int userType, String first_Name, String last_Name, String address, String program_Name, String MobileNo,String career_Name,String empID) {
		if (dataAccess.insertAddUser(emailID, password, userType, first_Name, last_Name, address,program_Name, MobileNo, career_Name,  empID)) {
			lblError.setText("");
			lblError.setText("Congratulation ! The user has been registered successfully.");
		} else {
			lblError.setText("");
			lblError.setText("Sorry ! The user is already registered");
		}
	}

	@FXML
	private void clickOnResetButton() {
		reset();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<String> obUserTypeList = FXCollections.observableArrayList("1,Student", "2,Admin",
				"3,Instructor", "4,Program Director");
		cbUserType.setItems(obUserTypeList);
		ObservableList<String> obcareerNameList = FXCollections.observableArrayList("G", "UG");
		cbCareerName.setItems(obcareerNameList);
		
		ArrayList<String> programNamelist = dataAccess.departmentNames();
		ObservableList<String> obprogramList = FXCollections.observableArrayList(programNamelist);
		cbProgram.setItems(obprogramList);
		
		txtEmpID.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*") || newValue.equals(null)) {
					txtEmpID.setText(newValue.replaceAll("[^\\d]", ""));
				}

			}

		});
		
		

	}

	public void reset() {
		txtEmailID.setText("");
		txtPassword.setText("");		
		lblError.setText("");
		TxtAreaAddress.setText("");
		txtEmpID.setText("");
		txtMobile.setText("");
		cbCareerName.getSelectionModel().clearSelection();
		cbProgram.getSelectionModel().clearSelection();
		cbUserType.getSelectionModel().clearSelection();
	}

}
