package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import DTO.Student;
import DTO.Term;
import controller.LoginController;
import database.MySQLAccess;

public class ViewCGPAModel {

	private DAO termInfo;

	private Term termDetail;


	MySQLAccess obj = new MySQLAccess();
	Connection conn = obj.getConnection();

	public ArrayList getCGPA(int studentID) {
		String userEmailID = null;
		String userType = null;

		int termID = 0;
		String point = null;
		String term_name = null;

		

		termInfo = new DAO();
		termDetail = new Term();
		termDetail = termInfo.termList(studentID);
		termID = termDetail.getTermid();
		System.out.println("termid" + termID);

		ArrayList listCGPA = new ArrayList();
		String sql = "";
		try {
			sql = "select distinct course_details_id, CONCAT( sbj.subject_code, '-', cs.course_code, ' ', cs.title ) AS course_title, ti.term, "
					+ " IF(EXISTS(select gpa from grade where course_id=rg.course_details_id), "
					+ " (select gpa from grade where course_id=rg.course_details_id), '') as gpa, "
					+ " (select grade_scale from grading_points grp, grade g where g.gpa = grp.points and g.course_id=rg.course_details_id) as grade_scale,"
					+ " cs.units as unit "
					+ " from registration rg, grade grd, course cs, course_details cd, term_info ti, subject sbj,grading_points gp"
					+ " where rg.student_id=" + studentID + " and rg.course_details_id=cd.id"
					+ " and cd.course_id=cs.id" + " and cd.term_id=ti.id" + " and cs.subject_id= sbj.id"
					+ " and cd.term_id=" + termID + " and grd.gpa = gp.points";
			PreparedStatement prepareStm = conn.prepareStatement(sql);
			ResultSet results = prepareStm.executeQuery();
			ArrayList<ArrayList<String>> Rows = new ArrayList<ArrayList<String>>();

			while (results.next()) {
				System.out.println("course title : "+ results.getString("course_title"));
				System.out.println("course gpa: "+ results.getString("gpa"));
				System.out.println("course gpa-scale: "+results.getString("grade_scale"));
				term_name = results.getString("term");
				System.out.println("course term: "+term_name);

				ArrayList<String> row = new ArrayList<String>();

				for (int i = 1; i <= 1; i++) {
					row.add(results.getString("course_title"));
					if (!results.getString("gpa").equals("-1")) {
						row.add(results.getString("gpa"));
					} else {
						row.add("-");
					}
					if (!results.getString("gpa").equals("-1")) {
						row.add(results.getString("grade_scale"));
					} else {
						row.add("-");
					}

					row.add(results.getString("unit"));
					// row.add(results.getString("point"));

					if (!results.getString("gpa").equals("-1") && results.getString("grade_scale") != null) {
						point = (String.valueOf(Double.parseDouble(results.getString("gpa"))
								* Double.parseDouble(results.getString("unit"))));
						row.add(point);
					} else {
						row.add("-");
					}
				}
				Rows.add(row);
				System.out.println("row Value " + Rows);
			}

		} catch (SQLException e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());

		}
		return null;
	}

}
