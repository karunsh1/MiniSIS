package controller;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.Student;
import DTO.Users;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.StudentModel;
import model.ViewCGPAModel;
import util.Singleton;

public class ViewCGPAController implements Initializable {
	
	@FXML
	private TextField txtSearchStudentID;
	@FXML
	private Button btnSearchViewCGPA;
	@FXML
	private HBox hBoxCGPADetail;
	@FXML
	private HBox hBoxSearchStudent;
	@FXML
	private Label lblStudentName;
	@FXML
	private Label lblStudentCareer;
	@FXML
	private Label lblStudentProgram;
	@FXML
	private Label lblStudentCGP;
	


	private StudentModel studentModel;
	private Student student;
	
	
	@FXML
	private void ClickOnBtnSearchCGPADetail(){
		hBoxCGPADetail.visibleProperty().set(true);
		String userEmailID = null;
		String userType = null;
		String studentID = null;
		
		/*studentID = Singleton.getInstance().getUserDataAccessID().getText();*/
		userType = Singleton.getInstance().getUserType().getText();
		studentID = Singleton.getInstance().getUserAcessID().getText();
		
		System.out.println("stduent id"+ userType  +"      email"+ studentID );
		
		/*ViewCGPAModel viewCGPA = new ViewCGPAModel();
		viewCGPA.getCGPA(studentID);*/
		
		
		
		
		
	}
	
	
	public void initialize(URL location, ResourceBundle resources){
		
	}

}
