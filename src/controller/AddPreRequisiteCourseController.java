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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DAO;
import model.StudentModel;
import util.Singleton;

public class AddPreRequisiteCourseController implements Initializable {

	@FXML
	private AnchorPane acPaneAddPrerequisite;
	@FXML
	private TextField txtSearchStudentID;
	@FXML
	private Button btnSearchViewCGPA, btnSave, btnReset;
	@FXML
	private ComboBox<String> cbSelectProgram, cbSelectCourseName;
	@FXML
	private Label lblError;
	public String studentID = Singleton.getInstance().getUserAcessID().getText();
	public String userType = Singleton.getInstance().getUserType().getText();
	public DAO dataAccess = new DAO();
	public String selectProgramName = null, selectCourseName = null, selectCourse_code = null;
	public int studentIDSearch = 0;
	public StudentModel studentValidity = new StudentModel();

	@FXML
	private void ClickOnBtnSearchStudentDetail() {
		lblError.setText(null);
		studentIDSearch = Integer.parseInt(txtSearchStudentID.getText());
		if (studentValidity.validateStudent(studentIDSearch)) {
			acPaneAddPrerequisite.visibleProperty().set(true);
			lblError.setText("Please Select all Detail to add the pre-requisite course for the student Id:  "+studentIDSearch+"");

		} else {
			lblError.setText("Sorry!,No record is avaiable!");
			acPaneAddPrerequisite.visibleProperty().set(false);

		}

	}

	@FXML
	private void onSelectProgramName() {
		selectProgramName = cbSelectProgram.getSelectionModel().getSelectedItem();
		ArrayList courseList = dataAccess.selectCourseName(selectProgramName);
		ObservableList oblCourseList = FXCollections.observableArrayList(courseList);
		cbSelectCourseName.setItems(oblCourseList);
		
		

	}

	@FXML
	private void onSelectCourseName() {

		selectCourseName = cbSelectCourseName.getSelectionModel().getSelectedItem();
		selectCourse_code = selectCourseName.substring(0, selectCourseName.lastIndexOf("-")).trim();
		
		System.out.println(selectCourse_code+"sd    "+selectCourseName);

	}

	@FXML
	private void clickOnResetButton() {
		reset();

	}

	@FXML
	private void clickOnSaveButton() {
		if (!(selectCourse_code.equals(null) && selectProgramName.equals(null) && studentIDSearch == 0)) {
			boolean preRequisiteSatus = dataAccess.addPreRequisiteCourseDetails(selectCourse_code, selectProgramName,
					studentIDSearch);
			if (preRequisiteSatus) {
				lblError.setText("Student Detail has been added Successfully! ");
			} else {
				lblError.setText("Student Detail already exists!");
			}
		} else {
			lblError.setText("Please select all detail!");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		ArrayList programList = dataAccess.departmentNames();
		ObservableList oblProgramList = FXCollections.observableArrayList(programList);
		cbSelectProgram.setItems(oblProgramList);

		txtSearchStudentID.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*") || newValue.equals(null)) {
					txtSearchStudentID.setText(newValue.replaceAll("[^\\d]", ""));
				}

			}

		});

	}
	private void reset(){
		cbSelectProgram.getSelectionModel().clearSelection();
		cbSelectCourseName.getSelectionModel().clearSelection();
		txtSearchStudentID.clear();
		lblError.setText("");
		
	}

}
