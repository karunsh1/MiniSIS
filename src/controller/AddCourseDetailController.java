package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.DAO;

public class AddCourseDetailController implements Initializable{

	
	@FXML
	private ComboBox<String> cbProgram,cbCourseName,cbInstructor,cbBuildingName,cbRoom,cbTerm;
	@FXML
	private ComboBox<Integer> cbDuration;
	
	@FXML
	private Button btnSave,btnReset;
	@FXML
	private Label lblError;
	
	public DAO dataAccess =new DAO();
	public String selectProgramName=null,selectCourseName=null,instructorName=null,selectTermName=null,buildingName=null,roomNo=null;
	public int duration =0;
	
	
	
	
	
	
	
	
	@FXML
	private void selectProgram(){
		selectProgramName = cbProgram.getSelectionModel().getSelectedItem();		
		ArrayList<String> courseNamelist = dataAccess.selectCourseName(selectProgramName);
		ObservableList<String> obCourseNameList = FXCollections.observableArrayList(courseNamelist);
		cbCourseName.setItems(obCourseNameList);
		
		
	}
	@FXML
	private void selectCourse(){		
		selectCourseName = cbCourseName.getSelectionModel().getSelectedItem();		
		
	}
	@FXML
	private void selectInstructor(){
		instructorName = cbInstructor.getSelectionModel().getSelectedItem();
		
	}
	@FXML
	private void selectTerm(){
		selectTermName = cbTerm.getSelectionModel().getSelectedItem();
		ObservableList<Integer> obDurationList = FXCollections.observableArrayList();
		obDurationList.add(6);
		obDurationList.add(13);
		cbDuration.setItems(obDurationList);
		
	}
	@FXML
	private void selectBuildName(){	
		buildingName = cbBuildingName.getSelectionModel().getSelectedItem();
		
		ArrayList<String> roomNolist = dataAccess.selectbuildingRoom(buildingName);
		ObservableList<String> obRoomNolist = FXCollections.observableArrayList(roomNolist);
		cbRoom.setItems(obRoomNolist);
		
	}
	@FXML
	private void selectRoomNo(){
		roomNo = cbRoom.getSelectionModel().getSelectedItem();
		
	}
	@FXML
	private void clickOnSaveButton(){
		if(!(roomNo.equals(null) && roomNo.equals(null) && selectTermName.equals(null) && instructorName.equals(null) && selectCourseName.equals(null))){
			
			boolean addCourseDetail = dataAccess.addCourseDetail(selectCourseName, roomNo, instructorName,selectTermName,duration);
			if(addCourseDetail){
				lblError.setText("Course detail has been added successfully!");
			}else {
				lblError.setText("Selected Course detail is already exist!");
			}
		}else{
			lblError.setText("");
			lblError.setText("Please select all fields");
		}
		
	}
	@FXML
	private void clickOnResetButton(){
		reset();
		
	}
	@FXML
	private void selectDuration(){
		duration = cbDuration.getSelectionModel().getSelectedItem();
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ArrayList<String> programNamelist = dataAccess.departmentNames();
		ObservableList<String> obprogramList = FXCollections.observableArrayList(programNamelist);
		cbProgram.setItems(obprogramList);
		
		ArrayList<String> termNameList = dataAccess.termNames();
		ObservableList<String> obTermList=FXCollections.observableArrayList(termNameList                                                    );
		cbTerm.setItems(obTermList);
		
		ArrayList<String> instructorNamelist = dataAccess.selectInstructorName();
		ObservableList<String> obInstructorNameList = FXCollections.observableArrayList(instructorNamelist);
		cbInstructor.setItems(obInstructorNameList);
		
		ArrayList<String> buidlingNamelist = dataAccess.selectbuilding();
		ObservableList<String> obBuidlingNamelist = FXCollections.observableArrayList(buidlingNamelist);
		cbBuildingName.setItems(obBuidlingNamelist);
		
		
		
	}
	private void reset(){
		cbBuildingName.getSelectionModel().clearSelection();
		cbCourseName.getSelectionModel().clearSelection();
		cbDuration.getSelectionModel().clearSelection();
		cbInstructor.getSelectionModel().clearSelection();
		cbProgram.getSelectionModel().clearSelection();
		cbRoom.getSelectionModel().clearSelection();
		cbTerm.getSelectionModel().clearSelection();
		lblError.setText("");
		
	}

}
