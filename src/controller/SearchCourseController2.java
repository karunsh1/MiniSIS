/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.MySQLAccess;
import java.sql.*;
import java.io.IOException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import DTO.Course;
import DTO.Student;
import DTO.Users;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import model.DAO;
import model.StudentModel;
import util.Singleton;
import javafx.scene.control.Label;


public class SearchCourseController2 implements Initializable
{   String userType ;
    String studentID;
    DAO dataAccess = new DAO();
	private TextField ErrorMsgField;
	ArrayList courseListArray;
	static ResultSet availcourses;
	public   ObservableList<ObservableList> data;
    int term_id;
    String level,program;
	@FXML
	private ComboBox<String> ComboboxProgram;
	@FXML
	private ComboBox<String> LevelCombobox;
	@FXML
	private ComboBox<String> CourseIdComboBox;
	@FXML
	private ComboBox<String> termComboBox;

	@FXML
	private Button SearchButton;
	@FXML
	private Button AddCourseButton;

	@FXML
	private BorderPane searchCourseBorderPane;   
	static Statement statement;
	
	Integer courseIdDisplay;
	String programDisplay,termDisplay,levelDisplay;
	@FXML TitledPane titledPane;
	@FXML Label LabelCourseTitle;
	@FXML Label LabelCourseDescription;
	@FXML Label LabelNumCredits;
	@FXML Label studentIdLabel;
	@FXML TextField TextFieldCourseTitle;
	@FXML TextField TextFieldCourseDescription;
    @FXML TextField TextFieldNumCredits;
    @FXML TextField studentIdTextField;
	@FXML Label labelInstructorName;
	@FXML TextField textFieldInstructor;
	@FXML Label labelRoomNumber;
	@FXML TextField textFieldRoomNumber;
	@FXML Label labelAddress;
	@FXML TextField textFieldAddress;
	@FXML TextField textFieldSchedule;
	@FXML Label labelSchedule;
	@FXML Button resetButton;
	@FXML Button getCourseIds;

	@Override
	public void initialize(URL url, ResourceBundle rb)
	{   
		studentIdLabel.setVisible(false);
		studentIdTextField.setVisible(false);
	/*studentID = Singleton.getInstance().getUserDataAccessID().getText();*/
	userType = Singleton.getInstance().getUserType().getText();
	System.out.println(userType);
	if(userType.equals("1"))
	{
	   studentID = Singleton.getInstance().getUserAcessID().getText();
	   System.out.println("student_id"+studentID);
	}
	if(userType.equals("2"))
	{
		studentIdLabel.setVisible(true);
		studentIdTextField.setVisible(true);
	}
	
	else {
		System.out.println("no match"+studentID);
	}
		if(studentID=="") {
			if(userType.equals("1"))
					 studentID = Singleton.getInstance().getUserAcessID().getText();
			else if(userType.equals("2")) {
				studentID=studentIdTextField.getText();
				System.out.println("2nd tym" +"student_id"+studentID);
		}
		}
		
		AddCourseButton.setVisible(false);
		titledPane.setExpanded(false);
		
		ArrayList termListArray = dataAccess.termNames();
		ArrayList deptListArray = dataAccess.departmentNames();
		List<String> levelListArray= new ArrayList();
		List<String> courseIdListArray= new ArrayList();
	    //ObservableList<String> availablecoursesObservableList = FXCollections.observableArrayList();  
		levelListArray.add("Graduate");
		levelListArray.add("Undergraduate");
		ObservableList termlist = FXCollections.observableArrayList(termListArray);
		termComboBox.setItems(termlist);
		ObservableList deptlist = FXCollections.observableArrayList(deptListArray);
		
		ObservableList levellist = FXCollections.observableArrayList(levelListArray);
		
		System.out.println("till level done");

		termComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{ 
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1)
			{   //clearAll();
				/*ComboboxProgram.getSelectionModel().clearSelection();
				 LevelCombobox.getSelectionModel().clearSelection();
				 CourseIdComboBox.getSelectionModel().clearSelection();*/
				LevelCombobox.setItems(levellist);
				termDisplay=termComboBox.getSelectionModel().getSelectedItem();//0-Fall, 1-Winter
                term_id=dataAccess.getTermId(termDisplay);
                //termDisplay="Winter 2017";
				LevelCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
				{  
					@Override
					public void changed(ObservableValue<? extends String> ov, String t, String t1)
					{ /*ComboboxProgram.getSelectionModel().clearSelection();
					 CourseIdComboBox.getSelectionModel().clearSelection();*/
						ComboboxProgram.setItems(deptlist);
						level=(LevelCombobox.getValue().equals("Graduate")?"G":"UG");
						System.out.println(level);
					    levelDisplay=level;
					    ComboboxProgram.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
						{ 
							@Override
							public void changed(ObservableValue<? extends String> ov, String t, String t1)
							{ //CourseIdComboBox.getSelectionModel().clearSelection();
								program=ComboboxProgram.getValue();
								System.out.println(program);
							    programDisplay=program;
							
				     
							  
					CourseIdComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
					{
						@Override
						public void changed(ObservableValue<? extends String> ov, String t, String t1)
						{
					         courseIdDisplay=Integer.valueOf((String)CourseIdComboBox.getValue());
					        }
				});}
					/*else {
							
					   }*/	
			//}
		});
			}
		});
			}
		});
			//
		}

		
		char charLevel(String level)
		{
			if ("Graduate".equals(level))
			{
				return 'G';
			}
			else 
			{
				return 'U';
			}

		}


		@FXML public void onAddCourse(ActionEvent event) {
			 DAO dataAccess = new DAO();
			ResultSet rs = dataAccess.CourseInfo(courseIdDisplay,termDisplay,programDisplay,levelDisplay);
            System.out.println("resultset" +rs);

           
            //System.out.println(rs.getString("title"));
            try {
				while(rs.next())
				{
					String course_details_id=rs.getString("course_details_id");
					Integer term_id=Integer.parseInt(rs.getString("term_id"));
					String program=rs.getString("term_id");
					Boolean success=dataAccess.addCourse(studentID,term_id,course_details_id,userType,program,levelDisplay,courseIdDisplay,termDisplay);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Course Registeration");
					if(success) {
					alert.setContentText("Course Added :))");
					}
					else
					{
						alert.setContentText("Course Registeration Failed.Contact your admin");
						}
					alert.showAndWait();
					System.out.println(success);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		@FXML public void onSearchCourse(ActionEvent event) {
			search();
		}
		
		public String search() {
			String course_details_id = null;
			 String start_time =null;
			 String end_time=null;
			ArrayList<String> scheduleDay = new ArrayList<String>();
			if(userType.equals("2"))
			{
		
				studentID=studentIdTextField.getText(); 
				System.out.println("student_id"+studentID);
			}
			
		    //TABLE VIEW AND DATA
		    ObservableList<Course> data;
		
		    DAO dataAccess = new DAO();
		    StringBuilder schedule=new StringBuilder();
		  
		          //data = new ObservableList<ObservableList>();
		          data = FXCollections.observableArrayList();
		          try{
		            //ResultSet
		        	  System.out.println("in search");
		            ResultSet rs = dataAccess.CourseInfo(courseIdDisplay,termDisplay,programDisplay,levelDisplay);
                    System.out.println("resultset" +rs);
		            /**********************************
		             * TABLE COLUMN ADDED DYNAMICALLY *
		             **********************************/
                    titledPane.setExpanded(true);
                	
                    //System.out.println(rs.getString("title"));
                    while(rs.next())
                    {
                    TextFieldCourseTitle.setText(rs.getString("title"));
                    TextFieldCourseDescription.setText(rs.getString("description"));
                    TextFieldNumCredits.setText(rs.getString("units"));
                    textFieldInstructor.setText(rs.getString("instructor_name"));
                    textFieldRoomNumber.setText(rs.getString("room_no"));
                    textFieldAddress.setText(rs.getString("address"));
                    start_time = rs.getString("start_time");
                    end_time = rs.getString("end_time");
                    course_details_id=rs.getString("course_details_id");
                    System.out.println(rs.getString("day"));
                     scheduleDay.add(rs.getString("day"));
                    }
                    System.out.println(scheduleDay.size());
                    for(int i=0;i<scheduleDay.size();i++)
                    {schedule.append(scheduleDay.get(i)).append(" ");}
                    schedule.append(start_time).append(" ");
                    schedule.append(end_time).append(" ");
                    textFieldSchedule.setText(schedule.toString());
                    AddCourseButton.setVisible(true);
        			}
 
		          catch(Exception e){
		              e.printStackTrace();
		              System.out.println("Error on Building Data");             
		          }
				return course_details_id;
			
		}

public void clearAll() {
	ComboboxProgram.getSelectionModel().clearSelection();
	 LevelCombobox.getSelectionModel().clearSelection();
	 CourseIdComboBox.getSelectionModel().clearSelection();
	termComboBox.getSelectionModel().clearSelection();
	clearSearchInfo();

}
	public void clearSearchInfo() {
		TextFieldCourseTitle.setText("");
		TextFieldCourseDescription.setText("");
		TextFieldNumCredits.setText("");
		studentIdTextField.setText("");
		textFieldInstructor.setText("");
		textFieldRoomNumber.setText("");
		textFieldAddress.setText("");
		textFieldSchedule.setText("");
	}
		@FXML public void onReset(ActionEvent event) {
			clearAll();
		}


		@FXML public void onGetCourseIds(ActionEvent event) {
			 courseListArray = dataAccess.courseList(term_id,level,program);
		      //ArrayList temp = new ArrayList(courseListArray);
		      //System.out.println("course list empty"+courseListArray.isEmpty());
		      //if(temp.isEmpty())
		     
		     // }
		      System.out.println(courseListArray);
			ObservableList courselist = FXCollections.observableArrayList(courseListArray);
			System.out.println(courselist);
			CourseIdComboBox.setItems(courselist);
		}
		
	}











