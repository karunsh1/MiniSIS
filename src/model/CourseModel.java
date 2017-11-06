package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;*/

import database.*;
//import com.msis.DTO.CourseCart;

public class CourseModel {
	
			// Establish Connection
				MySQLAccess obj = new MySQLAccess();
				Connection conn = obj.getConnection();

	public ArrayList courseListCart(int studentId) {

		ArrayList courseListCart = null;
		String sql = "";

		try {

			sql += "SELECT cd.id, ti.term as term,CONCAT(ins.first_name,' ',ins.last_name) as instructor,GROUP_CONCAT(CONCAT(sch.day,' ',sch.start_time,' - ',sch.end_time) SEPARATOR ' & ') as time,";
			sql += " CONCAT(bld.name,' ',rm.room_no,' ',bld.campus_short) as location, CONCAT(sbj.subject_code,' ',cs.course_code,' - ',cs.title) as course";
			sql += ", if((cd.class_capacity -(select count(*) from registration rgs where cd.id=rgs.course_details_id))>0,'Open','Closed') as status,";
			sql += " cs.units as unit";
			sql += " FROM course_details cd, course cs, subject sbj, term_info ti, instructor ins, schedule sch, course_schedule csch, room rm, building bld, registration_cart rc";
			sql += " WHERE rc.course_details_id=cd.id AND";
			sql += " cd.term_id=ti.id AND";
			sql += " cd.instructor_id=ins.id AND";
			sql += " cd.room_id=rm.id AND";
			sql += " rm.building_id =bld.id AND";
			sql += " cd.course_id=cs.id AND";
			sql += " cs.subject_id=sbj.id and";
			sql += " csch.schedule_id=sch.id AND";
			sql += " csch.course_detail_id=cd.id AND";
			sql += " rc.student_id=?";
			sql += " GROUP BY csch.course_detail_id";

			

			PreparedStatement course = conn.prepareStatement(sql);
			// PreparedStatement course = conn.prepareStatement(" select id,
			// name, status_client from test.projects where client=? ");
			course.setInt(1, studentId);

			ResultSet result = course.executeQuery();
			ArrayList Rows = new ArrayList();
			while (result.next()) {
				// System.out.println(dbID + dbName + dbStatusClient);
				ArrayList row = new ArrayList();
				for (int i = 1; i <= 1; i++) {
					row.add(result.getString("id"));
					row.add(result.getString("course"));
					row.add(result.getString("instructor"));
					row.add(result.getString("term"));
					row.add(result.getString("time"));
					row.add(result.getString("location"));
					row.add(result.getString("unit"));
					row.add(result.getString("status"));
				}
				Rows.add(row);
				courseListCart = Rows;
			}

		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		return courseListCart;
	}

	public ArrayList EnrollCourseList(int studentID, int termID) {	
		 
		int studentId = studentID;		
		int termId = termID;
		System.out.println(studentId);
		System.out.println(termId);
		
		ArrayList enrollCourseList = null;
		String sqlQuery = "";

		try {

			sqlQuery = "SELECT cd.id, ti.term as term,CONCAT(ins.first_name,' ',ins.last_name) as instructor,GROUP_CONCAT(CONCAT(sch.day,' ',sch.start_time,' - ',sch.end_time) SEPARATOR ' & ') as time,"
					+ " CONCAT(bld.name,' ',rm.room_no,' ',bld.campus_short) as location, CONCAT(sbj.subject_code,' ',cs.course_code,' - ',cs.title) as course, "
					+ " if((cd.class_capacity -(select count(*) from registration rgs where cd.id=rgs.course_details_id))>0,'Open','Closed') as status, cs.units as unit"
					+ " FROM course_details cd, course cs, subject sbj, term_info ti, instructor ins, schedule sch, course_schedule csch, room rm, building bld, registration rc"
					+ " WHERE rc.course_details_id=cd.id AND" + " cd.term_id=ti.id AND" + " ti.id=? AND"
					+ " cd.instructor_id=ins.id AND" + " cd.room_id=rm.id AND" + " rm.building_id =bld.id AND"
					+ " cd.course_id=cs.id AND" + " cs.subject_id=sbj.id and" + " csch.schedule_id=sch.id AND"
					+ " csch.course_detail_id=cd.id AND" + " rc.student_id=?" + " GROUP BY csch.course_detail_id";
			System.out.println(sqlQuery);
			PreparedStatement courseList = conn.prepareStatement(sqlQuery);
			// PreparedStatement course = conn.prepareStatement(" select id,
			// name, status_client from test.projects where client=? ");
			courseList.setInt(1, termId);
			courseList.setInt(2, studentId);
			

			ResultSet result = courseList.executeQuery();
			ArrayList Rows = new ArrayList();
				while (result.next()) {
					// System.out.println(dbID + dbName + dbStatusClient);
					ArrayList row = new ArrayList();
					for (int i = 1; i <= 1; i++) {
						row.add(result.getString("id"));
						row.add(result.getString("course"));
						row.add(result.getString("instructor"));
						row.add(result.getString("term"));
						row.add(result.getString("time"));
						row.add(result.getString("location"));
						row.add(result.getString("unit"));
						row.add(result.getString("status"));
					}
					Rows.add(row);
					enrollCourseList = Rows;
				}

		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		return enrollCourseList;
	}
}
