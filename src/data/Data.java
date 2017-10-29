package data;

import java.util.HashMap;

public class Data {
	public static int maxEnrollCourseNumberInOneSemester=4;
	public static int maxCredits=45;
	public static HashMap<String,String> subjectMajor=new HashMap();
	public static HashMap<Integer,String> dayToWord=new HashMap();
	public static HashMap<String,Double> gradeToPoint=new HashMap();
	public static HashMap<String,Integer> semesterOrder=new HashMap();
	public static String studentId="student_id";
	public static String firstName="first_name";
	public static String lastName="last_name";
	public static String birthday="birthdate";
	public static String email="email";
	public static String phoneNo="phone_no";
	public static String gender="gender";
	public static String major="major";
	public static String studentPass="user_pass";
	public static String adminId="admin_login";
	public static String adminPass="admin_pass";
	public static String studentTbl="student";
	public static String adminTbl="admin";
	public static String semesterTbl="semester";
	public static String semesterId="semester_id";
	public static String semesterCode="semester_code";
	public static String subject="course_code";
	public static String courseCode="course_id";//*
	public static String courseId="course_id";//*
	public static String courseTitle="course_title";
	public static String semester="semester_code";
	public static String credits="course_credits";
	public static String instructor="instructor";
	public static String room="room";
	public static String courseForEnrollId="schedule_id";
	public static String day="days";
	public static String enrollLimit="max_enroll";
	public static String enrollNumber="current_enroll";
	public static String endTime="end_time";
	public static String startTime="start_time";
	public static String courseTbl="course";
	public static String courseForEnrollTbl="schedule";
	public static String enrollId="enrollment_id";
	public static String grade="grade";
	public static String enroll_tbl="enrollment";
	public static String ongoing="ongoing";
	public static String degree="degree";
	public static String courseLevel="course_level";
	public static String[] gradeList={"A+","A","A-","B+","B","B-","C","F"};
	static{
		subjectMajor.put("comp", "computer science");
		subjectMajor.put("soen", "software engineering");
		subjectMajor.put("inse","information systems engineering");
		dayToWord.put(1,"Mon");
		dayToWord.put(2, "Tue");
		dayToWord.put(3, "Wed");
		dayToWord.put(4, "Thu");
		dayToWord.put(5, "Fri");
		gradeToPoint.put("A+", 4.3);
		gradeToPoint.put("A", 4.0);
		gradeToPoint.put("A-", 3.7);
		gradeToPoint.put("B+", 3.3);
		gradeToPoint.put("B", 3.0);
		gradeToPoint.put("B-", 2.7);
		gradeToPoint.put("C", 2.0);
		gradeToPoint.put("F", 1.0);
		semesterOrder.put("winter",1);
		semesterOrder.put("summer", 2);
		semesterOrder.put("fall", 3);
	}
	
}
