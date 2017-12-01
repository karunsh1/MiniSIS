package model;

import java.sql.Connection;
import java.sql.SQLException;
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

	public Float getCGPA(int studentID) {

		Float studentCGPA = 0f;

		DAO accessData = new DAO();
		ArrayList<Float> listGPA = new ArrayList<Float>();
		listGPA = accessData.getStudentGPA(studentID);
		
		int i = 0;
		Iterator<Float> itratorGPA = listGPA.iterator();
		System.out.println("total gpa single  " + listGPA);

		while (itratorGPA.hasNext()) {
			studentCGPA = studentCGPA + itratorGPA.next();
			//System.out.println("total gpa single  " + itratorGPA.next());

		}

		studentCGPA = studentCGPA / listGPA.size();

		System.out.println("total gpa" + listGPA.size());

		return studentCGPA;

	}
	public Float getCGPAPreRequisite(int studentID) {

		Float studentCGPA = 0f;

		DAO accessData = new DAO();
		ArrayList<Float> listGPA = new ArrayList<Float>();
		listGPA = accessData.getStudentGPAofPrerequisite(studentID);
		
		int i = 0;
		Iterator<Float> itratorGPA = listGPA.iterator();
		System.out.println("total gpa single  " + listGPA);

		while (itratorGPA.hasNext()) {
			studentCGPA = studentCGPA + itratorGPA.next();
			//System.out.println("total gpa single  " + itratorGPA.next());

		}

		studentCGPA = studentCGPA / listGPA.size();

		System.out.println("total gpa" + listGPA.size());

		return studentCGPA;

	}

}
