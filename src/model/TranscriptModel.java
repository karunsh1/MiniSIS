package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.MySQLAccess;

public class TranscriptModel {
	
	public ArrayList<ArrayList<String>> getFullTrascrip(int studentId){
		ArrayList<ArrayList<String>> Rows = null;
		
		// Check this functionality for a new student who does not have any grade yet or not registered any semester yet
		MySQLAccess obj = new MySQLAccess();
		Connection connection = obj.getConnection();
		
//		String sql = "SELECT ti.id, ti.term, CONCAT(sbj.subject_code,'-',cs.course_code,' ', cs.title) as course_title, gpa, grade_scale," +
//				" cs.units as unit, gpa*cs.units as point FROM registration_cart rgs, course_details cd, term_info ti, course cs, subject sbj, grade grd, grading_points gp" + 
//				" where rgs.student_id="+studentId+ 
//				" and cs.id = cd.course_id " +  
//				" and cd.id=rgs.course_details_id " + 
//				" and cs.subject_id=sbj.id " +  
//				" and cd.term_id=ti.id " + 
//				" and grd.course_id=rgs.course_details_id " +
//				" and grd.gpa=gp.points";
		String sql = "select distinct course_details_id, CONCAT( sbj.subject_code, '-', cs.course_code, ' ', cs.title ) AS course_title, ti.term, "+
						" IF(EXISTS(select gpa from grade where course_id=rg.course_details_id), "+ 
						" (select gpa from grade where course_id=rg.course_details_id), '') as gpa, "+ 
						" (select grade_scale from grading_points grp, grade g where g.gpa = grp.points and g.course_id=rg.course_details_id) as grade_scale,"+
						" cs.units as unit " +
						" from registration_cart rg, grade grd, course cs, course_details cd, term_info ti, subject sbj,grading_points gp" +
						" where rg.student_id="+studentId+
						" and rg.course_details_id=cd.id" +
						" and cd.course_id=cs.id" +
						" and cd.term_id=ti.id" +
						" and cs.subject_id= sbj.id" +
						" and grd.gpa = gp.points";
		
		System.out.println(sql);
		String point;
		PreparedStatement prepareStm;
		
		try {
			prepareStm = connection.prepareStatement(sql);
			ResultSet results = prepareStm.executeQuery();
			//make an String type arrayList containg all the info about course and GPA
			Rows = new ArrayList<ArrayList<String>>();
			while(results.next()){
//				System.out.println(results.getString("course_title"));
//				System.out.println(results.getString("gpa"));
//				System.out.println(results.getString("grade_scale"));
				
				
				ArrayList<String> row = new ArrayList<String>();
				for (int i = 1; i <= 1 ; i++){
					row.add(results.getString("term"));
			    	row.add(results.getString("course_title"));
			    	row.add(results.getString("gpa"));
			    	row.add(results.getString("grade_scale"));
			    	row.add(results.getString("unit"));
			    	if(!results.getString("gpa").equals("") && results.getString("grade_scale") != null){
			    		point = (String.valueOf(Double.parseDouble(results.getString("gpa")) * Double.parseDouble(results.getString("unit"))));
			    		row.add(point);
			    	} else {
			    		row.add("");
			    	}
			    	System.out.println(results.getString("unit"));
					System.out.println(results.getString("gpa"));
					System.out.println(results.getString("grade_scale"));
			    }
				Rows.add(row);
				
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		
		return Rows;
	}
}
