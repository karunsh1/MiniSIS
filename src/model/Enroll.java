package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import data.*;
import database.DataOperation;

public class Enroll {
	
	private int enrollId;
	private int courseForEnrollId;
	private String grade;
	private String studentId;
	private boolean ongoing;
	public static double getPoint(String grade)
	{
		if(grade==null)
			return 0;
		return Data.gradeToPoint.get(grade);
	}
	public static ArrayList<Enroll> getEnroll(ArrayList<String> findNames,ArrayList<Object> findValues) throws SQLException
	{
		ArrayList<String> getColNames=new ArrayList();
		getColNames.add(Data.enrollId);
		getColNames.add(Data.studentId);
		getColNames.add(Data.grade);
		getColNames.add(Data.courseForEnrollId);
		getColNames.add(Data.ongoing);
		ArrayList<HashMap<String,Object>> datas=new ArrayList();
		ArrayList<Enroll> findEnrolls=new ArrayList();
		datas=DataOperation.select(Data.enroll_tbl,getColNames,findNames,findValues);
		for (int i=0;i<datas.size();i++)
		{
			findEnrolls.add(dataToEnroll(datas.get(i)));
		}
		return findEnrolls;
	}
	
	public static ArrayList<HashMap<String,Object>> getCourseInfroByStudentId(String studentId) throws SQLException
	{
		ArrayList<String> getColNames=new ArrayList();
		ArrayList<String> findNames=new ArrayList();
		ArrayList<Object> findValues=new ArrayList();
		findNames.add(Data.studentId);
		findValues.add(studentId);
		findNames.add(Data.courseForEnrollTbl+"."+Data.courseForEnrollId+"="+
				Data.enroll_tbl+"."+Data.courseForEnrollId+" and "+
				Data.courseForEnrollTbl+"."+Data.semesterId+"="+
				Data.semesterTbl+"."+Data.semesterId+" and "+
				Data.courseForEnrollTbl+"."+Data.courseId+"="+
				Data.courseTbl+"."+Data.courseId);
		findValues.add("");
		getColNames.add(Data.grade);
		getColNames.add(Data.credits);
		getColNames.add(Data.semesterCode);
		getColNames.add(Data.courseTitle);
		getColNames.add(Data.courseTbl+"."+Data.courseId);
		getColNames.add(Data.subject);
		getColNames.add(Data.courseForEnrollTbl+"."+Data.courseForEnrollId);
		return DataOperation.select(Data.courseTbl+","+Data.enroll_tbl+","+Data.semesterTbl+","+Data.courseForEnrollTbl, getColNames, findNames, findValues);
	}
	
	public static HashMap<String,Transcript> getTranscripts(String studentId) throws SQLException
	{
		ArrayList<String> getColNames=new ArrayList();
		ArrayList<String> findNames=new ArrayList();
		ArrayList<Object> findValues=new ArrayList();
		findNames.add(Data.studentId);
		findNames.add(Data.courseForEnrollTbl+"."+Data.courseForEnrollId+"="+
				Data.enroll_tbl+"."+Data.courseForEnrollId+" and "+
				Data.courseForEnrollTbl+"."+Data.semesterId+"="+
				Data.semesterTbl+"."+Data.semesterId+" and "+
				Data.courseForEnrollTbl+"."+Data.courseId+"="+
				Data.courseTbl+"."+Data.courseId);
		findValues.add(studentId);
		findValues.add("");
		getColNames.add(Data.grade);
		getColNames.add(Data.credits);
		getColNames.add(Data.semesterCode);
		ArrayList<HashMap<String,Object>> results=DataOperation.select(Data.courseTbl+","+Data.enroll_tbl+","+Data.semesterTbl+","+Data.courseForEnrollTbl, getColNames, findNames, findValues);
		HashMap<String,Transcript> transcripts=new HashMap();
		transcripts.put("cgpa", new Transcript());
		System.out.println(transcripts.size());
		for (int i=0;i<results.size();i++)
		{
			String semName=(String)results.get(i).get(Data.semesterCode);
			if (transcripts.get(semName)==null)
				transcripts.put(semName, new Transcript());
			Transcript transcript=transcripts.get(semName);
			int credits=(Integer)results.get(i).get(Data.credits);
			if (results.get(i).get(Data.grade)!=null)
			{
				double point=getPoint((String)results.get(i).get(Data.grade));
				transcript.setCredits(transcript.getCredits()+credits);
				transcript.setTotalPoint(transcript.getTotalPoint()+point*credits);
				transcript.setGpa(transcript.getTotalPoint()/transcript.getCredits());
				Transcript cTranscript=transcripts.get("cgpa");
				cTranscript.setCredits(cTranscript.getCredits()+credits);
				cTranscript.setTotalPoint(cTranscript.getTotalPoint()+point*credits);
				cTranscript.setGpa(cTranscript.getTotalPoint()/cTranscript.getCredits());
			}
		}
		return transcripts;
	}
	
	public static Enroll dataToEnroll(HashMap<String,Object> data)
	{
		Enroll e=new Enroll();
		e.setEnrollId((Integer)data.get(Data.enrollId));
		e.setCourseForEnrollId((Integer)data.get(Data.courseForEnrollId));
		e.setStudentId((String)data.get(Data.studentId));
		e.setGrade((String)data.get(Data.grade));
		e.setOngoing((Boolean)data.get(Data.ongoing));
		return e;
		
	}
	public int getEnrollId() {
		return enrollId;
	}
	public void setEnrollId(int enrollId) {
		this.enrollId = enrollId;
	}
	public int getCourseForEnrollId() {
		return courseForEnrollId;
	}
	public void setCourseForEnrollId(int courseForEnrollId) {
		this.courseForEnrollId = courseForEnrollId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public boolean isOngoing() {
		return ongoing;
	}

	public void setOngoing(boolean ongoing) {
		this.ongoing = ongoing;
	}

}
