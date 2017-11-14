package controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import DTO.Student;
import DTO.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.DAO;
import model.StudentModel;
import model.ViewCGPAModel;
import sun.launcher.resources.launcher;
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
	@FXML
	private Label lblError;

	private ViewCGPAModel getCGPAModel = new ViewCGPAModel();
	DAO accessData = new DAO();
	StudentModel studentModel = new StudentModel();
	Student student = new Student();

	
	public String userType = Singleton.getInstance().getUserType().getText();;
	public String studentID = Singleton.getInstance().getUserAcessID().getText();

	@FXML
	private void ClickOnBtnSearchCGPADetail() {

		int studentIDSearch = 0;
		lblError.setText(null);

		studentIDSearch = Integer.parseInt(txtSearchStudentID.getText());
		System.out.println("studentIDSearch" + studentIDSearch);
		System.out.println("validity status    " + accessData.validateStudentForCGPA(studentIDSearch));
		if (accessData.validateStudentForCGPA(studentIDSearch)) {

			hBoxCGPADetail.visibleProperty().set(true);
			Float cGPA = getCGPAModel.getCGPA(studentIDSearch);
			
			student = studentModel.selectStudent(studentIDSearch);
			lblStudentName.setText(student.getFirst_name() + " " + student.getLast_name());
			lblStudentCareer.setText(student.getCareer_Name());
			lblStudentProgram.setText(student.getSubject_Name());
			BigDecimal bdCGPA = new BigDecimal(Float.toString(cGPA));
			bdCGPA = bdCGPA.setScale(1, BigDecimal.ROUND_HALF_UP);
			System.out.println("Control value CGPA " + bdCGPA.floatValue());
			lblStudentCGP.setText(bdCGPA.toString());
			// txtSearchStudentID.setText(null);
		} else {
			hBoxCGPADetail.visibleProperty().set(false);
			lblError.setText("Sorry!, No record is avaiable for " + studentIDSearch + " .");
			// txtSearchStudentID.setText(null);
			// clearCGPADetail();

		}

	}

	public void initialize(URL location, ResourceBundle resources) {
		hBoxCGPADetail.visibleProperty().set(false);
		System.out.println("stduent id " + userType + " email" + studentID);

		if (userType.equals("1")) {
			hBoxSearchStudent.visibleProperty().set(false);

			if (accessData.validateStudentForCGPA(Integer.parseInt(studentID))) {
				hBoxCGPADetail.visibleProperty().set(true);
				StudentModel studentModel = new StudentModel();
				Student student = new Student();
				student = studentModel.selectStudent(Integer.parseInt(studentID));
				System.out.println("name  "+ student.getFirst_name());
				
				lblStudentName.setText(student.getFirst_name() + " " + student.getLast_name());
				lblStudentCareer.setText(student.getCareer_Name());
				lblStudentProgram.setText(student.getSubject_Name());
				Float cGPA = getCGPAModel.getCGPA(Integer.parseInt(studentID));
				BigDecimal bdCGPA = new BigDecimal(Float.toString(cGPA));
				bdCGPA = bdCGPA.setScale(1, BigDecimal.ROUND_HALF_UP);
				System.out.println("Control value CGPA " + bdCGPA.floatValue());
				lblStudentCGP.setText(bdCGPA.toString());

			} else {
				hBoxCGPADetail.visibleProperty().set(false);
				lblError.setText("Sorry!,No record is avaiable!");

			}

		} else {
			clearCGPADetail();
		}
		txtSearchStudentID.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*") || newValue.equals(null)) {
					txtSearchStudentID.setText(newValue.replaceAll("[^\\d]", ""));
				}

			}

		});

	}

	private void clearCGPADetail() {
		lblStudentCareer.setText(null);
		lblStudentCGP.setText(null);
		lblStudentName.setAccessibleHelp(null);
		lblStudentProgram.setText(null);

	}

}
