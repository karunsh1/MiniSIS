package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import data.*;
import database.DataOperation;

public class Student {
	private String studentId;
	private String firstName;
	private String lastName;
	private String gender;
	private String major;
	private String email;
	private String phoneNo;
	private Date birthday;
	private String password;
	private String degree;
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static Student dataToStudent(HashMap<String,Object>data)
	{
		Student student=new Student();
		student.setEmail((String)data.get(Data.email));
		student.setPassword((String)data.get(Data.studentPass));
		student.setPhoneNo((String)data.get(Data.phoneNo));
		student.setMajor((String)data.get(Data.major));
		student.setGender((String)data.get(Data.gender));
		student.setLastName((String)data.get(Data.lastName));
		student.setFirstName((String)data.get(Data.firstName));
		student.setBirthday((Date)data.get(Data.birthday));
		student.setStudentId((String)data.get(Data.studentId));
		student.setDegree((String)data.get(Data.degree));
		return student;
		
	}
	
	public static Student getStudentByStudentId(String studentId) throws SQLException
	{
		ArrayList<String> getColNames=new ArrayList();
		ArrayList<String> findColNames=new ArrayList();
		ArrayList<Object> findValues=new ArrayList();
		getColNames.add(Data.email);
		getColNames.add(Data.studentPass);
		getColNames.add(Data.phoneNo);
		getColNames.add(Data.major);
		getColNames.add(Data.gender);
		getColNames.add(Data.lastName);
		getColNames.add(Data.firstName);
		getColNames.add(Data.birthday);
		getColNames.add(Data.studentId);
		getColNames.add(Data.degree);
		findColNames.add(Data.studentId);
		findValues.add(studentId);
		Student student=new Student();
		ArrayList<HashMap<String,Object>> dataSet=new ArrayList();
		dataSet=DataOperation.select(Data.studentTbl, getColNames, findColNames, findValues);
		student=dataToStudent(dataSet.get(0));
		return student;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}

}
