package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.MySQLAccess;
import DTO.PaymentDue;

public class DueAmountModel {
	protected String tableName = "due";
	
	public PaymentDue totalDueAmount(int studentId) {
		PaymentDue dueAmount = null;
		
		String sql = "select id,student_id,term_id,SUM(due_amount) as total_amount from "+ tableName +" where student_id=?";
		
		// Establish Connection
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();
		int id = 0;
		int student_Id = 0;
		int term_id = 0;
		Double payableDueAmount = 0.0;
		
		try {
			PreparedStatement prepareStm = connection.prepareStatement(sql);
			prepareStm.setInt(1, studentId);
			ResultSet results = prepareStm.executeQuery();
			while(results.next()){
				 id = results.getInt("id");
				 student_Id = results.getInt("student_id");
				 term_id = results.getInt("term_id");
				 payableDueAmount = results.getDouble("total_amount");
				System.out.println("Due amount is: "+payableDueAmount);
			}
			
			dueAmount = new PaymentDue(id, student_Id, term_id, payableDueAmount);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dueAmount;
	}
}
