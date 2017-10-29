package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import data.*;
import database.DataOperation;

public class CourseForEnroll {
	private int courseForEnrollId;
	private int courseId;
	private String semester;
	private String room;
	private Date startTime;
	private Date endTime;
	private int courseCode;
	private int day;
	private int enrollLimit;
	private int enrollNumber;
	private String instructor;
	private int credits;
	private String subject;
	private String courseTitle;
	private String courseLevel;
	

	
	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getEnrollLimit() {
		return enrollLimit;
	}

	public void setEnrollLimit(int enrollLimit) {
		this.enrollLimit = enrollLimit;
	}

	public int getEnrollNumber() {
		return enrollNumber;
	}

	public void setEnrollNumber(int enrollNumber) {
		this.enrollNumber = enrollNumber;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	public static CourseForEnroll dataToCourseForEnroll(HashMap<String,Object> data)
	{
		CourseForEnroll nCFE=new CourseForEnroll();
		nCFE.setCourseCode((Integer)data.get(Data.courseCode));
		nCFE.setCourseForEnrollId((Integer)data.get(Data.courseForEnrollId));
		nCFE.setCourseId((Integer)data.get(Data.courseId));
		nCFE.setDay((Integer)data.get(Data.day));
		nCFE.setCredits((Integer)data.get(Data.credits));
		nCFE.setEndTime((Date)data.get(Data.endTime));
		nCFE.setStartTime((Date)data.get(Data.startTime));
		nCFE.setCourseTitle((String)data.get(Data.courseTitle));
		nCFE.setSubject((String)data.get(Data.subject));
		nCFE.setEnrollLimit((Integer)data.get(Data.enrollLimit));
		nCFE.setEnrollNumber((Integer)data.get(Data.enrollNumber));
		nCFE.setRoom((String)data.get(Data.room));
		nCFE.setInstructor((String)data.get(Data.instructor));
		nCFE.setSemester((String)data.get(Data.semester));
		nCFE.setCourseLevel((String)data.get(Data.courseLevel));
		return nCFE;
	}
	
	public static ArrayList<CourseForEnroll> getCourseForEnrollsByCondition(ArrayList<String> findNames,ArrayList<Object> findValues) throws SQLException
	{
		System.out.println("this is minisis");
		ArrayList<String> getColNames=new ArrayList();
		//getColNames.add(Information.courseCode);
		getColNames.add(Data.courseForEnrollId);
		getColNames.add(Data.courseTbl+"."+Data.courseId);
		getColNames.add(Data.day);
		getColNames.add(Data.credits);
		getColNames.add(Data.endTime);
		getColNames.add(Data.startTime);
		getColNames.add(Data.courseTitle);
		getColNames.add(Data.subject);
		getColNames.add(Data.enrollLimit);
		getColNames.add(Data.enrollNumber);
		getColNames.add(Data.room);
		getColNames.add(Data.instructor);
		getColNames.add(Data.semester);
		getColNames.add(Data.courseLevel);
		findNames.add(Data.courseForEnrollTbl+"."+Data.courseId+"="+Data.courseTbl+"."+Data.courseId);
		findValues.add("");
		findNames.add(Data.courseForEnrollTbl+"."+Data.semesterId+"="+Data.semesterTbl+"."+Data.semesterId);
		findValues.add("");
		ArrayList<HashMap<String,Object>> datas=new ArrayList();
		ArrayList<CourseForEnroll> findCourses=new ArrayList();
		datas=DataOperation.select(Data.courseTbl+","+Data.courseForEnrollTbl+","+Data.semesterTbl,getColNames,findNames,findValues);
		for (int i=0;i<datas.size();i++)
		{
			findCourses.add(dataToCourseForEnroll(datas.get(i)));
		}
		return findCourses;
	}
	
	public static ArrayList<CourseForEnroll> getAllCourseForEnroll() throws SQLException
	{
		ArrayList<String> getColNames=new ArrayList();
		ArrayList<String> findNames=new ArrayList();
		ArrayList<Object> findValues=new ArrayList();
		return getCourseForEnrollsByCondition(findNames,findValues);
	}
	
	public static CourseForEnroll getCourseForEnrollById(int courseForEnrollId) throws SQLException
	{
		
		ArrayList<String> getColNames=new ArrayList();
		ArrayList<String> findNames=new ArrayList();
		ArrayList<Object> findValues=new ArrayList();
		//getColNames.add(Information.courseCode);
		getColNames.add(Data.courseForEnrollId);
		getColNames.add(Data.courseTbl+"."+Data.courseId);
		getColNames.add(Data.day);
		getColNames.add(Data.credits);
		getColNames.add(Data.endTime);
		getColNames.add(Data.startTime);
		getColNames.add(Data.courseTitle);
		getColNames.add(Data.subject);
		getColNames.add(Data.enrollLimit);
		getColNames.add(Data.enrollNumber);
		getColNames.add(Data.room);
		getColNames.add(Data.instructor);
		getColNames.add(Data.semester);
		getColNames.add(Data.courseLevel);
		findNames.add(Data.courseForEnrollTbl+"."+Data.courseId+"="+Data.courseTbl+"."+Data.courseId);
		findValues.add("");
		findNames.add(Data.courseForEnrollId);
		findValues.add(courseForEnrollId);
		findNames.add(Data.courseForEnrollTbl+"."+Data.semesterId+"="+Data.semesterTbl+"."+Data.semesterId);
		findValues.add("");
		ArrayList<HashMap<String,Object>> datas=new ArrayList();
		datas=DataOperation.select(Data.courseTbl+","+Data.courseForEnrollTbl+","+Data.semesterTbl,getColNames,findNames,findValues);
		if (datas.size()==0)
		{
			return null;
		}
		else
		{
			return dataToCourseForEnroll(datas.get(0));
		}
	}
	
	public static ArrayList<HashMap<String,Object>> getAllCodes() throws SQLException
	{
		ArrayList<String> getColNames=new ArrayList();
		getColNames.add("distinct "+Data.subject);
		ArrayList<String> findNames=new ArrayList();
		ArrayList<Object> findValues=new ArrayList();
		return DataOperation.select(Data.courseTbl, getColNames, findNames, findValues);
	}
	
	public static int deleteById(int courseForEnrollId) throws SQLException
	{
		ArrayList<String> findNames=new ArrayList();
		ArrayList<Object> findValues=new ArrayList();
		findNames.add(Data.courseForEnrollId);
		findValues.add(courseForEnrollId);
		return DataOperation.delete(Data.courseForEnrollTbl, findNames, findValues);
		
	}
	
	public void setCourseForEnrollId(int courseForEnrollId) {
		this.courseForEnrollId = courseForEnrollId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseForEnrollId() {
		return courseForEnrollId;
	}

	public int getCourseId() {
		return courseId;
	}
	
	public static HashSet<String> daysToSet(int days)
	{
		HashSet<String> wDays=new HashSet<String>();
		while (days!=0)
		{
			int w=days%10;
			wDays.add(Data.dayToWord.get(w));
			days=days/10;
		}
		return wDays;
	}
	
	public static String daysToString(int days)
	{
		StringBuffer str=new StringBuffer();
		
		while (days!=0)
		{
			int w=days%10;
			str.append(Data.dayToWord.get(w)+" ");
			days=days/10;
		}
		return str.toString();
	}
	
	public static boolean haveSameDay(int days1,int days2)
	{
		HashSet<String> wDay1=daysToSet(days1);
		HashSet<String> wDay2=daysToSet(days2);
		Iterator<String> iterator=wDay1.iterator();
		while (iterator.hasNext())
		{
			String d=iterator.next();
			if (wDay2.contains(d))
				return true;
		}
		return false;
		
	}
	
	public static boolean timeConflict(Date s1,Date e1,Date s2,Date e2)
	{
		if (s1.before(e2)&&s2.before(e1))
			return true;
		else 
			return false;
		
	}

	public String getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

}
