package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.scenario.animation.shared.SingleLoopClipEnvelope;

import java.math.BigDecimal;
import java.sql.*;
import DTO.Student;
import DTO.Term;
import controller.LoginController;
import database.MySQLAccess;
import util.Singleton;

public class ViewCGPAModel {

	/*public Float getCGPA(int studentID) {

		Float studentCGPA = 0f;

		DAO accessData = new DAO();
		ArrayList<Float> listGPA = new ArrayList<Float>();
		listGPA = accessData.getStudentGPA(studentID);

		int i = 0;
		Iterator<Float> itratorGPA = listGPA.iterator();
		System.out.println("total gpa single  " + listGPA);

		while (itratorGPA.hasNext()) {
			studentCGPA = studentCGPA + itratorGPA.next();
			// System.out.println("total gpa single " + itratorGPA.next());

		}

		studentCGPA = studentCGPA / listGPA.size();

		System.out.println("total gpa" + listGPA.size());

		return studentCGPA;

	}*/
	public String getCGPA(int studentID) {

		Float studentCGPA = 0f;
		String cgPA="";

		DAO accessData = new DAO();
		ArrayList<String> listGPA = new ArrayList<String>();
		listGPA = accessData.getStudentGPA(studentID);
		
		for(String gpa: listGPA){
			if(!gpa.equals(null)){
				float floatGpa = Float.parseFloat(gpa);
				studentCGPA = floatGpa+studentCGPA;
			}else{
				cgPA = " ";
			}
			
		}

		
       if(listGPA.size()>0){
    	   studentCGPA = studentCGPA / listGPA.size(); 
    	   DecimalFormat df = new DecimalFormat("###.#");
    	   cgPA = String.valueOf(df.format(studentCGPA));
       }
		
		

		System.out.println("total gpa" + listGPA.size());

		return cgPA;

	}

	public String getCGPAPreRequisite(int studentID) {

		Float studentCGPA = 0f;
		String CGPAwithNull = "";

		DAO accessData = new DAO();
		ArrayList<String> listGPA = new ArrayList<String>();
		listGPA = accessData.getStudentGPAofPrerequisite(studentID);
		int count = accessData.studentGPAofPrerequisiteStatus(studentID);
		System.out.println("count        "+count);
		if (count <1) {
			for (String gpa : listGPA) {
				if (gpa.equals(null)) {
					CGPAwithNull = studentCGPA + "-NA";
				} else {
					float studentCGPA1 = Float.parseFloat(gpa);
					studentCGPA = studentCGPA1 + studentCGPA;
				}
			}
			float totalGPA = studentCGPA / listGPA.size();

			if (CGPAwithNull.contains("NA")) {
				CGPAwithNull = "Prequisite Course is Pending";
			} else {
				DecimalFormat df = new DecimalFormat("###.#");
				CGPAwithNull = String.valueOf(df.format(totalGPA));

			}
		}else{
			CGPAwithNull = "Prequisite Course is Pending";
		}

		return CGPAwithNull;

	}

}
