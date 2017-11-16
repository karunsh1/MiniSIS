package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
				// =======

				// public ArrayList courseIds(String term) {
				// String sql = null;
				// ArrayList courseIdList = new ArrayList();
				//
				// sql = "select course_code from course where ";
				// MySQLAccess obj = new MySQLAccess();
				// Connection conn = obj.getConnection();
				// try {
				// PreparedStatement courseId = conn.prepareStatement(sql);
				// ResultSet result = .executeQuery();
				//
				// while (result.next()) {
				// for (int i = 1; i <= 1; i++) {
				// courseIdList.add(result.getString("course_code"));
				// }
				//
				// }
				// } catch (SQLException e) {
				// //
				// e.printStackTrace();
				// }
				//
				// return termNameList;
				//
				// }
				// >>>>>>> 023f9ef0069d67b0b49df06d64fbeb4a84600245

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
	 * 
	 * @param instrutorID
	 * @return deptList
	 */

	public ArrayList<String> careerList_ins(int instrutorID) {
		String sql = null;
		ArrayList<String> deptList = new ArrayList<String>();

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
		sql = "SELECT id, first_name, last_name FROM  minisis.student  WHERE id IN ("
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
			System.out.println("resul ="+results);
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
	
	public Instructor getInstructorInfo(String email){
		Instructor instructor = null;
		String sql = "select * from admin where email=?";
		
		/// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();
		
		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			prepareStm.setString(1, email);
			ResultSet results = prepareStm.executeQuery();
			while(results.next()){
				email = results.getString("email");
				
				int id = results.getInt("id");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				//String mobile = results.getString("mobile");
				String address = results.getString("address");
				int emp_id = results.getInt("emp_id");
	
				instructor = new Instructor(id, first_name, last_name, email, emp_id, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return instructor;
	}
		
		
	

}