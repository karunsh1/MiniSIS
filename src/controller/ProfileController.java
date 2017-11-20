package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DTO.Admin;
import DTO.Instructor;
import DTO.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AdminModel;
import model.DAO;
import model.StudentModel;
import util.Singleton;



public class ProfileController implements Initializable {
	
	
	@FXML
	private Label lblOccupation , lblCareer, lblAddress, lblName;
	@FXML
	private StackPane stackPaneProfile =null;
	
	@FXML 
	private Hyperlink hLinkChangePassword;
	@FXML
	private PasswordField txtPasswordProfile;
	
	
	public String userType = Singleton.getInstance().getUserType().getText();
	public String userID = Singleton.getInstance().getUserAcessID().getText();
	public String userMailID = Singleton.getInstance().getEmailID();
	StudentModel studentInfo = new StudentModel();
	Student student = new Student();
	Instructor instructor = new Instructor();
	DAO dataAccess = new DAO();
	Admin admin = new Admin();
	AdminModel adminModel = new AdminModel();
	
	
	@FXML
	public void changePassword(){
		System.out.println("print hyperlink");
		
		BorderPane root1;
		try {
			root1 = FXMLLoader.load(getClass().getResource("/view/ChangePasswordDashboard.fxml"));
			Scene scene = new Scene(root1);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Stage popupLayout = new Stage();			
			popupLayout.initModality(Modality.APPLICATION_MODAL);
			popupLayout.initStyle(StageStyle.UNDECORATED);			
			popupLayout.setScene(scene);
			popupLayout.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		       
		}
		
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(userType.equals("1")){
			lblOccupation.setText("Student");
			student =dataAccess.studentProfile(Integer.parseInt(userID));
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
			admin = adminModel.selectAdmin(userMailID);
			lblName.setText("Hi! "+ admin.getFirst_name()+" "+admin.getLast_name());
			lblAddress.setText(admin.getAddress());
			
			
		}else if(userType.equals("3")){
			
			lblOccupation.setText("Instructor");
			lblCareer.setText("Proffesor");
			instructor=dataAccess.selectInstructor(userMailID);
			lblName.setText("Hi! "+instructor.getFirst_name()+" "+instructor.getLast_name());
			lblAddress.setText(instructor.getAddress());
			
		}else{
			lblOccupation.setText("Program Director");
			
		}
		// TODO Auto-generated method stub
		
	}
	
	

}
