package controller;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.Instructor;
import DTO.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import model.DAO;
import model.StudentModel;
import util.Singleton;



public class ProfileController implements Initializable {
	
	
	@FXML
	private Label lblOccupation , lblCareer, lblAddress, lblName;
	@FXML
	private StackPane stackPaneProfile;
	
	@FXML 
	private Hyperlink hLinkChangePassword;
	@FXML
	private PasswordField txtPasswordProfile;
	
	public String userType = Singleton.getInstance().getUserType().getText();
	public String userID = Singleton.getInstance().getUserAcessID().getText();
	StudentModel studentInfo = new StudentModel();
	Student student = new Student();
	Instructor instructor = new Instructor();
	DAO dataAccess = new DAO();
	
	
	@FXML
	public void changePassword(){
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(userType.equals("1")){
			lblOccupation.setText("Student");
			student =studentInfo.selectStudent(Integer.parseInt(userID));
			String userName = student.getFirst_name() +" " + student.getLast_name();
			lblName.setText("Hi! "+userName);
			String careerName = student.getCareer_Name();
			String SubjectName = student.getSubject_Name();
			String address = student.getAddress();
			lblAddress.setText(address);
			if(careerName.equals("U")){
				lblCareer.setText("Under-Grad in" + student.getCareer_Name());
			}else{
				lblCareer.setText("Grad in " + SubjectName);
			}
			
		}else if(userType.equals("2")){
			lblOccupation.setText("Admin");
			lblCareer.setText("Co-ordinator");
			
		}else if(userType.equals("3")){
			lblOccupation.setText("Instructor");
			lblCareer.setText("Proffesor");
		}else{
			lblOccupation.setText("Program Director");
			
		}
		// TODO Auto-generated method stub
		
	}
	
	

}
