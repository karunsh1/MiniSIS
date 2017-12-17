package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DTO.Course;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import model.CourseModel;
import model.DAO;
import util.Singleton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

public class DropCourseController implements Initializable {
	String userType;
	
	@FXML AnchorPane SearchCourseAnchorPane;
	@FXML TableView<Course> searchCourseTableView;
	@FXML TableColumn<Course, String> ColumnProgram;
	@FXML TableColumn<Course, String> ColumnCourseTitle;
	@FXML TableColumn<Course, String> ColumnCourseId;	
	@FXML TableColumn<Course, String> ColumnLevel;
	@FXML TableColumn<Course, Integer> ColumnNumCredits;
	@FXML TableColumn<Course, String> ColumnTerm;
	@FXML TableColumn<Course, String> ColumnDescription;
	
	String studentID = null;
	DAO dataAccess = new DAO();
	@FXML Button DropCourseButton;
	@FXML TableColumn ColumnInstructorName;
	@FXML BorderPane searchCourseBorderPane;
	@FXML ComboBox TermCombobox;
    int term_id;
    String termDisplay=null;
    String term_from_view,  program_from_view,level_from_view;
    Integer course_id_from_view;
     CourseModel coursemodel = new CourseModel();
	@FXML Button resetButton;
	@FXML Label labelStudentId;
	@FXML TextField textFieldStudentId;
     
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelStudentId.setVisible(false);
		textFieldStudentId.setVisible(false);
		/*studentID = Singleton.getInstance().getUserDataAccessID().getText();*/
		userType = Singleton.getInstance().getUserType().getText();
		System.out.println(userType);
		if(userType.equals("1"))
		{
			studentID = Singleton.getInstance().getUserAcessID().getText();
			System.out.println("student_id"+studentID);
		}
		if(userType.equals("2")||userType.equals("4"))
		{
			labelStudentId.setVisible(true);
			textFieldStudentId.setVisible(true);
			//studentID=textFieldStudentId.getText();
		}

		else {
			System.out.println("no match"+studentID);
		}
		if(studentID==null) {
			if(userType.equals("1"))
				studentID = Singleton.getInstance().getUserAcessID().getText();
			else if(userType.equals("2")||userType.equals("4")) {
				studentID=textFieldStudentId.getText();
				System.out.println("2nd tym" +"student_id"+studentID+"test"+textFieldStudentId.getText());
			}
		}

        
		ArrayList termListArray = dataAccess.termNamesAllowed();
		if(termListArray.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Course Drop");
			alert.setContentText("Sorryyy!! Disc deadline for possible terms has ended.You cannot drop course now.");
			alert.showAndWait();
			clear();
		}
		ObservableList termlist = FXCollections.observableArrayList(termListArray);
		TermCombobox.setItems(termlist);
		TermCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1)
			{   
				termDisplay=(String) TermCombobox.getSelectionModel().getSelectedItem();//0-Fall, 1-Winter
				 term_id=dataAccess.getTermId(termDisplay);
				 if(userType.equals("1"))
						studentID = Singleton.getInstance().getUserAcessID().getText();
					else if(userType.equals("2")) {
						studentID=textFieldStudentId.getText();
						System.out.println("2nd tym" +"student_id"+studentID+"test"+textFieldStudentId.getText());
					}
		        ArrayList data = new ArrayList();
		      	      ColumnProgram.setCellValueFactory(new PropertyValueFactory<Course,String>("Program"));
		      	      ColumnCourseTitle.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseTitle"));
		      	      ColumnCourseId.setCellValueFactory(new PropertyValueFactory<Course,String>("courseId"));
		      	      ColumnLevel.setCellValueFactory(new PropertyValueFactory<Course,String>("Level"));
		      	      ColumnNumCredits.setCellValueFactory(new PropertyValueFactory<Course,Integer>("numCredits"));
		      	      ColumnTerm.setCellValueFactory(new PropertyValueFactory<Course,String>("Term"));
		      	      ColumnDescription.setCellValueFactory(new PropertyValueFactory<Course,String>("Description"));
		      	      ColumnInstructorName.setCellValueFactory(new PropertyValueFactory<Course,String>("instructor"));
		        	  searchCourseTableView.setItems(coursemodel.EnrollCourseList(Integer.parseInt(studentID), term_id));
		        			
		        			
			}
		});

			
	}


	
	@FXML public void onDropCourse(ActionEvent event) {
		String course_details_id=null;
		 DAO dataAccess = new DAO();
		 System.out.println(course_id_from_view);
		 System.out.println(term_from_view);
		 System.out.println(program_from_view);
		 System.out.println(term_from_view);
		 
			ResultSet rs = dataAccess.CourseInfo(course_id_from_view,term_from_view,program_from_view,level_from_view);
         System.out.println("resultset" +rs);
       try {System.out.println("in try");
				while(rs.next())
				{
				 course_details_id=rs.getString("course_details_id");
					
				}
				Boolean success=dataAccess.dropCourse(studentID,course_details_id);
				clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Course Registeration");
				searchCourseTableView.setItems(coursemodel.EnrollCourseList(Integer.parseInt(studentID), term_id));
				if(success) {
				alert.setContentText("Course deleted successfully :))");
				}
				else
				{
					alert.setContentText("Course dropping Failed.Contact your admin");
					}
				alert.showAndWait();
				System.out.println(success);
			} catch (SQLException e) {
				System.out.print("error in SQL drop");
				e.printStackTrace();
			}
	}



	@FXML public void onRowClick(MouseEvent event) {
		
		 Course selectedItems = searchCourseTableView.getSelectionModel().getSelectedItems().get(0);
         term_from_view = selectedItems.getTerm();
         System.out.println(term_from_view);
		 course_id_from_view =Integer.parseInt(selectedItems.getCourseId()) ;
		 program_from_view= selectedItems.getProgram();
		 level_from_view= selectedItems.getLevel();
		 
		//System.out.println(courseId);
	}



	@FXML public void onReset(ActionEvent event) {
		
		clear();
		
	}
    
	public void clear() {
		TermCombobox.getSelectionModel().clearSelection();
		searchCourseTableView.getItems().clear();
	}
	
	}