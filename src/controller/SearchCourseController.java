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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import javafx.scene.control.TitledPane;


public class SearchCourseController implements Initializable
{
	private TextField ErrorMsgField;
	private TableView<Course> searchCourseTableView;
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
	static private RadioButton GraduateRadioButton;
	@FXML
	static private RadioButton UnderGraduateRadioButton;
	@FXML
	private Button SearchButton;
	@FXML
	private Button AddCourseButton;
	//    @FXML
	//    private Button cancelButton;
	@FXML
	private BorderPane searchCourseBorderPane;   
	static Statement statement;
	//    ToggleGroup graduateundergraduateToggleGroup;
	//    @FXML
	//	private void onSearchCourse() {
	//
	//	}
	//    @FXML
	//	private void onAddCourse() {
	//
	//	}
	@FXML TableColumn<Course, String> ColumnProgram;
	@FXML TableColumn<Course, String> ColumnCourseTitle;
	@FXML TableColumn<Course, Integer> ColumnCourseId;	
	@FXML TableColumn<Course, String> ColumnLevel;
	@FXML TableColumn<Course, Integer> ColumnNumCredits;
	@FXML TableColumn<Course, String> ColumnTerm;
	@FXML TableColumn<Course, String> ColumnDescription;
	
	String courseIdDisplay,programDisplay,termDisplay,levelDisplay;


	//
	//	MySQLAccess obj = new MySQLAccess();
	//	Connection conn = obj.getConnection();
	//
	//	private static final long serialVersionUID = 1L;
	//	String successMSG = "";
	//	int type = 1;
	//	
	//	// Default constructor
	public SearchCourseController() {

	}
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
		DAO dataAccess = new DAO();
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
		ComboboxProgram.setItems(deptlist);
		ObservableList levellist = FXCollections.observableArrayList(levelListArray);
		LevelCombobox.setItems(levellist);
		System.out.println("till level done");

		termComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1)
			{   
				int intermediate_term_id=termComboBox.getSelectionModel().getSelectedIndex();//0-Fall, 1-Winter
                term_id=intermediate_term_id+1;
		        if(term_id==0)
		        	termDisplay="Fall 2016";
		        else if(term_id==1)
		        	termDisplay="Winter 2017";
				LevelCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
				{
					@Override
					public void changed(ObservableValue<? extends String> ov, String t, String t1)
					{
						level=LevelCombobox.getValue();
						System.out.println(level);
					    levelDisplay=level;
					    ComboboxProgram.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
						{
							@Override
							public void changed(ObservableValue<? extends String> ov, String t, String t1)
							{
								program=ComboboxProgram.getValue();
								System.out.println(program);
							    programDisplay=program;
							    
				      courseListArray = dataAccess.courseList(term_id,level,program);
				      System.out.println(courseListArray);
					ObservableList courselist = FXCollections.observableArrayList(courseListArray);
					System.out.println(courselist);
					CourseIdComboBox.setItems(courselist);
					courseIdDisplay=CourseIdComboBox.getValue();
					        }
				});
			}
		});
			}
		});

	      ColumnProgram.setCellValueFactory(new PropertyValueFactory<>("Program"));
	      ColumnCourseTitle.setCellValueFactory(new PropertyValueFactory<>("Course Title"));
	      ColumnCourseId.setCellValueFactory(new PropertyValueFactory<>("Course ID"));
	      ColumnLevel.setCellValueFactory(new PropertyValueFactory<>("Level"));
	      ColumnNumCredits.setCellValueFactory(new PropertyValueFactory<>("# of Credits"));
	      ColumnTerm.setCellValueFactory(new PropertyValueFactory<>("Term"));
	      ColumnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
				//
		}

		//    int program(String programname)
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
		//		return 0;
		//    }

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


		@FXML public void onAddCourse(ActionEvent event) {}
		@FXML public void onSearchCourse(ActionEvent event) {
			
		    //TABLE VIEW AND DATA
		    ObservableList<Course> data;
		  //  private TableView tableview;
		    DAO dataAccess = new DAO();

			      
		          //data = new ObservableList<ObservableList>();
		          data = FXCollections.observableArrayList();
		          try{
		            //ResultSet
		            ResultSet rs = dataAccess.CourseInfo(6260, "INSE","Fall 2016","Graduate");
                    System.out.println("resultset" +rs);
		            /**********************************
		             * TABLE COLUMN ADDED DYNAMICALLY *
		             **********************************/

//        			while(rs.next())
//        			{  System.out.println("resultset" +rs);
//        				data.add(new Course(
//        						rs.getString("program"),
//        						
//        						rs.getString("title"),
//        						rs.getInt("course_code"),
//        						rs.getString("level"),
//        						rs.getInt("units"),
//        						rs.getString("term"),
//        						rs.getString("description")
//        						));
//        				System.out.println(rs.getString("program"));
//        				System.out.println(data);
//        				for(int i = 0; i < data.size(); i++) {   
//        				    System.out.print(data.get(i));
//        				}  
                    while(rs.next()){
                        Course course = new Course();
                        course.program.set(rs.getString("program"));  
                        course.courseTitle.set(rs.getString("title"));
                        course.courseId.set(rs.getInt("course_code"));
                        course.level.set(rs.getString("level"));
                        course.numCredits.set(rs.getInt("units"));
                        course.term.set(rs.getString("term"));
                        course.description.set(rs.getString("description"));
                        data.add(course);                  
                    }
                    //tableview.setItems(data);
        				searchCourseTableView.setItems(data);
        			}
        			//preparedStatement.close();
        			//rs.close();
//		            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
//		                //We are using non property style for making dynamic table
//		                final int j = i;                
//		                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
//		                System.out.println("col"+ col);
//		                col.setCellFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
//		                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {   
//		                    	System.out.println("stringproperty" +new SimpleStringProperty(param.getValue().get(j).toString()));
//		                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
//		                    }                    
//		                });
//		                
//		                searchCourseTableView.getColumns().addAll(col); 
//		                System.out.println("Column ["+i+"] ");
//		            }
//
//		            /********************************
//		             * Data added to ObservableList *
//		             ********************************/
//		            while(rs.next()){
//		                //Iterate Row
//		                ObservableList<String> row = FXCollections.observableArrayList();
//		                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
//		                    //Iterate Column
//		                    row.add(rs.getString(i));
//		                    System.out.println(row);
//		                }
//		                System.out.println("Row [1] added "+row );
//		                data.add((Course) row);
//
//		            }
//
//		            //FINALLY ADDED TO TableView
//		            searchCourseTableView.setItems(data);
		          catch(Exception e){
		              e.printStackTrace();
		              System.out.println("Error on Building Data");             
		          }
		}
		    
		
	}











