package controller;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.DAO;

public class AddScheduleController implements Initializable {

	@FXML
	private BorderPane bpAddClassSchedule;
	@FXML
	private ComboBox<String> cbCourseName, cbProgram, cbDay, cbInstructorName, cbTerm;
	@FXML
	private ComboBox<Time> cbStartTime, cbEndTime;
	@FXML
	private Button btnSave, btnReset;
	@FXML
	private Label lblError;
	public DAO dataAccess = new DAO();
	public String selectProgramName = null, selectCourseName = null, instructorName = null, slectedTerm = null,
			selectDay = null;
	public Time selectStartTime = null, selectEndTime = null;

	@FXML
	private void selectTerm() {
		slectedTerm = cbTerm.getSelectionModel().getSelectedItem();
	}

	@FXML
	private void selectPogram() {
		selectProgramName = cbProgram.getSelectionModel().getSelectedItem();
		ArrayList<String> courseNamelist = dataAccess.selectCourseName_AddSchedule(selectProgramName);
		ObservableList<String> obCourseNameList = FXCollections.observableArrayList(courseNamelist);
		cbCourseName.setItems(obCourseNameList);
	}

	@FXML
	private void SelectCourseName() {
		selectCourseName = cbCourseName.getSelectionModel().getSelectedItem();
		ArrayList<String> instructorNamelist = dataAccess.selectInstructorName_AddSchedule(selectCourseName);
		ObservableList<String> obInstructorNameList = FXCollections.observableArrayList(instructorNamelist);
		cbInstructorName.setItems(obInstructorNameList);

	}

	@FXML
	private void selectInstructorName() {
		instructorName = cbInstructorName.getSelectionModel().getSelectedItem();
		cbEndTime.getSelectionModel().clearSelection();
		cbStartTime.getSelectionModel().clearSelection();
		cbDay.getSelectionModel().clearSelection();
		ArrayList<Time> startTimelist = dataAccess.selectStartTime_AddSchedule();
		ObservableList<Time> obStartTimeList = FXCollections.observableArrayList(startTimelist);
		cbStartTime.setItems(obStartTimeList);

		ArrayList<Time> endTimelist = dataAccess.selectEndTime_AddSchedule();
		ObservableList<Time> obEndTimeList = FXCollections.observableArrayList(endTimelist);
		cbEndTime.setItems(obEndTimeList);

		ArrayList<String> daylist = dataAccess.selectDay_AddSchedule();
		ObservableList<String> obDayList = FXCollections.observableArrayList(daylist);
		cbDay.setItems(obDayList);

	}

	@FXML
	private void selectStartTime() {
		selectStartTime = cbStartTime.getSelectionModel().getSelectedItem();

	}

	@FXML
	private void SelectEndTime() {
		selectEndTime = cbEndTime.getSelectionModel().getSelectedItem();
		
	}

	@FXML
	private void selectDay() {
		selectDay = cbDay.getSelectionModel().getSelectedItem();

	}

	@FXML
	private void clickOnBtnReset() {
		reset();

	}

	@FXML
	private void clickOnBtnSave() {

		if (!(slectedTerm.equals(null) && selectCourseName.equals(null) && selectDay.equals(null)
				&& selectStartTime.equals(null) && selectEndTime.equals(null) && instructorName.equals(null))) {
			boolean addScheduleStatus = dataAccess.addSchedule(selectCourseName, slectedTerm, instructorName, selectDay, selectStartTime, selectEndTime);
			if(addScheduleStatus){
				lblError.setText("Course shcedule has been added successfully!");
			}else {
				lblError.setText("Selected shcedule detail is already exist!");
			}
		}else{
			lblError.setText("");
			lblError.setText("Please select all fields");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ArrayList<String> programNamelist = dataAccess.departmentNames();
		ObservableList<String> obprogramList = FXCollections.observableArrayList(programNamelist);
		cbProgram.setItems(obprogramList);

		ArrayList<String> termNameList = dataAccess.selecctTerm_AddSchedule();
		ObservableList<String> obTermList = FXCollections.observableArrayList(termNameList);
		cbTerm.setItems(obTermList);

	}

	private void reset() {
		cbCourseName.getSelectionModel().clearSelection();
		cbProgram.getSelectionModel().clearSelection();
		cbTerm.getSelectionModel().clearSelection();
		cbEndTime.getSelectionModel().clearSelection();
		cbStartTime.getSelectionModel().clearSelection();
		cbDay.getSelectionModel().clearSelection();
		lblError.setText("");

	}

}
