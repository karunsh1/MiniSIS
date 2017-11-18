package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DTO.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.StudentModel;
import util.Singleton;

public class TableViewController implements Initializable {
	
		@FXML
		TableView<Student> tableViewID;
		@FXML
		TableColumn<Student, Integer> userID;
		@FXML 
		TableColumn<Student, String> emailID;
		
		private int userid = 1;
		
		String studentID = Singleton.getInstance().getUserAcessID().getText();
		StudentModel studentModel = new StudentModel();
		Student student = new Student();
		
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
			student = studentModel.selectStudent(Integer.parseInt(studentID));
			
			
			System.out.println("Student ID "+ studentID +" name  "+ student.getLast_name() );
			
			ArrayList data = new ArrayList();
			data.add(student.getFirst_name());
			data.add(student.getLast_name());
			System.out.println(student.getId() +"   email  "+student.getLast_name());
			
			userID.setCellValueFactory(new PropertyValueFactory<>("first_name"));
			emailID.setCellValueFactory(new PropertyValueFactory<>("first_name"));
			
			
			//ObservableList<Student> dataOB = FXCollections.observableArrayList(data);
			ObservableList<Student> dataob = FXCollections.observableArrayList();	        
	        dataob.add(new Student(student.getFirst_name(),student.getFirst_name()));
			tableViewID.setItems(dataob);
			System.out.println(data +"   obdata   "+  dataob);
			
			
			
		}
		
		
		
		
		

}
