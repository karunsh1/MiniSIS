package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;

public class DropCourseController implements Initializable {
	

	
	
	
	

	
	@FXML AnchorPane SearchCourseAnchorPane;
	@FXML TableView<Course> searchCourseTableView;
	@FXML TableColumn<Course, String> ColumnProgram;
	@FXML TableColumn<Course, String> ColumnCourseTitle;
	@FXML TableColumn<Course, Integer> ColumnCourseId;	
	@FXML TableColumn<Course, String> ColumnLevel;
	@FXML TableColumn<Course, Integer> ColumnNumCredits;
	@FXML TableColumn<Course, String> ColumnTerm;
	@FXML TableColumn<Course, String> ColumnDescription;
	
	String studentID = Singleton.getInstance().getUserAcessID().getText();
	DAO dataAccess = new DAO();
	@FXML Button DropCourseButton;
	@FXML TableColumn ColumnInstructorName;
	@FXML BorderPane searchCourseBorderPane;
	@FXML ComboBox TermCombobox;
  int term_id;
  String termDisplay;

  
     CourseModel coursemodel = new CourseModel();
     
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
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
		        if(term_id==1)
		        	termDisplay="Fall 2016";
		        else if(term_id==2)
		        	termDisplay="Winter 2017";
		        Course  course= coursemodel.EnrollCourseList(Integer.parseInt(studentID), term_id);
		        ArrayList data = new ArrayList();
		      	      ColumnProgram.setCellValueFactory(new PropertyValueFactory<Course,String>("Program"));
		      	      ColumnCourseTitle.setCellValueFactory(new PropertyValueFactory<Course,String>("CourseTitle"));
		      	      ColumnCourseId.setCellValueFactory(new PropertyValueFactory<Course,Integer>("CourseID"));
		      	      ColumnLevel.setCellValueFactory(new PropertyValueFactory<Course,String>("Level"));
		      	      ColumnNumCredits.setCellValueFactory(new PropertyValueFactory<Course,Integer>("NumOfCredits"));
		      	      ColumnTerm.setCellValueFactory(new PropertyValueFactory<Course,String>("Term"));
		      	      ColumnDescription.setCellValueFactory(new PropertyValueFactory<Course,String>("Description"));
		      	      ColumnInstructorName.setCellValueFactory(new PropertyValueFactory<Course,String>("InstructorName"));
		        			
		        		
		        		//ObservableList<Student> dataOB = FXCollections.observableArrayList(data);
		        			ObservableList<Course> dataob = FXCollections.observableArrayList();	        
		        	        dataob.add(new Course(course.getProgram(),course.getCourseTitle(),course.getCourseId(),course.getLevel(),course.getNumCredits(),course.getTerm(),course.getDescription(),
		        	        		course.getInstructor()));
		        	        searchCourseTableView.setItems(dataob);
		        			System.out.println(data +"   obdata   "+  dataob);
		        			
			}
		});

			
	}

	@FXML public void onDropCourse(ActionEvent event) {}
	
    
	
	
	



	}