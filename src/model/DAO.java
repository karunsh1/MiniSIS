package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.mysql.fabric.xmlrpc.base.Array;

import DTO.Admin;
import DTO.Course;
import DTO.GradesInfo;
import DTO.Instructor;
import DTO.Schedule;
import DTO.Student;
import DTO.Term;
import controller.SearchCourseController2;
import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import util.Auth;

public class DAO {

	private ArrayList<String> dataobSchedule;
	private Schedule schedule;
	private ArrayList<String> courseDetailIdsList = new ArrayList<String>();

	/**
	 * 
	 * @param studentId
	 * @return terminfo
	 */
	public Term termList(int studentId) {
		Term terminfo = null;

		try {

			String sql = "select distinct ti.id,ti.term from registration rgs, course_details cd, term_info ti"
					+ " where rgs.student_id=" + studentId + " and rgs.course_details_id=cd.id"
					+ " and cd.term_id=ti.id";

			System.out.println(sql);

			// Establish Connection
			MySQLAccess obj = new MySQLAccess();
			Connection conn = obj.getConnection();
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();
			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					int id = result.getInt("id");
					String termName = result.getString("term");
					terminfo = new Term(termName, id);
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		return terminfo;
	}

	public ArrayList<String> termNames() {
		String sql = null;
		ArrayList<String> termNameList = new ArrayList<String>();

		sql = "select term from term_info";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();

			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					termNameList.add(result.getString("term"));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return termNameList;

	}

	/**
	 * 
	 * @param instructorID
	 * @return termNameList
	 */
	public ArrayList<String> termNames(int instructorID) {
		String sql = null;
		ArrayList<String> termNameList = new ArrayList<String>();

		sql = "SELECT  term FROM term_info where id in(select term_id from course_details where instructor_id = "
				+ instructorID + ")";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();

			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					termNameList.add(result.getString("term"));
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return termNameList;

	}

	/**
	 * Department Name
	 * 
	 * @return departmentList
	 */

	public ArrayList<String> departmentNames() {
		String sql = null;
		ArrayList<String> departmentList = new ArrayList<String>();

		sql = "select subject_code from subject";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();

			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					departmentList.add(result.getString("subject_code"));
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departmentList;

	}

	/**
	 * 
	 * @param email
	 * @return instructor
	 */

	public Instructor selectInstructor(String email) {
		Instructor instructor = null;
		String sql = "select * from instructor where email=?";

		/// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();

		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			prepareStm.setString(1, email);
			ResultSet results = prepareStm.executeQuery();
			while (results.next()) {
				email = results.getString("email");

				int id = results.getInt("id");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				// String mobile = results.getString("mobile");
				String address = results.getString("address");
				int emp_id = results.getInt("emp_id");

				instructor = new Instructor(id, first_name, last_name, email, emp_id, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return instructor;
	}

	/**
	 * 
	 * @param instrutorID
	 * @param deptName
	 * @return courseList
	 */
	public ArrayList<String> instructor_Courses(int instrutorID, String deptName) {
		String sql = null;
		ArrayList<String> courseList = new ArrayList<String>();

		sql = "select course_code,title from course where id in(select course_id from course_details where instructor_id="
				+ instrutorID + " ) AND subject_id IN (SELECT id FROM subject WHERE subject_code = '" + deptName + "')";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();
			int CourseID = 0;
			String CourseTitle = null;
			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					CourseID = result.getInt("course_code");
					CourseTitle = result.getString("title");
					courseList.add(CourseID + "-" + CourseTitle);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return courseList;

	}

	public ResultSet CourseInfo(int course_code, String term, String program, String level) {
		String sql = null;

		sql = "SELECT *,concat(first_name, \" \", last_name) as instructor_name, course.program, course.title, course_details.id as course_details_id ,course.course_code,course.description,course.level, course.units,course_details.id as course_details_id,term_info.term from course join course_details on course.id \r\n" + 
				"		=course_details.course_id inner join \r\n" + 
				"				term_info on  course_details.term_id= term_info.id join instructor on instructor.id"
				+ "=course_details.instructor_id join room on room_id=room.id join course_schedule on course_details.id=course_schedule.course_detail_id join schedule on schedule.id=course_schedule.schedule_id "
				+ "where course_code= " +"\""+course_code+ "\""  
				+ "and program="+"\""+program+ "\"" +"and level=" +"\""+level+ "\"" +"and term=" +"\""+term+ "\"" ;



		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ResultSet result = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public ArrayList getCourseDetailIds(int studentID, int termID) {
		System.out.println("in getCourseIdlist");
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		int studentId = studentID;
		int termId = termID;
		String course_details_id;

		System.out.println(studentId);
		System.out.println(termId);

		ArrayList enrollCourseList = null;
		String sqlQuery = "";
		String enrolled = "enrolled";
		try {

			sqlQuery = "SELECT * ,CONCAT(instructor.first_name,' ',instructor.last_name) as instructor, course.id as course_id from  registration join course_details on  course_details.id=registration.course_details_id join"
					+ " student on student.id= registration.student_id join course on course_details.course_id= course.id join instructor on "
					+ "instructor.id=course_details.instructor_id  join term_info on term_info.id= course_details.term_id where status="
					+ "\"" + enrolled + "\"" + " and student_id=" + "\"" + studentID + "\"" + "and term_id=" + "\""
					+ termID + "\"";

			System.out.println(sqlQuery);
			PreparedStatement courseList = conn.prepareStatement(sqlQuery);
			ResultSet result = courseList.executeQuery();

			while (result.next()) {

				course_details_id = result.getString("course_details_id");
				System.out.println(result.getString("course_details_id"));
				courseDetailIdsList.add(course_details_id);
				for (int i = 0; i < courseDetailIdsList.size(); i++) {
					System.out.println("courseIdListValyes");
					System.out.println(courseDetailIdsList.get(i));
					System.out.println(courseDetailIdsList.size());
				}


			}

		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		// System.out.println("size in DAO course--------"
		// +courseDetailIdsList.size());
		return courseDetailIdsList;
	}
	
	
	
	boolean alreadyExists;
	boolean alreadyThree;
	boolean sameProgram;
	boolean availibility;
	boolean feePaid;
	boolean scheduleConflict;
	public boolean addCourse(String studentId,int term_id,String course_details_id,String userType, String program, String level, Integer courseId) {


		System.out.println("in getCourseIdlist");
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		int studentID = Integer.parseInt(studentId);
		int termId = term_id;
		SearchCourseController2 getCourseDetailId=new SearchCourseController2();
		String course_id = null;
		ArrayList enrollCourseList = null;
		String sqlQuery = "";
		String enrolled="enrolled";
		alreadyThree=false;
		sameProgram=false;
		availibility=false;
		feePaid=false;
		scheduleConflict=false;

		try {

			sqlQuery = "SELECT * ,CONCAT(instructor.first_name,' ',instructor.last_name) as instructor, course.id as course_id from  registration join course_details on  course_details.id=registration.course_details_id join"
					+ " student on student.id= registration.student_id join course on course_details.course_id= course.id join instructor on "
					+ "instructor.id=course_details.instructor_id  join term_info on term_info.id= course_details.term_id join subject on subject.id=student.subject_id where status="
					+ "\"" + enrolled + "\"" + " and student_id=" + "\"" + studentID + "\"" + "and term_id=" + "\""
					+ termId + "\"";

			System.out.println(sqlQuery);
			PreparedStatement courseList = conn.prepareStatement(sqlQuery);
			ResultSet result = courseList.executeQuery();
			int count_courses = 0;
			String subject_code = null;
			while (result.next()) {
				count_courses++;
				// course_id = result.getString("course_id");
				System.out.println("course_details_id" + course_details_id);
				program = result.getString("program");
				courseDetailIdsList.add(result.getString("course_details_id"));
			}
			course_id = getCourseId(course_details_id).toString();
			subject_code = getSubjectCode(studentId);
			System.out.println("program" + program);
			System.out.println("subjct code" + subject_code);
			System.out.println("user type" + userType);
			if (program.equals(subject_code) || userType != "1")
				sameProgram = true;
			System.out.println("sameProgram" + sameProgram);
			System.out.println("courses" + count_courses);
			for (int i = 0; i < courseDetailIdsList.size(); i++) {
				System.out.println("courseIdListValyes");
				System.out.println(courseDetailIdsList.get(i));
				// System.out.println(courseDetailIdsList.size());
			}
			System.out.println("in addcourse-----------------");
	for(int i=0;i<courseDetailIdsList.size();i++)
			{  System.out.println("course details id for chk" +course_details_id);
			if(courseDetailIdsList.get(i).equals(course_details_id)&& courseDetailIdsList!=null ){
				alreadyExists=true;
				System.out.println(alreadyExists);
				//break;
			}
			}
			ArrayList schedules=getAlreadyEnrolledSchedule(studentID, termId);
			ArrayList scheduleCourse=getSearchSchedule(level, term_id,program,courseId);
			System.out.println("--------schedule comparison---------");
			for(int i=0;i<schedules.size();i++) {
				System.out.println("schedules "+ schedules.get(i));
			}
			for(int j=0;j<scheduleCourse.size();j++) {
				System.out.println("schedule course"+scheduleCourse.get(j));
			}
			for(int i=0;i<schedules.size();i++)
			{
				for(int j=0;j<scheduleCourse.size();j++) {
					
					
					if(schedules.get(i).equals(scheduleCourse.get(j))) {
						
						scheduleConflict=true;
						break;
					}
				}
			}
			System.out.println("schedule conflict"+scheduleConflict);
			int class_availability=getClassAvailability(course_details_id);
			if(getClassAvailability(course_details_id)>0)
				availibility=true;
			if(count_courses==3)
				alreadyThree=true;
			System.out.println("fee from get function "+getDuePayment(term_id,studentId));
			if(getDuePayment(term_id,studentId)==0.0)
				feePaid=true;
			String sql = null;
			String sql1 = null;
			String sql2 = null;
			if (alreadyExists == true) {
				System.out.println("in already exists true");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Course Registeration");
				alert.setContentText("You have already registered in this course:))");
				alert.showAndWait();
			}

			if(scheduleConflict==true) {
				System.out.println("in schedule conflict true");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Course Registeration");
				alert.setContentText("Cannot register ::You have a conflict with another course");	
				alert.showAndWait();
			}
			System.out.println("same program" +sameProgram);
			if(alreadyThree==true) {


				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Course Registeration");
				alert.setContentText("You have already registered in three courses");
				alert.showAndWait();
			}
			if (sameProgram == false) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Course Registeration");
				alert.setContentText("You cannot register in courses outside your domain");
				alert.showAndWait();
			}
			if (availibility == false) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Course Registeration");
				alert.setContentText("The class is already full");
				alert.showAndWait();
			}
			System.out.println("fee paid" + feePaid);
			if (feePaid == false) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Course Registeration");
				alert.setContentText("Not eligible for registered.Please pay to continue");
				alert.showAndWait();
			}

			if (alreadyExists == false && alreadyThree == false && sameProgram == true && availibility == true
					&& feePaid == true) {
				sql = "INSERT INTO registration (student_id, course_details_id, status) \r\n" + "VALUES (" + "\""
						+ studentId + "\"" + "," + "\"" + course_details_id + "\"" + "," + "\"" + enrolled + "\""
						+ ") ON DUPLICATE KEY UPDATE status =" + "\"" + enrolled + "\"";

				sql1 = "INSERT INTO grade (course_id, student_id, term_id)" + "VALUES (" + "\"" + course_id + "\"" + ","
						+ "\"" + studentId + "\"" + "," + "\"" + termId + "\"" + ")";

				sql1="INSERT INTO grade (course_id, student_id, term_id)"
						+"VALUES ("+"\""+  course_id  +"\""+","+ "\""+ studentId+"\""+","+ "\""+ termId+"\""+")";

				sql2="update course_details set class_availability="+ "\""+(class_availability-1)  + "\""+  "where id=" + "\""+course_details_id  + "\"";

				PreparedStatement ps = conn.prepareStatement(sql);
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps.executeUpdate();
				ps1.executeUpdate();
				ps2.executeUpdate();
				System.out.println(sql);
				System.out.println(sql1);
				System.out.println(sql2);

				return true; }


		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

		return false;

	}


	private String getSubjectCode(String studentId) {
		String subject_code = null;
		String sql = null;
		ArrayList courseList = new ArrayList();
		sql = "select subject_code from subject join student on subject.id=student.subject_id where student.id=" + "\""
				+ studentId + "\"";
		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ResultSet result = null;
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			result = courselist.executeQuery();
			while(result.next())
			{

				subject_code = result.getString("subject_code");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("payment due"+subject_code);
		return subject_code;

	}

	public boolean waiveOffCourse(String studentId,String course_code,String program) {
		boolean doneWaiveOff=false;
		String sql = null;
		sql="delete from pre_requisite where student_id="+ "\""+ studentId+"\""+"and course_code=" + "\""+ course_code+"\""+"and program=" +"\""+ program+"\"";
		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ResultSet result = null;
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			result = courselist.executeQuery();
			doneWaiveOff=true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doneWaiveOff;

	}

	public boolean dropCourse(String studentId, String course_details_id) {
		String sql = null;
		String sql1=null;
		String sql2=null;
		String dropped="dropped";
		int class_availability=getClassAvailability(course_details_id);
		String course_id=getCourseId(course_details_id).toString();
		sql =sql ="INSERT INTO registration (student_id, course_details_id, status) \r\n" + 
				"VALUES ("+"\""+ studentId+"\""+","+ "\""+ course_details_id+"\""+","+ "\""+ dropped+"\""+") ON DUPLICATE KEY UPDATE status ="+"\""+ dropped+"\"";

		sql1="DELETE FROM grade where course_id="+"\""+  course_id  +"\"";

		sql2="update course_details set class_availability="+ "\""+(class_availability+1)  + "\""+  "where id=" + "\""+course_details_id  + "\"";
		System.out.println(sql);
		System.out.println(sql1);
		System.out.println(sql2);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ResultSet result = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.executeUpdate();
			PreparedStatement ps2 = conn.prepareStatement(sql1);
			ps2.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * 
	 * @param instructorID
	 * @param terms
	 * @return deptList
	 */
	public ArrayList<String> Instructor_Dept(int instructorID, String terms) {
		String sql = null;
		ArrayList<String> deptList = new ArrayList<String>();

		sql = "select subject_code from subject where id in (select subject_id from course where  id  in( select course_id  from course_details where instructor_id="
				+ instructorID + " and term_id in(select id from term_info where term = '" + terms + "')))";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet result = term.executeQuery();

			while (result.next()) {

				if (!result.wasNull()) {

					deptList.add(result.getString("subject_code"));
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return deptList;

	}

	/**
	 * 
	 * @param instrutorID
	 * @return deptList
	 */

	public ArrayList<String> careerList_ins(int instrutorID) {
		String sql = null;
		ArrayList<String> deptList = new ArrayList<String>();

		sql = "select level from course where id in (select course_id from course_details where instructor_id ="
				+ instrutorID + ")";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();

			while (result.next()) {
				for (int i = 1; i <= 1; i++) {

					deptList.add(result.getString("level"));
					;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return deptList;

	}

	public ResultSet getPaymentDetails(String term, String StudentID) {
		String sql = null;
		ArrayList courseList = new ArrayList();

		sql = "SELECT * from payment where term=" + "\"" + term + "\"" + "and student_id=" + "\"" + StudentID + "\"";

		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ResultSet result = null;
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			result = courselist.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public double getDuePayment(int term_id, String StudentID) {
		double amount_due = 0;
		String sql = null;
		System.out.println("in get due payment");
		System.out.println(term_id);
		System.out.println(StudentID);
		sql = "SELECT amount_due from payment where term_id=" + "\"" + term_id + "\"" + "and student_id=" + "\""
				+ StudentID + "\"";

		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ResultSet result = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
			while(result.next())
			{
				amount_due=Double.parseDouble(result.getString("amount_due"));
				System.out.println("amount due in next loop"+amount_due);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("payment due in DAO"+amount_due);
		return amount_due;

	}

	public int getClassAvailability(String course_details_id) {
		String sql = null;
		int class_availability = 0;
		ArrayList courseList = new ArrayList();

		sql="select class_availability from course_details where id="+"\""+course_details_id  + "\""; 

		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ResultSet result = null;
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			result = courselist.executeQuery();

			while(result.next())
			{
				class_availability=Integer.parseInt(result.getString("class_availability"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return class_availability;

	}

	public String getCourseId(String course_details_id) {
		String sql = null;
		String course_id = null;
		ArrayList courseList = new ArrayList();

		sql="select *,course_details.id as course_details_id from course join course_details on course.id=course_details.course_id where course_details.id="+"\""+course_details_id  + "\""; 

		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ResultSet result = null;
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			result = courselist.executeQuery();
			while(result.next())
			{
				course_id=result.getString("course_id");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course_id;

	}

	public boolean setPaymentDetails(String term, String StudentID, String AmountDue) {
		String sql = null;
		ArrayList courseList = new ArrayList();
		sql = "update payment set amount_due=" + "\"" + AmountDue + "\"" + "where term=" + "\"" + term + "\""
				+ "and student_id=" + "\"" + StudentID + "\"";

		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		int result = 0;
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			result = courselist.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public ArrayList courseList(int term_id, String level, String program) {
		String sql = null;
		ArrayList courseList = new ArrayList();

		sql = "SELECT course.course_code\r\n" + "FROM course \r\n" + "JOIN course_details \r\n"
				+ "ON course.id = course_details.course_id \r\n" + "WHERE course_details.term_id =" + term_id + " \r\n"
				+ "AND level = " + "\"" + level + "\"" + "AND program = " + "\"" + program + "\"";

		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			ResultSet result = courselist.executeQuery();

			while (result.next()) {
				for (int i = 1; i <= 1; i++) {

					courseList.add(result.getString("course_code"));
					;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return courseList;

	}

	public boolean WaiveOffExists(String studenId) {
		String sql = null;
		ResultSet result=null;
		boolean waiveOffExists=false;
		ArrayList courseList = new ArrayList();
		sql="SELECT * FROM pre_requisite join course on course.program=pre_requisite.program "
				+" WHERE student_id ="+ "\""+ studenId +"\"";
		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			result = courselist.executeQuery();
			if(result.next()) {
				while (result.next()) {

					courseList.add(result.getString("course_code"));

				}
				waiveOffExists=true;
			}
			else {
				waiveOffExists=false;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return waiveOffExists;

	}

	public ArrayList courseForWaiveOff(String studenId) {
		String sql = null;
		ResultSet result=null;
		ArrayList courseList = new ArrayList();
		sql="SELECT * FROM pre_requisite join course on course.program=pre_requisite.program "
				+" WHERE student_id ="+ "\""+ studenId +"\"";
		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			result = courselist.executeQuery();
			if(result.next()) {
				while (result.next()) {

					courseList.add(result.getString("course_code"));
				}
			}
			else {

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return courseList;

	}
	public String courseForWaiveOffProgram(String studenId) {
		String sql = null;
		ResultSet result=null;
		String program=null;
		sql="SELECT * FROM pre_requisite join course on course.program=pre_requisite.program "
				+" WHERE student_id ="+ "\""+ studenId +"\"";
		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			result = courselist.executeQuery();
			if(result.next()) {
				while (result.next()) {
					for (int i = 1; i <= 1; i++) {

						program=result.getString("program");
						;
					}
				}
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Waive Off Course");
				alert.setContentText("Waive Off Course");
				alert.setContentText("Student has no pre-requisite");
				alert.showAndWait();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return program;

	}

	/**
	 * 
	 * @param term_id
	 * @param level
	 * @return courseList
	 */

	public ArrayList<String> courseList(int term_id, String level) {
		String sql = null;
		ArrayList<String> courseList = new ArrayList<String>();
		sql = "SELECT course.course_code\r\n" + "FROM course \r\n" + "JOIN course_details \r\n"
				+ "ON course.id = course_details.course_id \r\n" + "WHERE course_details.term_id =" + term_id + " \r\n"
				+ "OR level = " + "\"" + level + "\"";
		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement courselist = conn.prepareStatement(sql);
			ResultSet result = courselist.executeQuery();

			while (result.next()) {
				for (int i = 1; i <= 1; i++) {

					courseList.add(result.getString("course_code"));
					;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return courseList;

	}

	/**
	 * 
	 * @param file
	 * @param instructorID
	 * @param term
	 * @param dept
	 * @param courseCode
	 * @param courseTitle
	 */
	
	public boolean exportCSVStudent_Inst_uploadGrades(File file, String instructorID, String term, String dept,
			String courseCode, String courseTitle) {

		String sql = "";
		boolean exportStatus = false;

		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		sql = "SELECT id, first_name, last_name FROM  student  WHERE id IN ("
				+ "SELECT student_id FROM registration WHERE  status = 'enrolled'  AND course_details_id IN ("
				+ "SELECT id FROM course_details WHERE instructor_id =" + instructorID + " AND term_id IN ("
				+ "SELECT id  FROM  term_info   WHERE  term = '" + term + "') AND course_id IN ("
				+ "SELECT id FROM  course  WHERE course_code =" + courseCode + "  AND title = '" + courseTitle
				+ "'  AND subject_id IN (SELECT id  FROM  subject  WHERE  subject_code = '" + dept + "'))))";

		boolean alreadyExists = file.exists();

		try {
			CsvWriter csvStudentFile = new CsvWriter(new FileWriter(file, true), ',');
			if (!alreadyExists) {
				csvStudentFile.write("Student_ID");
				csvStudentFile.write("First_Name");
				csvStudentFile.write("Last_name");
				csvStudentFile.write("GPA");
				csvStudentFile.endRecord();
			}

			PreparedStatement studentInfo = conn.prepareStatement(sql);
			System.out.println("studentInfo  " + studentInfo);
			ResultSet result = studentInfo.executeQuery();
			while (result.next()) {

				csvStudentFile.write(result.getString("id"));
				csvStudentFile.write(result.getString("first_Name"));
				csvStudentFile.write(result.getString("last_name"));
				csvStudentFile.endRecord();
				exportStatus = true;

			}
			csvStudentFile.close();
			System.out.println("Done");

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exportStatus;

	}

	/**
	 * 
	 * @param filePath
	 * @param instructorID
	 * @param term
	 * @param subject_code
	 * @param course_Code
	 * @param course_title
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */

	public boolean importCSVGPA_Instrutor(String filePath, int instructorID, String term, String subject_code,
			String course_Code, String course_title) throws FileNotFoundException, SQLException {
		boolean uploadStatus = false;
		CsvReader csvGPAFile;
		String strudentID = null;
		String studentGPA = null;
		String updateSQL = "";
		int result = -1;

		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();

		try {
			csvGPAFile = new CsvReader(filePath);
			csvGPAFile.readHeaders();
			while (csvGPAFile.readRecord()) {
				strudentID = csvGPAFile.get("Student_ID").trim();
				studentGPA = csvGPAFile.get("GPA");
				System.out.println("gpa " + studentGPA);

				updateSQL = "UPDATE ignore grade SET `gpa`='" + studentGPA + "' WHERE `student_id`='" + strudentID
						+ "' and course_id in(" + "select course_id from course_details where instructor_id="
						+ instructorID + " and  course_id in(" + "select  course_id from course where course_code = "
						+ course_Code + " and title = '" + course_title + "' and subject_id in("
						+ "select id from subject where subject_code='" + subject_code + "')) and term_id in("
						+ "select id from term_info where term = '" + term + "' ))";

				PreparedStatement selectStatement = conn.prepareStatement(updateSQL);

				result = selectStatement.executeUpdate();
				if (result == 1) {
					uploadStatus = true;
					System.out.println("Updated" + strudentID);
				} else {
					uploadStatus = false;
					System.out.println("Wrong ID " + strudentID);

				}

				System.out.println("SQL Prnt" + updateSQL);

			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return uploadStatus;

	}

	/**
	 * 
	 * @param studentID
	 * @return gpalist
	 */
	public ArrayList<Float> getStudentGPA(int studentID) {
		ArrayList<Float> gpalist = new ArrayList<Float>();
		String sql = "";

		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		sql = "SELECT gpa FROM grade where student_id = " + studentID + " and gpa is not NULL and  course_id  not in("
				+ "select course_id from minisis.course_details where course_id in(select id from minisis.course where course_code in("
				+ "select course_code  from minisis.pre_requisite  where student_id = " + studentID + " and program in ("
				+ "select subject_code from minisis.subject ))))";

		try {
			PreparedStatement gpaStatement = conn.prepareStatement(sql);
			ResultSet result = gpaStatement.executeQuery();
			while (result.next()) {
				gpalist.add(result.getFloat("gpa"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gpalist;
	}
	
	public ArrayList<Float> getStudentGPAofPrerequisite(int studentID) {
		ArrayList<Float> gpalist = new ArrayList<Float>();
		String sql = "";

		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		
		sql = "SELECT gpa FROM grade where student_id = " + studentID + " and gpa is not NULL and  course_id in("
				+ "select course_id from minisis.course_details where course_id in(select id from minisis.course where course_code in("
				+ "select course_code  from minisis.pre_requisite  where student_id = " + studentID + " and program in ("
				+ "select subject_code from minisis.subject ))))";

		try {
			PreparedStatement gpaStatement = conn.prepareStatement(sql);
			ResultSet result = gpaStatement.executeQuery();
			while (result.next()) {
				gpalist.add(result.getFloat("gpa"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gpalist;
	}

	/**
	 * 
	 * @param studentId
	 * @return returnStudentValidity
	 */
	public boolean validateStudentForCGPA(int studentId) {
		boolean returnStudentValidity = false;
		String sql = "select student_id from grade where student_id=?";

		// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();

		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			prepareStm.setInt(1, studentId);
			ResultSet results = prepareStm.executeQuery();
			System.out.println("resul =" + results);
			if (results.next()) {
				while (results.next()) {
					returnStudentValidity = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnStudentValidity;

	}

	public Instructor getInstructorInfo(String email) {
		Instructor instructor = null;
		String sql = "select * from admin where email=?";

		/// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();

		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			prepareStm.setString(1, email);
			ResultSet results = prepareStm.executeQuery();
			while (results.next()) {
				email = results.getString("email");

				int id = results.getInt("id");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				// String mobile = results.getString("mobile");
				String address = results.getString("address");
				int emp_id = results.getInt("emp_id");

				instructor = new Instructor(id, first_name, last_name, email, emp_id, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return instructor;
	}

	public ObservableList<GradesInfo> getGradeViewDetail(int studentID, String term) {
		String sql = null;
		ObservableList<GradesInfo> oblGradeInfo = FXCollections.observableArrayList();

		sql = "SELECT  subject.subject_code,course.course_code,course.title,course.units,grade.gpa  FROM "
				+ "subject inner join  course inner join grade on "
				+ "course.subject_id = subject.id  and course.id = grade.course_id " + "and grade.term_id in ("
				+ "select term_info.id from minisis.term_info where term_info.term = '" + term
				+ "' ) and grade.student_id= " + studentID;
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement psterm = conn.prepareStatement(sql);
			System.out.println(sql);
			ResultSet result = psterm.executeQuery();

			while (result.next()) {

				String subject_Code = result.getString("subject_code");
				String course_Code = result.getString("course_code");
				String courseName = subject_Code + "-" + course_Code;
				String courseTitle = result.getString("title");

				float attempted = result.getFloat("units");
				String gpa = result.getString(("gpa"));

				System.out.println("Float value" + gpa);

				String grade = "";

				if (result.wasNull()) {
					gpa = "-";
					grade = "-";

				} else {
					grade = calGrades(Float.parseFloat(gpa));
				}
				oblGradeInfo.add(new GradesInfo(courseName, courseTitle, attempted, grade, gpa));
				// System.out.println(courseName+ courseTitle + attempted +
				// grade + gpa);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("oblGradeInfo" + oblGradeInfo.size());

		return oblGradeInfo;

	}

	private String calGrades(float gpa) {
		String grade;
		if (gpa <= 2.0) {
			grade = "F";

		} else if (gpa <= 2.7) {

			grade = "C";
		} else if (gpa < 3.0) {

			grade = "B-";
		} else if (gpa < 3.4) {

			grade = "B";
		} else if (gpa < 3.7) {

			grade = "B+";
		} else if (gpa < 4) {

			grade = "A";
		} else if (gpa == 4) {

			grade = "A+";
		} else {
			grade = "-";
		}
		return grade;
	}

	public ArrayList getTermGrade(int studentID) {
		String sql = null;
		ArrayList termList = new ArrayList<>();

		sql = "SELECT term FROM term_info where id in(select term_id from grade where student_id=" + studentID + ")";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement psterm = conn.prepareStatement(sql);
			ResultSet result = psterm.executeQuery();
			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					termList.add(result.getString("term"));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return termList;

	}

	public Student studentProfile(int studentID) {
		Student student = null;
		String sql = "select  student.first_name,student.last_name,student.address,student.level,subject.subject_code "
				+ "from student left join subject on student.subject_id = subject.id where student.id=" + studentID
				+ "";

		// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();

		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			// System.out.println(prepareStm+ " , " + sql);

			ResultSet results = prepareStm.executeQuery();

			while (results.next()) {

				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				String career_Level = results.getString("level");
				String subject_name = results.getString("subject_code");
				String address = results.getString("address");
				student = new Student(first_name, last_name, career_Level, subject_name, address);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public String changeUserPassword(String email, String oldPasswordd, String newPassword) {

		String message = null;

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		String sql = "";
		int result = 0;
		sql = "UPDATE `users` SET `password`= '" + Auth.md5(newPassword) + "' WHERE `email`='" + email
				+ "' and `password` = '" + Auth.md5(oldPasswordd) + "'";

		try {
			PreparedStatement psPWD = conn.prepareStatement(sql);
			System.out.println("ps statment " + psPWD);
			result = psPWD.executeUpdate();
			System.out.println("reslt statment " + result);
			if (result == 1) {
				message = "Congrats! Password has been saved successfully!";
				System.out.println(message);
			} else {
				message = "Please verify your current password!";
				System.out.println(message);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;

	}

	public boolean insertAddUser(String emailId, String passwordID, int userType, String first_Name, String last_Name,
			String address, String program_Name, String mobileNo, String career_Name, String empID) {

		String sql = null;
		String sqlsubjectID = null;
		String table = null;
		String Sqlemail = null;
		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		boolean insertStatus = true;

		try {
			sqlsubjectID = "select id from subject where subject_code = '" + program_Name + "'";
			PreparedStatement prepareStm = conn.prepareStatement(sqlsubjectID);
			ResultSet resultSubject = prepareStm.executeQuery();
			int subjectID = 0;
			while (resultSubject.next()) {
				subjectID = resultSubject.getInt("id");

			}

			sql = "INSERT ignore INTO users (`email`, `password`, `type`) VALUES ('" + emailId + "', '"
					+ Auth.md5(passwordID) + "', '" + userType + "')";

			Statement psAdduser = conn.createStatement();

			int result = psAdduser.executeUpdate(sql);

			if (result == 1) {
				if (userType == 1) {
					table = "student";
					Sqlemail = "INSERT ignore INTO " + table
							+ " (`first_name`,`last_name`,`email`,`mobile`, `address`, `level`,`subject_id`) "
							+ "VALUES ('" + first_Name + "', '" + last_Name + "', '" + emailId + "', '" + mobileNo
							+ "', '" + address + "', '" + career_Name + "', '" + subjectID + "')";
					Statement psAdduseremail = conn.createStatement();
					psAdduseremail.executeUpdate(Sqlemail);

				} else if (userType == 2) {
					table = "admin";
					Sqlemail = "INSERT ignore INTO " + table
							+ " (`first_name`,`last_name`,`email`,`mobile`, `emp_id`,`address` ) " + "VALUES ('"
							+ first_Name + "', '" + last_Name + "', '" + emailId + "', '" + mobileNo + "', '" + empID
							+ "', '" + address + "')";
					Statement psAdduseremail = conn.createStatement();
					psAdduseremail.executeUpdate(Sqlemail);
				} else if (userType == 3) {
					table = "instructor";
					Sqlemail = "INSERT ignore INTO " + table
							+ " (`first_name`,`last_name`,`email`, `emp_id`,`address` ) " + "VALUES ('" + first_Name
							+ "', '" + last_Name + "', '" + emailId + "', '" + empID + "', '" + address + "')";
					Statement psAdduseremail = conn.createStatement();
					psAdduseremail.executeUpdate(Sqlemail);
				} else if (userType == 4) {
					table = "admin";
					Sqlemail = "INSERT ignore INTO " + table
							+ " (`first_name`,`last_name`,`email`,`mobile`, `emp_id`,`address` ) " + "VALUES ('"
							+ first_Name + "', '" + last_Name + "', '" + emailId + "', '" + mobileNo + "', '" + empID
							+ "', '" + address + "')";
					Statement psAdduseremail = conn.createStatement();
					psAdduseremail.executeUpdate(Sqlemail);
				}

				insertStatus = true;

			} else {
				insertStatus = false;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return insertStatus;
	}

	public ArrayList<String> selectSecurityQue() {

		String sql = "";
		ArrayList<String> securityQuestion = new ArrayList<>();

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		sql = "SELECT question FROM security_question";
		try {
			PreparedStatement psSQue = conn.prepareStatement(sql);
			ResultSet resultSubject = psSQue.executeQuery();
			while (resultSubject.next()) {
				securityQuestion.add(resultSubject.getString("question"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return securityQuestion;

	}

	public boolean addSecurityQueAnswer(String oldPassword, String securityQue, String secAnswer, String email) {

		boolean addStatus = false;
		String sql = "";
		String updateSql = "";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();

		sql = "INSERT ignore INTO `minisis`.`user_security_question` (`email`,`sq_id`, `answer`) "
				+ "select users.email, security_question.id,'" + secAnswer + "'  from users join "
				+ "security_question where users.password = '" + Auth.md5(oldPassword)
				+ "' and security_question.question = '" + securityQue + "' and users.email='" + email + "'";

		updateSql = "UPDATE `minisis`.`user_security_question` SET `sq_id`=(select id from security_question where "
				+ "question='" + securityQue + "'), `answer`='" + secAnswer + "' WHERE `email` = "
				+ "(select email from minisis.users where password='" + Auth.md5(oldPassword) + "' and email='" + email
				+ "');";
		Statement psAdduser;

		try {
			if (!isEmailExistSecuritQue(email)) {
				psAdduser = conn.createStatement();
				int result = psAdduser.executeUpdate(sql);
				if (result == 1) {
					addStatus = true;
				} else {
					addStatus = false;
				}
			} else {
				psAdduser = conn.createStatement();
				int resultUpdate = psAdduser.executeUpdate(updateSql);

				if (resultUpdate == 1) {
					addStatus = true;
				} else {
					addStatus = false;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addStatus;

	}

	public boolean isEmailExistSecuritQue(String email) {

		boolean addStatus = false;
		String sql = "";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		sql = "SELECT email FROM user_security_question where email = '" + email + "'";
		Statement psAdduser;
		try {
			PreparedStatement psemailvalid = conn.prepareStatement(sql);
			ResultSet result = psemailvalid.executeQuery();

			while (result.next()) {
				addStatus = true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addStatus;

	}

	public boolean resetPassword(String email, String newPassword) {

		boolean resetStatus = false;
		String sql = "";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		sql = "update  users set password='" + Auth.md5(newPassword) + "' Where email = '" + email + "'";
		Statement psResetPassword;
		try {
			psResetPassword = conn.createStatement();
			int result = psResetPassword.executeUpdate(sql);

			if (result == 1) {
				resetStatus = true;

			} else {
				resetStatus = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resetStatus;

	}

	public boolean forgetPassword(String email, String securityQue, String queAnswer, String newPassowrd) {

		boolean changePwdstatus = false;
		String sql = "";

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		try {
			sql = "update ignore `users` set `password`='" + Auth.md5(newPassowrd) + "'  where  `email` =("
					+ "select email from user_security_question where answer='" + queAnswer + "' and email='" + email
					+ "' and sq_id " + "in(select id from security_question where question='" + securityQue + "'))";
			Statement forgetPwd = conn.createStatement();
			int result = forgetPwd.executeUpdate(sql);
			if (result == 1) {
				changePwdstatus = true;
			} else {
				changePwdstatus = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return changePwdstatus;

	}

	public boolean isUserValid(String email) {
		boolean addStatus = false;
		String sql = "";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		sql = "SELECT email FROM users where email = '" + email + "'";

		try {
			PreparedStatement psemailvalid = conn.prepareStatement(sql);
			ResultSet result = psemailvalid.executeQuery();

			while (result.next()) {
				addStatus = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addStatus;

	}

	public ObservableList<Schedule> ViewSchedule(int studentID, int termID) {
		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();

		ObservableList<Schedule> dataobSchedule = FXCollections.observableArrayList();
		int studentId = studentID;
		int termId = termID;

		String course;
		String day;
		String start_time;
		String end_time;
		int room_num;
		String building;

		System.out.println(studentId);
		System.out.println(termId);

		ArrayList enrollCourseList = null;
		String sqlQuery = "";
		String enrolled = "enrolled";
		try {

			sqlQuery = "select *, CONCAT(program ,course_code) as full_course_name, full_name as building_name from course_schedule join course_details on course_schedule.course_detail_id=course_details.id"
					+ " join schedule on schedule.id=course_schedule.schedule_id join course on course.id=course_details.course_id "
					+ " join room on room.id=course_details.room_id join term_info on term_info.id=course_details.term_id join registration on course_details.id=registration.course_details_id join student"
					+ " on student.id=registration.student_id join building on building.id=room.building_id where  student_id="
					+ "\"" + studentId + "\"" + "and status=" + "\"" + enrolled + "\"" + "and term_id=" + "\"" + termId
					+ "\"";

			System.out.println(sqlQuery);
			PreparedStatement courseList = conn.prepareStatement(sqlQuery);

			ResultSet result = courseList.executeQuery();
			ArrayList Rows = new ArrayList();
			while (result.next()) {

				course = result.getString("full_course_name");
				System.out.println(result.getString("full_course_name"));
				day = result.getString("day");
				System.out.println(result.getString("day"));
				start_time = result.getString("start_time");
				System.out.println(result.getString("start_time"));
				end_time = result.getString("end_time");
				System.out.println(result.getString("end_time"));
				room_num = Integer.parseInt(result.getString("room_no"));
				System.out.println(result.getString("room_no"));
				building = result.getString("building_name");
				System.out.println(result.getString("building_name"));

				schedule = new Schedule(course, day, start_time, end_time, room_num, building);
				// System.out.println("------course id----" +
				// course.getCourseId());
				dataobSchedule.add(new Schedule(schedule.getCourse(), schedule.getDay(), schedule.getStart_time(),
						schedule.getEnd_time(), schedule.getRoom_num(), schedule.getBuilding()));
				System.out.println(" ----------------------------------  obdata   " + dataobSchedule);

			}

		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		return dataobSchedule;
	}
	public ArrayList<String> getAlreadyEnrolledSchedule(int studentID, int termID) {	
		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		int studentId = studentID;		
		int termId = termID;
		
		ArrayList<String> schedules=new ArrayList<String>();
		String day;
		String start_time;
		String end_time;

		System.out.println(studentId);
		System.out.println(termId);

		ArrayList enrollCourseList = null;
		String sqlQuery = "";
		String enrolled="enrolled";
		try {

			sqlQuery =	"select *, CONCAT(program ,course_code) as full_course_name, full_name as building_name from course_schedule join course_details on course_schedule.course_detail_id=course_details.id"+
					" join schedule on schedule.id=course_schedule.schedule_id join course on course.id=course_details.course_id "+
					" join room on room.id=course_details.room_id join term_info on term_info.id=course_details.term_id join registration on course_details.id=registration.course_details_id join student"+
					" on student.id=registration.student_id join building on building.id=room.building_id where  student_id="+"\""+studentId +"\""+"and status="+"\""+enrolled+"\""+ "and term_id="+"\""+termId+"\"";

			System.out.println(sqlQuery);
			PreparedStatement scheduleList = conn.prepareStatement(sqlQuery);



			ResultSet result = scheduleList.executeQuery();
			while (result.next()) {
				StringBuilder schedule=new StringBuilder();
				day=result.getString("day");
				System.out.println(result.getString("day"));
				start_time=result.getString("start_time");	
				System.out.println(result.getString("start_time"));
				end_time=result.getString("end_time");
				System.out.println(result.getString("end_time"));
                schedule.append(day).append(" ").append(start_time).append(" ").append(end_time);
                schedules.add(schedule.toString());
			}
			System.out.println("------------schedule------------------");
			for(int i=0;i<schedules.size();i++)
				System.out.println(schedules.get(i));

		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		return schedules;
	}
	
	public ArrayList<String> getSearchSchedule(String level, int termID,String program,int courseId) {	
		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		ArrayList<String> schedules=new ArrayList<String>();
		String day;
		String start_time;
		String end_time;

		ArrayList enrollCourseList = null;
		String sqlQuery = "";

		try {

			sqlQuery =	"SELECT *\r\n" + 
					"FROM course \r\n" + 
					"JOIN course_details \r\n" + 
					"ON course.id = course_details.course_id \r\n" + 
					"JOIN course_schedule ON course_schedule.course_detail_id=course_details.id\r\n" + 
					"join schedule on schedule.id=course_schedule.schedule_id\r\n" + 
					"WHERE course_details.term_id =\r\n"  +"\""+termID +"\""+
					"AND level = " +"\""+level +"\""+"AND program ="+"\""+program +"\"" +" and course_code="+ "\""+courseId +"\"";

			System.out.println(sqlQuery);
			PreparedStatement scheduleList = conn.prepareStatement(sqlQuery);
			System.out.println("-----check----------");
            System.out.println("course_code"+ courseId);


			ResultSet result = scheduleList.executeQuery();
			while (result.next()) {
				StringBuilder schedule=new StringBuilder();
				day=result.getString("day");
				System.out.println(result.getString("day"));
				start_time=result.getString("start_time");	
				System.out.println(result.getString("start_time"));
				end_time=result.getString("end_time");
				System.out.println(result.getString("end_time"));
                schedule.append(day).append(" ").append(start_time).append(" ").append(end_time);
                schedules.add(schedule.toString());
			}
			System.out.println("------------schedule of course------------------");
			for(int i=0;i<schedules.size();i++)
				System.out.println(schedules.get(i));

		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		return schedules;
	}


	public boolean addtermDetail(String term, LocalDate termStartDate, LocalDate termEndDate,
			LocalDate termRegStartDate) {

		boolean addTermStatus = false;
		String sql = "";

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();

		Statement forgetPwd;
		try {
			sql = "INSERT ignore INTO `term_info` (`term`, `start_date`, `end_date`, `registration_start`) VALUES ('"
					+ term + "', '" + termStartDate + "', '" + termEndDate + "', '" + termRegStartDate + "');";
			forgetPwd = conn.createStatement();
			int result = forgetPwd.executeUpdate(sql);
			if (result == 1) {
				addTermStatus = true;
			} else {
				addTermStatus = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addTermStatus;

	}

	public ArrayList<String> selectCourseName(String subjectName) {

		String sql = "";
		ArrayList<String> courseList = new ArrayList<>();

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		sql = "Select concat(course_code,'-',title) as courseName from course where subject_id in(select id from subject  where subject_code='"
				+ subjectName + "')";
		try {
			PreparedStatement psSQue = conn.prepareStatement(sql);
			ResultSet resultSubject = psSQue.executeQuery();
			while (resultSubject.next()) {
				courseList.add(resultSubject.getString("courseName"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return courseList;

	}

	public ArrayList<String> selectbuilding() {

		String sql = "";
		ArrayList<String> buidlingList = new ArrayList<>();

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		sql = "SELECT name FROM building";
		try {
			PreparedStatement psSQue = conn.prepareStatement(sql);
			ResultSet resultSubject = psSQue.executeQuery();
			while (resultSubject.next()) {
				buidlingList.add(resultSubject.getString("name"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return buidlingList;

	}

	public ArrayList<String> selectbuildingRoom(String buildingName) {

		String sql = "";
		ArrayList<String> buidlingRoomList = new ArrayList<>();

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		sql = "SELECT room_No FROM room where building_id in(select id from minisis.building where name ='"
				+ buildingName + "')";
		try {
			PreparedStatement psSQue = conn.prepareStatement(sql);
			ResultSet resultSubject = psSQue.executeQuery();
			while (resultSubject.next()) {
				buidlingRoomList.add(resultSubject.getString("room_No"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return buidlingRoomList;

	}

	public boolean addCourseDetail(String courseName, String roomNo, String instructorName, String TermName,
			int duration) {

		boolean addStatus = false;
		String sql = "";

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();

		sql = "INSERT ignore INTO `course_details` ( `course_id`, `instructor_id`, `term_id`, `duration`, `room_id`,`class_Capacity`,`class_availablity`) "
				+ "select course.id,minisis.instructor.id,term_info.id,'" + duration
				+ "',room.id,room.capacity,room.capacity from "
				+ "course join instructor join term_info join room where "
				+ "concat(course.course_code,'-',course.title) = '" + courseName + "' and " + "room.room_no ='" + roomNo
				+ "' and" + " concat(instructor.first_name,' ',instructor.last_name) ='" + instructorName + "'"
				+ " and term_info.term ='" + TermName + "';";

		Statement addCourseDetail;

		try {
			addCourseDetail = conn.createStatement();
			int result = addCourseDetail.executeUpdate(sql);
			if (result == 1) {
				addStatus = true;
			} else {
				addStatus = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addStatus;

	}

	public ArrayList<String> selectInstructorName() {

		String sql = "";
		ArrayList<String> instructorNameList = new ArrayList<>();

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;

		conn = obj.getConnection();

		sql = "SELECT concat(first_name,' ',last_name) as full_Name FROM instructor";
		try {
			PreparedStatement psSQue = conn.prepareStatement(sql);
			ResultSet resultSubject = psSQue.executeQuery();
			while (resultSubject.next()) {

				instructorNameList.add(resultSubject.getString("full_Name"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return instructorNameList;

	}
}
