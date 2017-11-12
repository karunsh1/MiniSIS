package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import DTO.Admin;
import DTO.Instructor;
import DTO.Term;
import database.*;

public class DAO {

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
				// System.out.println(dbID + dbName + dbStatusClient);
				ArrayList row = new ArrayList();
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

	
	
	/*
	 * public ArrayList termList() {
	 * 
	 * ArrayList termList = null;
	 * 
	 * try {
	 * 
	 * String sql = "select id, term from term_info";
	 * 
	 * System.out.println(sql);
	 * 
	 * // Establish Connection MySQLAccess obj = new MySQLAccess(); Connection
	 * conn = obj.getConnection();
	 * 
	 * PreparedStatement term = conn.prepareStatement(sql);
	 * 
	 * ResultSet result = term.executeQuery(); ArrayList Rows = new ArrayList();
	 * while (result.next()) { // System.out.println(dbID + dbName +
	 * dbStatusClient); ArrayList row = new ArrayList(); for (int i = 1; i <= 1;
	 * i++) { row.add(result.getString("id"));
	 * row.add(result.getString("term")); } Rows.add(row); termList = Rows; }
	 * 
	 * } catch (Exception e) {
	 * System.out.println("Something went wrong. Please contact system admin.");
	 * System.err.println(e.getMessage()); } return termList; }
	 */
	public ArrayList<String> termNames() {
		String sql = null;
		ArrayList<String> termNameList = new ArrayList();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return termNameList;

	}
<<<<<<< HEAD
	/**
	 * 
	 * term list as per instructor
	 * 
	 */
	public ArrayList<String> termNames( String instructorID) {
		String sql = null;
		ArrayList<String> termNameList = new ArrayList();

		sql = "SELECT  term FROM term_info where id in(select term_id from course_details where instructor_id = "+instructorID+")";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();

			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					termNameList.add(result.getString("term"));
				}
=======
	
//	public ArrayList courseIds(String term) {
//		String sql = null;
//		ArrayList courseIdList = new ArrayList();
//
//		sql = "select course_code from course where ";
//		MySQLAccess obj = new MySQLAccess();
//		Connection conn = obj.getConnection();
//		try {
//			PreparedStatement courseId = conn.prepareStatement(sql);
//			ResultSet result = .executeQuery();
//
//			while (result.next()) {
//				for (int i = 1; i <= 1; i++) {
//					courseIdList.add(result.getString("course_code"));
//				}
//
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return termNameList;
//
//	}
>>>>>>> 023f9ef0069d67b0b49df06d64fbeb4a84600245

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
	 * @return
	 */

	public ArrayList departmentNames() {
		String sql = null;
		ArrayList departmentList = new ArrayList();

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
	 * Instructor ID
	 * 
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
	 * Course Name for Instructor
	 */
	public ArrayList Instructor_Courses(int instrutorID) {
		String sql = null;
		ArrayList CoursetList = new ArrayList();

		sql = "select course_code,title from course where id in(select course_id from course_details where instructor_id="
				+ instrutorID + ")";
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
					CoursetList.add(CourseID + "-" + CourseTitle);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return CoursetList;

	}

	/**
	 * instructor Department where he teaches
	 * 
	 * 
	 */
	public ArrayList Instructor_Dept(int instrutorID) {
		String sql = null;
		ArrayList deptList = new ArrayList();

		sql = "select subject_code from subject where id in (select subject_id from course where  id  in( select course_id  from course_details where instructor_id="
				+ instrutorID + "))";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();

			while (result.next()) {
				for (int i = 1; i <= 1; i++) {

					deptList.add(result.getString("subject_code"));
					;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return deptList;

	}

	/**
	 * Career Leve Grad or UnderGrad
	 */

	public ArrayList careerList_ins(int instrutorID) {
		String sql = null;
		ArrayList deptList = new ArrayList();

		sql = "select level from minisis.course where id in (select course_id from minisis.course_details where instructor_id ="
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
    
	
	public ArrayList courseList(int term_id, String level) {
		String sql = null;
		ArrayList courseList = new ArrayList();
        sql="SELECT course.course_code\r\n" + 
        		"FROM course \r\n" + 
        		"JOIN course_details \r\n" + 
        		"ON course.id = course_details.course_id \r\n" + 
        		"WHERE course_details.term_id =" + term_id +" \r\n" +
        		"OR level = " + "\""+level+ "\"";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return courseList;

	}
	/**
	 * CSV Export from Database
	 */
	public void exportCSVStudent_Inst_uploadGrades(String filePath, String IntructorID, String termID) {

		String fileName = filePath + "\\" + "Student_Result.csv";
		String sql = "";

		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		sql = "Select id,first_name,last_name from student where id in(select student_id from registration where status ='enrolled' and"
				+ "  course_details_id in(select id from course_details where instructor_id =" + IntructorID
				+ " and term_id =" + termID + "))";
		boolean alreadyExists = new File(fileName).exists();

		try {
			CsvWriter csvStudentFile = new CsvWriter(new FileWriter(fileName, true), ',');
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
	
	public void importCSVGPA_Instrutor(String filePath) throws FileNotFoundException, SQLException{
		
	CsvReader csvGPAFile;
	String strudentID = null;
	String studentGPA = null;
	String selectsql = "";
	String insertsql = "";
	
	MySQLAccess obj = new MySQLAccess();
	Connection conn = obj.getConnection();
	
	
	
	
	try {
		csvGPAFile = new CsvReader(filePath);
		csvGPAFile.readHeaders();
		while(csvGPAFile.readRecord()){			
			strudentID = csvGPAFile.get("Student_ID");
			selectsql = "SELECT student_id,gpa FROM grade where student_id "+strudentID+" course_id in( select course_id from course_details where id in(select course_details_id from registration))";
			PreparedStatement selectStatement = conn.prepareStatement(selectsql);
			if(selectStatement.executeQuery().absolute(1))
			{
				studentGPA = csvGPAFile.get("GPA");
				selectStatement.setString(2, studentGPA);
			}
			
			
			
			
			
		}
		
		
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
				
		
	}

}