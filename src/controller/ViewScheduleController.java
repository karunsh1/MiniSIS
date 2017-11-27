package controller;

import java.net.URL;
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

public class ViewScheduleController implements Initializable {



	public String studentID = Singleton.getInstance().getUserAcessID().getText();
	public String userType = Singleton.getInstance().getUserType().getText();
	public DAO dataAccess = new DAO();
	public String selectTerm = null;
	public int studentIDSearch = 0;
	@FXML BorderPane searchCourseBorderPane;
	@FXML ComboBox TermCombobox;
	@FXML BorderPane ViewScheduleBorderPane;
	@FXML TableView viewScheduleTableView;
	@FXML TableColumn ColumnCourse;
	@FXML TableColumn ColumnDay;
	@FXML TableColumn ColumnEndTime;
	@FXML TableColumn ColumnStartTime;
	@FXML TableColumn ColumnRoomNumber;
	@FXML TableColumn ColumnBuilding;
	@FXML Button ViewScheduleButton;
	String course;
	String day;
	String start_time;
	String end_time;
	Integer room_num;
	String building;
	protected int term_id;
	@FXML
	private void onSelectTerm() {
		if (userType.equals("1")) {
			selectTerm = TermCombobox.getSelectionModel().getSelectedItem().toString();
			tableViewdDtailPopUp(Integer.parseInt(studentID), selectTerm);
		}else{
			selectTerm = TermCombobox.getSelectionModel().getSelectedItem().toString();
			tableViewdDtailPopUp(studentIDSearch, selectTerm);
		}
	}

	private void tableViewdDtailPopUp(int studentID, String selectTerm2) {


	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		
		ArrayList termListArray = dataAccess.termNames();
		
		ObservableList termlist = FXCollections.observableArrayList(termListArray);
		TermCombobox.setItems(termlist);
		TermCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1)
			{   
				int intermediate_term_id=TermCombobox.getSelectionModel().getSelectedIndex();//0-Fall, 1-Winter
                term_id=intermediate_term_id+1;
		        ArrayList data = new ArrayList();
				ColumnCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
				ColumnDay.setCellValueFactory(new PropertyValueFactory<>("day"));
				ColumnStartTime.setCellValueFactory(new PropertyValueFactory<>("start_time"));
				ColumnEndTime.setCellValueFactory(new PropertyValueFactory<>("end_time"));
				ColumnRoomNumber.setCellValueFactory(new PropertyValueFactory<>("room_num"));
				ColumnBuilding.setCellValueFactory(new PropertyValueFactory<>("building"));
				if((!dataAccess.ViewSchedule(Integer.parseInt(studentID), term_id).isEmpty())) {
				viewScheduleTableView.setItems(dataAccess.ViewSchedule(Integer.parseInt(studentID), term_id));
				System.out.println("data there" +dataAccess.ViewSchedule(Integer.parseInt(studentID), term_id));}
				else
				{       System.out.println("data not there" +dataAccess.ViewSchedule(Integer.parseInt(studentID), term_id));
					    Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText("Schedule");
						alert.setContentText("Not registered in any course for this term. Please register in a course to view schedule");
						alert.showAndWait();
				
				}		
		        			
			}
		});

	}
	public void clearFields(){
		TermCombobox.setItems(null);
		viewScheduleTableView.setItems(null);
		
	}

	@FXML public void onViewSchedule(ActionEvent event) {}

	@FXML public void onRowClick(MouseEvent event) {}
	

}
