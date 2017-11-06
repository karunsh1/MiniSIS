package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.*;

public class TermInfoModel {

	public ArrayList termList(int studentId) {

		ArrayList termList = null;

		try {

			String sql = "select distinct ti.id,ti.term from registration rgs, course_details cd, term_info ti"+
					" where rgs.student_id="+ studentId +
					" and rgs.course_details_id=cd.id"+
					" and cd.term_id=ti.id";
			
			System.out.println(sql);
			
			// Establish Connection
			MySQLAccess obj = new MySQLAccess();
			Connection conn = obj.getConnection();

			PreparedStatement term = conn.prepareStatement(sql);
			
			ResultSet result = term.executeQuery();
			ArrayList Rows = new ArrayList();
			while (result.next()) {
				// System.out.println(dbID + dbName + dbStatusClient);
				ArrayList row = new ArrayList();
				for (int i = 1; i <= 1; i++) {
					row.add(result.getString("id"));
					row.add(result.getString("term"));
				}
				Rows.add(row);
				termList = Rows;
			}

		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		return termList;
	}
	
	public ArrayList termList() {

		ArrayList termList = null;

		try {

			String sql = "select id, term from term_info";
			
			System.out.println(sql);
			
			// Establish Connection
			MySQLAccess obj = new MySQLAccess();
			Connection conn = obj.getConnection();

			PreparedStatement term = conn.prepareStatement(sql);
			
			ResultSet result = term.executeQuery();
			ArrayList Rows = new ArrayList();
			while (result.next()) {
				// System.out.println(dbID + dbName + dbStatusClient);
				ArrayList row = new ArrayList();
				for (int i = 1; i <= 1; i++) {
					row.add(result.getString("id"));
					row.add(result.getString("term"));
				}
				Rows.add(row);
				termList = Rows;
			}

		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		return termList;
	}

}