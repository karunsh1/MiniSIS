package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MySQLAccess;
import DTO.Admin;

public class AdminModel {
	
	public Admin selectAdmin(String email) {
		Admin admin = null;
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
				String mobile = results.getString("mobile");
				String address = results.getString("address");
				int emp_id = results.getInt("emp_id");
	
				admin = new Admin(id, first_name, last_name, email,mobile, emp_id, address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return admin;
	}
}
