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
import java.util.ResourceBundle;

import DTO.Student;
import DTO.Users;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.StudentModel;


public class SearchCourseController implements Initializable
{
    private TextField ErrorMsgField;
    private TableView searchCourseTableView;
    static ResultSet availcourses;
    @FXML
    static private ComboBox<String> ComboboxAvailableCourses;
    @FXML
    private ComboBox<String> ComboboxProgram;
    ObservableList<String> termObservableList = FXCollections.observableArrayList("Fall 2017", "Winter 2018");
    ObservableList<String> programObservableList = FXCollections.observableArrayList("Computer Science", "Software Engineering","Information and Security"); 
    ObservableList<String> availablecoursesObservableList = FXCollections.observableArrayList();  
    @FXML
    private ComboBox<String> termComboBox;
    @FXML
    static private RadioButton GraduateRadioButton;
    @FXML
    static private RadioButton UnderGraduateRadioButton;
   @FXML
    private Button ButtonSearchCourse;
    @FXML
    private Button cancelButton;
    static Statement statement;
    ToggleGroup graduateundergraduateToggleGroup;
    @FXML
	private void searchCourseButtonAction() {

	}
    
    @FXML
	private void CancelButtonAction() {

	}
//
//	MySQLAccess obj = new MySQLAccess();
//	Connection conn = obj.getConnection();
//
//	private static final long serialVersionUID = 1L;
//	String successMSG = "";
//	int type = 1;
//	
//	// Default constructor
//	public SearchCourseController() {
//
//	}
//      Users user=new Users();
//        Student student=new Student();
//		String term = termComboBox.getSelectionModel().getSelectedItem();
//		String program =ComboboxProgram.getSelectionModel().getSelectedItem();
//		//String subject = request.getParameter("subject");
//		String courseNo = ComboboxAvailableCourses.getSelectionModel().getSelectedItem();
//		int studentId = 0;
//
//        
//		if (user.getType()==1) {
//			studentId = Integer.parseInt(request.getParameter("studentID"));
//			session.setAttribute("studentId", studentId);
//			// check the student validity
//			StudentModel studentModel = new StudentModel();
//			if(studentModel.validateStudent(studentId) == false){
//				ErrorMsg("errorMsg", "Not a valid student id.");
//				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/searchCourse.jsp");
//				requestDispatcher.forward(request, response);
//			}
//		} else {
//			studentId = (Integer) session.getAttribute("userId");
//			session.setAttribute("studentId", studentId);
//		} 
//
//		try {
//			// Establish Connection
//			String sqlQuery = null;
//
//			sqlQuery = "SELECT cd.id, ti.term as term,CONCAT(ins.first_name,' ',ins.last_name) as instructor,GROUP_CONCAT(CONCAT(sch.day,' ',sch.start_time,' - ',sch.end_time) SEPARATOR ' & ') as time, CONCAT(bld.name,' ',rm.room_no,' ',bld.campus_short) as location, CONCAT(sbj.subject_code,' ',cs.course_code,' - ',cs.title) as course";
//			sqlQuery += ", if((cd.class_capacity -(select count(*) from registration rgs where cd.id=rgs.course_details_id))>0,'Open','Closed') as status, CONCAT(DATE_FORMAT(ti.start_date,'%d/%m/%Y'),' - ', DATE_FORMAT(DATE_ADD(ti.start_date, INTERVAL cd.duration week),'%d/%m/%Y')) as meeting";
//			sqlQuery += " FROM course_details cd, course cs, subject sbj, term_info ti, instructor ins, schedule sch, course_schedule csch, room rm, building bld";
//			sqlQuery += " WHERE ti.id=? and";
//			sqlQuery += " cs.level=? AND";
//			sqlQuery += " sbj.subject_code=? AND";
//			if (courseNo != "") {
//				sqlQuery += " cs.course_code=? AND";
//			}
//			sqlQuery += " cd.term_id=ti.id AND";
//			sqlQuery += " cd.instructor_id=ins.id and";
//			sqlQuery += " cd.room_id=rm.id AND";
//			sqlQuery += " rm.building_id =bld.id AND";
//			sqlQuery += " cd.course_id=cs.id AND";
//			sqlQuery += " cs.subject_id=sbj.id and";
//		    sqlQuery += " csch.schedule_id=sch.id AND";
//			sqlQuery += " csch.course_detail_id=cd.id";
//			sqlQuery += " GROUP BY csch.course_detail_id";
//
//			System.out.print(sqlQuery + "\n");
//			PreparedStatement course = conn.prepareStatement(sqlQuery);
//			// PreparedStatement course = conn.prepareStatement(" select id,
//			// name, status_client from test.projects where client=? ");
//			course.setString(1, term);
//			course.setString(2, program);
//			course.setString(3, subject);
//
//			if (courseNo != "") {
//				course.setString(4, courseNo);
//			}
//
//			ResultSet result = course.executeQuery();
//			ArrayList Rows = new ArrayList();
//			while (result.next()) {
//				// System.out.println(dbID + dbName + dbStatusClient);
//				ArrayList row = new ArrayList();
//				for (int i = 1; i <= 1; i++) {
//					row.add(result.getString("id"));
//					row.add(result.getString("term"));
//					row.add(result.getString("instructor"));
//					row.add(result.getString("time"));
//					row.add(result.getString("location"));
//					row.add(result.getString("course"));
//					row.add(result.getString("status"));
//					row.add(result.getString("meeting"));
//					PreparedStatement registered = conn.prepareStatement(
//							"select if(count(*)>0,1,0) as registered from registration where course_details_id="
//									+ result.getString("id") + " and student_id=" + studentId);
//					ResultSet regiResult = registered.executeQuery();
//					while (regiResult.next()) {
//
//						if (Integer.parseInt(regiResult.getString("registered")) < 1) {
//
//							PreparedStatement registeredCart = conn.prepareStatement(
//									"select if(count(*)>0,1,0) as registered from registration_cart where course_details_id="
//											+ result.getString("id") + " and student_id=" + studentId);
//							ResultSet cartResult = registeredCart.executeQuery();
//							while (cartResult.next()) {
//								row.add(cartResult.getString("registered"));
//							}
//						} else {
//							row.add(regiResult.getString("registered"));
//						}
//						System.out.print(regiResult.getString("registered"));
//					}
//				}
//				Rows.add(row);
//			}
//			if(Rows.size()<1)
//			{   
//				ErrorMsgField.setText("No Data Found. Please Try With Different Value");
//			}
//			else{
//			session.setAttribute("courseList", Rows);
//			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/courseList.jsp");
//			requestDispatcher.forward(request, response);
//			}
//		} catch (Exception e) {
//			System.out.println("Something went wrong. Please contact system admin.");
//			System.err.println(e.getMessage());
//		}
//	
   @Override
   public void initialize(URL url, ResourceBundle rb)
   {
//       
//			MySQLAccess obj = new MySQLAccess();
//			Connection conn = null;
//			conn=obj.getConnection();
//
//        GraduateRadioButton.setDisable(true);
//        UnderGraduateRadioButton.setDisable(true);
//        termComboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1)
//            {
//                if (GraduateRadioButton.isSelected())
//                {
//                    try
//                    {
//                        availcourses = statement.executeQuery("select course_code from course where term=" + termComboBox.getSelectionModel().getSelectedItem() + "' and level='G'");
//                        availablecoursesObservableList.clear();
//                        while (availcourses.next())
//                        {
//                        	availablecoursesObservableList.add(availcourses.getString("course_code"));
//                        }
//                    } catch (SQLException ex)
//                    {
//                    }
//                    ComboboxAvailableCourses.setDisable(false);
//                }
//                if (UnderGraduateRadioButton.isSelected())
//                {
//                	 try
//                     {
//                         availcourses = statement.executeQuery("select course_code from course where term=" + termComboBox.getSelectionModel().getSelectedItem() + "' and level='U'");
//                         availablecoursesObservableList.clear();
//                         while (availcourses.next())
//                         {
//                         	availablecoursesObservableList.add(availcourses.getString("course_code"));
//                         }
//                     } catch (SQLException ex)
//                     {
//                     }
//                     ComboboxAvailableCourses.setDisable(false);
//                }
//                GraduateRadioButton.setDisable(false);
//                UnderGraduateRadioButton.setDisable(false);
//
//            }
//        });
//
//        GraduateRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1)
//            {
//            	 try
//                 {
//                     availcourses = statement.executeQuery("select course_code from course where term=" + termComboBox.getSelectionModel().getSelectedItem() + "' and level='G'");
//                     availablecoursesObservableList.clear();
//                     while (availcourses.next())
//                     {
//                     	availablecoursesObservableList.add(availcourses.getString("course_code"));
//                     }
//                 } catch (SQLException ex)
//                 {
//                 }
//                 ComboboxAvailableCourses.setDisable(false);
//            }
//        });
//
//        UnderGraduateRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1)
//            {
//            	 try
//                 {
//                     availcourses = statement.executeQuery("select course_code from course where term=" + termComboBox.getSelectionModel().getSelectedItem() + "' and level='U'");
//                     availablecoursesObservableList.clear();
//                     while (availcourses.next())
//                     {
//                     	availablecoursesObservableList.add(availcourses.getString("course_code"));
//                     }
//                 } catch (SQLException ex)
//                 {
//                 }
//                 ComboboxAvailableCourses.setDisable(false);
//            }
//            
//        });
//
//        graduateundergraduateToggleGroup = new ToggleGroup();
//        GraduateRadioButton.setToggleGroup(graduateundergraduateToggleGroup);
//        UnderGraduateRadioButton.setToggleGroup(graduateundergraduateToggleGroup);
//
//    
//        ComboboxProgram.setItems(programObservableList);
//        termComboBox.setItems(termObservableList);
//
//
}
//      
//    int block(String programname)
//    {
//        if ("Computer Science".equals(programname))
//        {
//            return 1;
//        }
//        else if ("Software Engineering".equals(programname))
//        {
//            return 2;
//        }
//        else if ("Information and Security".equals(programname))
//        {
//            return 3;
//        }
//    }
}






