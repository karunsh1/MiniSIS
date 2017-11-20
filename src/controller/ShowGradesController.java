package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DTO.GradesInfo;
import DTO.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.DAO;
import model.StudentModel;
import util.Singleton;

public class ShowGradesController implements Initializable {

	@FXML
	private TextField txtSearchStudentID;
	@FXML
	private HBox hBoxSearchStudent;
	@FXML
	private AnchorPane acPaneGradeView;
	@FXML
	private Label lblError;
	@FXML
	private ComboBox cbSelectTerm;
	@FXML
	private TableView<GradesInfo> tbltermCourseDetail;
	@FXML
	private TableColumn<GradesInfo, String> tblColCourse;
	@FXML
	private TableColumn<GradesInfo, String> tblColDescription;
	@FXML
	private TableColumn<GradesInfo, String> tblColGrades;
	@FXML
	private TableColumn<GradesInfo, Float> tblColAttempted;
	@FXML
	private TableColumn<GradesInfo, Float> tblColGPA;
	@FXML
	private Button btnSearchViewCGPA;

	public String studentID = Singleton.getInstance().getUserAcessID().getText();
	public String userType = Singleton.getInstance().getUserType().getText();
	public DAO dataAccess = new DAO();
	public String selectTerm = null;
	public int studentIDSearch = 0;

	@FXML
	private void onSelectTerm() {
		if (userType.equals("1")) {
			selectTerm = cbSelectTerm.getSelectionModel().getSelectedItem().toString();
			tableViewdDtailPopUp(Integer.parseInt(studentID), selectTerm);
		}else{
			selectTerm = cbSelectTerm.getSelectionModel().getSelectedItem().toString();
			tableViewdDtailPopUp(studentIDSearch, selectTerm);
		}
	}

	private void tableViewdDtailPopUp(int studentID, String selectTerm2) {

		tblColCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tblColDescription.setCellValueFactory(new PropertyValueFactory<>("course_Title"));
		tblColGrades.setCellValueFactory(new PropertyValueFactory<>("grade"));
		tblColAttempted.setCellValueFactory(new PropertyValueFactory<>("attempted"));
		tblColGPA.setCellValueFactory(new PropertyValueFactory<>("gpa"));
		tbltermCourseDetail.setItems(dataAccess.getGradeViewDetail(studentID, selectTerm));

	}

	@FXML
	private void ClickOnBtnSearchStudentDetail() {
		
		lblError.setText(null);
		studentIDSearch = Integer.parseInt(txtSearchStudentID.getText());
		if (dataAccess.validateStudentForCGPA(studentIDSearch)) {
			acPaneGradeView.visibleProperty().set(true);
			ArrayList termList = dataAccess.getTermGrade(studentIDSearch);
			ObservableList oblTermList = FXCollections.observableArrayList(termList);
			cbSelectTerm.setItems(oblTermList);
			

		} else {
			lblError.setText("Sorry!,No record is avaiable!");
			acPaneGradeView.visibleProperty().set(false);
			clearFields();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		if (userType.equals("1")) {
			hBoxSearchStudent.visibleProperty().set(false);
			if (dataAccess.validateStudentForCGPA(Integer.parseInt(studentID))) {
				acPaneGradeView.visibleProperty().set(true);
				ArrayList termList = dataAccess.getTermGrade(Integer.parseInt(studentID));
				ObservableList oblTermList = FXCollections.observableArrayList(termList);
				cbSelectTerm.setItems(oblTermList);
			} else {
				lblError.setText("Sorry!,No record is avaiable!");
				acPaneGradeView.visibleProperty().set(false);
				clearFields();
			}

		} else {
			txtSearchStudentID.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*") || newValue.equals(null)) {
						txtSearchStudentID.setText(newValue.replaceAll("[^\\d]", ""));
					}

				}

			});
		}

	}
	public void clearFields(){
		cbSelectTerm.setItems(null);
		tbltermCourseDetail.setItems(null);
		
	}
	

}
