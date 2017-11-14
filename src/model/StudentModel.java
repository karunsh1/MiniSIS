package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MySQLAccess;
import DTO.Student;

public class StudentModel {
	protected String tableName = "student";
	
	public Student selectStudent(String email) {
		Student student = null;
		String sql = "select * from "+ tableName +" where email=?";
		
		// Establish Connection
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
				String mobile = results.getString("mobile");
				String address = results.getString("address");
	
				student = new Student(id, first_name, last_name, email,mobile, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	public Student selectStudent(int studentID) {
		Student student = null;
		String sql = "SELECT student.first_name,student.last_name,student.level,subject.subject_code from student "
				+ "left join subject on student.subject_id = subject.id where student.id = "+studentID+"";
		
		// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();
		
		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			//System.out.println(prepareStm+ "  ,             " +  sql);
			
			ResultSet results = prepareStm.executeQuery();
			while(results.next()){
				
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				String career_Level = results.getString("level");
				String subject_name = results.getString("subject_code");
				
				//System.out.println("first_name "+ first_name + " last_name " + last_name +" career_name "+ career_Level +" subject"+ subject_name );
	
				student = new Student(first_name, last_name, career_Level,subject_name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	public boolean validateStudent(int studentId){
		boolean returnStudentValidity = false;
		String sql = "select * from "+ tableName +" where id=?";
		
		// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();
		
		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			prepareStm.setInt(1, studentId);
			ResultSet results = prepareStm.executeQuery();
			while(results.next()){
				returnStudentValidity = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnStudentValidity;
	}
}
