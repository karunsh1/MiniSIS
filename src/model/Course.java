package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import data.Data;
import database.DataOperation;

public class Course {
	private int courseId;
	private String courseCode;
	private int credts;
	private String courseTitle;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public int getCredts() {
		return credts;
	}
	public void setCredts(int credts) {
		this.credts = credts;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	public static Course dataToCourse(HashMap<String,Object> data)
	{
		Course c=new Course();
		c.setCourseCode((String)data.get(Data.subject));
		c.setCourseId((Integer)data.get(Data.courseId));
		c.setCourseTitle((String)data.get(Data.courseTitle));
		c.setCredts((Integer)data.get(Data.credits));
		return c;
		
	}
	
	public static ArrayList<Course> getAllCourse() throws SQLException
	{
		ArrayList<String> getColNames=new ArrayList();
		getColNames.add("*");
		ArrayList<String> findNames=new ArrayList();
		ArrayList<Object> findValues=new ArrayList();
		ArrayList<HashMap<String,Object>> res=DataOperation.select(Data.courseTbl, getColNames, findNames, findValues);
		ArrayList<Course>courses=new ArrayList();
		for (int i=0;i<res.size();i++)
		{
			courses.add(dataToCourse(res.get(i)));
		}
		return courses;
		
	}
	
	

}
