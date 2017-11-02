package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import DTO.StudentPayment;
import database.MySQLAccess;

public class PaymentModel {
	
	protected String tableName = "payment";
	
	public boolean create(StudentPayment payment){
		String sql = "insert into "+ tableName +" (student_id,paid_amount) values (?, ?)";
		MySQLAccess dbConnection = new MySQLAccess();
		Connection connection = dbConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, payment.getStudent_id());
			statement.setDouble(2, payment.getPaid_amount());
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public StudentPayment totalPaidAmount(int studentId) {
		StudentPayment paidAmount = null;
		
		String sql = "select id,student_id,SUM(paid_amount) as total_paid_amount from "+ tableName +" where student_id=?";
		
		// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();
		int id = 0;
		int student_Id = 0;

		Double totalPaidAmount = 0.0;
		
		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			prepareStm.setInt(1, studentId);
			ResultSet results = prepareStm.executeQuery();
			while(results.next()){
				 id = results.getInt("id");
				 student_Id = results.getInt("student_id");
				 totalPaidAmount = results.getDouble("total_paid_amount");
				System.out.println("Due amount is: " + totalPaidAmount);
			}
			
			paidAmount = new StudentPayment(id, student_Id, totalPaidAmount);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paidAmount;
	}
}
