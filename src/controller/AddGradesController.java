package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.DAO;
import util.Singleton;

public class AddGradesController implements Initializable {

	@FXML
	private ComboBox cbSelectTerm ;
	@FXML
	private BorderPane addGrades;
	@FXML
	private ComboBox cbDeptName;
	@FXML
	private ComboBox cbCourseName;

	@FXML
	private ComboBox cbSelectTermUpload;
	@FXML
	private ComboBox cbDeptNameUpload;
	@FXML
	private ComboBox cbCourseNameUpload;

	@FXML
	private Button btnDownloadTemplate;
	@FXML
	private Button btnUploadTemplate;
	@FXML
	private Label lblFileName;
	@FXML
	private Tab tbGradeTemplate, tbUploadGrades;
	@FXML
	private Button btnFileChooser;
	private Window stage;
	public String selectTerm = null;
	public String selectDept = null;
	public String selectCourse = null;

	String instructorID = Singleton.getInstance().getUserAcessID().getText();
	DAO dataAccess = new DAO();

	@FXML
	private void onSelecttbUploadGrades(){
		
		cbSelectTerm.getSelectionModel().clearSelection();
		
	}
	@FXML
	private void onSelecttbGradeTemplate(){
		cbSelectTermUpload.getSelectionModel().clearSelection();
		
		
	}
	@FXML
	private void onSelectTerm()

	{
		selectTerm = null;
		selectTerm = cbSelectTerm.getSelectionModel().getSelectedItem().toString();
		System.out.println(selectTerm);
		ArrayList departmentNames = dataAccess.Instructor_Dept(Integer.parseInt(instructorID), selectTerm);
		ObservableList DepartmentNamelist = FXCollections.observableArrayList(departmentNames);
		cbDeptName.setItems(DepartmentNamelist);

	}

	@FXML
	private void onSelectDept() {
		selectDept = null;
		selectDept = cbDeptName.getSelectionModel().getSelectedItem().toString();
		if(!selectDept.equals(null)){
		ArrayList courseNames = dataAccess.instructor_Courses(Integer.parseInt(instructorID), selectDept);
		ObservableList courseNamelist = FXCollections.observableArrayList(courseNames);
		cbCourseName.setItems(courseNamelist);
		}else{
			System.out.println("Select term");
		}

	}

	@FXML
	private void onSelectCourseName() {
		selectCourse = null;
		selectCourse = cbCourseName.getSelectionModel().getSelectedItem().toString();

	}

	@FXML
	private void downnloadCSV() {

		String instructorID = Singleton.getInstance().getUserAcessID().getText();
		String selectCourseCode = selectCourse.substring(0, selectCourse.lastIndexOf("-")).trim();
		System.out.println("select course " + selectCourse);
		String selectCourseTitle = selectCourse
				.substring((selectCourse.lastIndexOf("-") + 1), (selectCourse.length() - 1)).trim();
		System.out.println("select course " + selectCourseTitle);

		FileChooser fileSave = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV File(*.csv)", "*.csv");
		fileSave.getExtensionFilters().add(extFilter);

		File file = fileSave.showSaveDialog(stage);
		if (file != null) {

			dataAccess.exportCSVStudent_Inst_uploadGrades(file, instructorID, selectTerm, selectDept, selectCourseCode,
					selectCourseTitle);

		}

	}

	// For upload CSV
	@FXML
	private void onSelectTermUpload() {
		selectTerm = null;
		selectTerm = cbSelectTermUpload.getSelectionModel().getSelectedItem().toString();
		System.out.println(selectTerm);
		ArrayList departmentNames = dataAccess.Instructor_Dept(Integer.parseInt(instructorID), selectTerm);
		ObservableList DepartmentNamelist = FXCollections.observableArrayList(departmentNames);
		cbDeptNameUpload.setItems(DepartmentNamelist);

	}

	@FXML
	private void onSelectDeptUpload() {
		selectDept = null;
		selectDept = cbDeptNameUpload.getSelectionModel().getSelectedItem().toString();
		ArrayList courseNames = dataAccess.instructor_Courses(Integer.parseInt(instructorID), selectDept);
		ObservableList courseNamelist = FXCollections.observableArrayList(courseNames);
		cbCourseNameUpload.setItems(courseNamelist);
	}

	@FXML
	private void onSelectCourseNameUpload() {
		selectCourse = null;
		selectCourse = cbCourseNameUpload.getSelectionModel().getSelectedItem().toString();
	}

	@FXML
	private void clickOnFileChooser() {
		String selectedPath = null;

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Grade Template");
		File file = fileChooser.showOpenDialog(stage);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV File(*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

		if (file != null) {
			selectedPath = file.getPath();
			lblFileName.setText(selectedPath);
			// lblFileName.textProperty().setValue(selectedPath);
			System.out.println(
					"file path" + selectedPath + "   File Name   " + file.getName() + "label value " + lblFileName);
		}

	}

	@FXML
	private void UploadCSV() {
		String filepath = null;
		filepath = lblFileName.getText();

		System.out.println("upload file" + filepath);
		String selectCourseCode = selectCourse.substring(0, selectCourse.lastIndexOf("-")).trim();
		System.out.println("select course " + selectCourse);
		String selectCourseTitle = selectCourse
				.substring((selectCourse.lastIndexOf("-") + 1), (selectCourse.length() - 1)).trim();
		System.out.println("select course " + selectCourseTitle);

		try {
			dataAccess.importCSVGPA_Instrutor(filepath, Integer.parseInt(instructorID), selectTerm, selectDept,
					selectCourseCode, selectCourseTitle);
			System.out.println("uploaded");
		} catch (FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// download template term
		ArrayList termListArray = dataAccess.termNames(Integer.parseInt(instructorID));
		ObservableList termlist = FXCollections.observableArrayList(termListArray);
		cbSelectTerm.setItems(termlist);

		// Upload template term
		ArrayList termListArrayUpload = dataAccess.termNames(Integer.parseInt(instructorID));
		ObservableList termlistUpload = FXCollections.observableArrayList(termListArrayUpload);
		cbSelectTermUpload.setItems(termlistUpload);

	}

}
