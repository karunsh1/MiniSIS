package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import data.*;
import database.DataOperation;

public class Semester {
	public static ArrayList<HashMap<String,Object>> getAllSemester() throws SQLException
	{
		ArrayList<Object> semesters=new ArrayList();
		ArrayList<String> getColNames=new ArrayList();
		getColNames.add("*");
		ArrayList<String> findNames=new ArrayList();
		ArrayList<Object> findValues=new ArrayList();
		return DataOperation.select(Data.semesterTbl, getColNames,findNames,findValues);
	}
}
