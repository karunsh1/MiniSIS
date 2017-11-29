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
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import DTO.Admin;
import DTO.GradesInfo;
import DTO.Instructor;
import DTO.Student;
import DTO.Term;
import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Auth;

public class DAO {

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

		sql = "SELECT course.program, course.title,course.course_code,course.description,course.level, course.units,course_details.id as course_details_id,term_info.term from course join course_details on course.id =course_details.course_id inner join \r\n"
				+ "term_info on  course_details.term_id= term_info.id where course_code= " + "\"" + course_code + "\""
				+ "and program=" + "\"" + program + "\"" + "and level=" + "\"" + level + "\"" + "and term=" + "\""
				+ term + "\"";

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

	public boolean addCourse(String studentId, String course_details_id) {
		String sql = null;
		String enrolled = "enrolled";
		sql = "INSERT INTO registration (student_id, course_details_id, status)" + "VALUES (" + "\"" + studentId + "\""
				+ "," + "\"" + course_details_id + "\"" + "," + "\"" + enrolled + "\"" + ")";
		System.out.println(sql);
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ResultSet result = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void exportCSVStudent_Inst_uploadGrades(File file, String instructorID, String term, String dept,
			String courseCode, String courseTitle) {

		String sql = "";

		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		sql = "SELECT id, first_name, last_name FROM  student  WHERE id IN ("
				+ "SELECT student_id FROM registration WHERE  status = 'enrolled'  AND course_details_id IN ("
				+ "SELECT id FROM course_details WHERE instructor_id =" + instructorID + " AND term_id IN ("
				+ "SELECT id  FROM  term_info   WHERE  term = '" + term + "') AND course_id IN ("
				+ "SELECT id FROM  course  WHERE course_code =" + courseCode + "  AND title = '" + courseTitle
				+ "'  AND subject_id IN (" + "SELECT id  FROM  subject  WHERE  subject_code = '" + dept + "'))))";

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
			ResultSet result = studentInfo.executeQuery();
			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					csvStudentFile.write(result.getString("id"));
					csvStudentFile.write(result.getString("first_Name"));
					csvStudentFile.write(result.getString("last_name"));
					csvStudentFile.endRecord();
				}

			}
			csvStudentFile.close();
			System.out.println("Done");

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public void importCSVGPA_Instrutor(String filePath, int instructorID, String term, String subject_code,
			String course_Code, String course_title) throws FileNotFoundException, SQLException {

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

				updateSQL = "UPDATE grade SET `gpa`='" + studentGPA + "' WHERE `student_id`='" + strudentID
						+ "' and course_id in(" + "select course_id from course_details where instructor_id="
						+ instructorID + " and  course_id in(" + "select  course_id from course where course_code = "
						+ course_Code + " and title = '" + course_title + "' and subject_id in("
						+ "select id from subject where subject_code='" + subject_code + "')) and term_id in("
						+ "select id from term_info where term = '" + term + "' ))";

				PreparedStatement selectStatement = conn.prepareStatement(updateSQL);

				result = selectStatement.executeUpdate();
				if (result == 1) {
					System.out.println("Updated" + strudentID);
				} else {
					System.out.println("Wrong ID " + strudentID);

				}

				System.out.println("SQL Prnt" + updateSQL);

			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
		sql = "SELECT gpa FROM grade where student_id = " + studentID + " and gpa is not NULL";

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

	public boolean forgetPassword(String email, String securityQue, String queAnswer, String newPassowrd) {

		boolean changePwdstatus = false;
		String sql = "";

		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		try {
			sql = "update ignore `users` set `password`='" + Auth.md5(newPassowrd) + "'  where  `email` =("
					+ "select email from user_security_question where answer='" + queAnswer + "' and email='"
					+ email + "' and sq_id " + "in(select id from security_question where question='"
					+ securityQue + "'))";
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
	
	public boolean isUserValid(String email){
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

}