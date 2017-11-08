package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public ArrayList termNames() {
		String sql = null;
		ArrayList termNameList = new ArrayList();
		

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
	
	/**
	 * Department Name
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
	/**
	 * Course Name for Instructor
	 */
	public ArrayList Instructor_Courses(int instrutorID) {
		String sql = null;
		ArrayList CoursetList = new ArrayList();
		

		sql = "select course_code,title from course where id in(select course_id from course_details where instructor_id="+ instrutorID +")";
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
					CoursetList.add(CourseID+"-"+ CourseTitle);					
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
		

		sql = "select subject_code from subject where id in (select subject_id from course where  id  in( select course_id  from course_details where instructor_id="+ instrutorID +"))";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();
			
			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					
					deptList.add(result.getString("subject_code"));;					
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
		

		sql = "select level from minisis.course where id in (select course_id from minisis.course_details where instructor_id ="+ instrutorID +")";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		try {
			PreparedStatement term = conn.prepareStatement(sql);
			ResultSet result = term.executeQuery();
			
			while (result.next()) {
				for (int i = 1; i <= 1; i++) {
					
					deptList.add(result.getString("level"));;					
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return deptList;

	}
	
	/**
	 * CSV Export from Database
	 */
	public File exportCSVStudent_Inst_uploadGrades(String filePath, String IntructorID ,String termID ){
		
		String fileName = "Student_Result";
		String sql = "";
		
				
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		sql = "Select id,first_name,last_name from minisis.student where id in(select student_id from minisis.registration where status ='enrolled' and"
				+ "  course_details_id in(select id from minisis.course_details where instructor_id ="+IntructorID+" and term_id ="+termID+"))";
		
		try {
			PrintWriter pw = new PrintWriter(new File(filePath+ "\\" +fileName));
			StringBuilder sb = new StringBuilder();
	        sb.append("id");
	        sb.append(',');
	        sb.append("first_name");
	        sb.append(',');
	        

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
		
	

}