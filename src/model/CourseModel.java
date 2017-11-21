package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Course;

/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;*/

import database.*;
//import com.msis.DTO.CourseCart;

public class CourseModel {
	
			// Establish Connection
				MySQLAccess obj = new MySQLAccess();
				Connection conn = obj.getConnection();
				Course course;
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

	public Course EnrollCourseList(int studentID, int termID) {	
		 
		int studentId = studentID;		
		int termId = termID;
		String program;
		String courseTitle;
		int courseId;
		String level;
		int numCredits;
		String term;
		String description;
		String instructor;
		
		System.out.println(studentId);
		System.out.println(termId);
		
		ArrayList enrollCourseList = null;
		String sqlQuery = "";
        String enrolled="enrolled";
		try {

			sqlQuery = "SELECT * ,CONCAT(instructor.first_name,' ',instructor.last_name) as instructor, course.id as course_id from  registration join course_details on  course_details.id=registration.course_details_id join" +
					 " student on student.id= registration.student_id join course on course_details.course_id= course.id join instructor on "+
					"instructor.id=course_details.instructor_id  join term_info on term_info.id= course_details.term_id where status=" + "\""+enrolled +"\""+" and student_id="+ "\""+studentID  + "\""+
					"and term_id=" + "\""+termID  + "\"";
						
			
			
			System.out.println(sqlQuery);
			PreparedStatement courseList = conn.prepareStatement(sqlQuery);
			// PreparedStatement course = conn.prepareStatement(" select id,
			// name, status_client from test.projects where client=? ");
//			courseList.setInt(1, termId);
//			courseList.setInt(2, studentId);
			
	
			ResultSet result = courseList.executeQuery();
			ArrayList Rows = new ArrayList();
				while (result.next()) {

					program=result.getString("program");
					System.out.println(result.getString("program"));
					courseId=Integer.parseInt(result.getString("course_code"));
					System.out.println(result.getString("course_code"));
					courseTitle=result.getString("title");	
					System.out.println(result.getString("title"));
					level=result.getString("level");
					System.out.println(result.getString("level"));
					instructor=	result.getString("instructor");
					System.out.println(result.getString("instructor"));
					term=result.getString("term");
					System.out.println(result.getString("term"));
					numCredits=Integer.parseInt(result.getString("units"));
					System.out.println(result.getString("units"));
					description=result.getString("description");
					System.out.println(result.getString("description"));
					course=new Course(program,courseTitle,courseId,level,numCredits,term,description,instructor);
					
					//}
					//Rows.add(row);
					//enrollCourseList = Rows;
				}

		} catch (Exception e) {
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
		return course;
	}
}
