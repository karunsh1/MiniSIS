package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DTO.Course;
import DTO.GradesInfo;
import DTO.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.DAO;
import model.StudentModel;
import util.Singleton;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class WaiveOffCourseController implements Initializable {

	ArrayList courseList=null;

	String program="";
	String courseId=null;
	String studentID="";
	public DAO dataAccess = new DAO();
	@FXML
	private BorderPane mBorderPane;   
	@FXML private  Label LabelWaiveOff;
	@FXML private Label LabelStudentId;
	@FXML private TextField textFieldStudentId;
	@FXML private Button buttonWaiveOff;
	@FXML private ComboBox<String> comboBoxCourseIds;
	@FXML private Label labelCourseId;
	@FXML Button buttonSearch;
	@FXML Label labelProgram;
	@FXML Label labelProgramValue;

	@FXML Button btnReset;
	;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if(studentID=="") {
			studentID=getStudentId();
		}

		labelProgram.setVisible(false);


		comboBoxCourseIds.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1)
			{   
				courseId=comboBoxCourseIds.getSelectionModel().getSelectedItem();

			}
		});

	}
	public String getStudentId() {

		textFieldStudentId.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*") || newValue.equals(null)) {
					textFieldStudentId.setText(newValue.replaceAll("[^\\d]", ""));
				}
				studentID=textFieldStudentId.getText();
			}
		});
		return studentID;
	}


	public void clearFields(){
		comboBoxCourseIds.setItems(null);
		textFieldStudentId.setText("");

	}

	@FXML public void onWaiveOff(ActionEvent event) {

		Boolean success=dataAccess.waiveOffCourse(studentID,courseId,program) ;
		if(success) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Waive Off Course");
			alert.setContentText("Waive Off Course");
			alert.setContentText("Course Waived Off Successfully");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Waive Off Course");
			alert.setContentText("Waive Off Course");
			alert.setContentText("There is a problem in waiving off the course");
			alert.showAndWait();
		}

	}
	
	public void reset() {
		textFieldStudentId.setText(" ");
		comboBoxCourseIds.getSelectionModel().clearSelection();
	}
	@FXML public void onSearch(ActionEvent event) {
		labelProgram.setVisible(true);
		if(dataAccess.WaiveOffExists(studentID)) {
			program=dataAccess.courseForWaiveOffProgram(studentID);
			ArrayList courseList=dataAccess.courseForWaiveOff(studentID);
			if(courseList!=null) {
				labelProgramValue.setText(program);
				ObservableList courseObservableList = FXCollections.observableArrayList(courseList);
				comboBoxCourseIds.setItems(courseObservableList);}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Waive Off Course");
			alert.setContentText("Waive Off Course");
			alert.setContentText("Student has no pre-requisite");
			alert.showAndWait();
			
			

		}
	}
	@FXML public void onReset(ActionEvent event) {
		reset();
	}
}



