package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import model.DAO;
import util.Singleton;

public class AddGradesController implements Initializable {
	
	
	@FXML
    private ComboBox cbSelectTerm;
	@FXML
    private BorderPane addGrades;
	@FXML
    private ComboBox cbDeptName;
	@FXML
    private ComboBox cbCourseName;
	@FXML
    private ComboBox cbCareerName;
	@FXML
    private ComboBox cbSelectTermUpload;
	@FXML
    private ComboBox cbDeptNameUpload;
	@FXML
    private ComboBox cbCourseNameUpload;
	@FXML
    private ComboBox cbCareerNameUpload;	
	@FXML
    private Button btnDownloadTemplate;
	@FXML
    private Button btnUploadTemplate;
	@FXML
    private Label lblFileName;
	@FXML
	private Button btnFileChooser;
	private Window stage;
	
	
	
	
	@FXML
	private void onSelectTerm(){


		
	}
	@FXML
	private void onSelectDept(){		
	}
	@FXML
	private void onSelectCourseName(){			
	}
	@FXML
	private void onSelectDeptName(){		
	}
	@FXML
	private void onSelectCareer(){		
	}
	@FXML
	private void downnloadCSV(){	
		
		String termID = "1";
		String instructorID = Singleton.getInstance().getUserAcessID().getText(); 
		String userHomeFolder = System.getProperty("user.home");
		DAO dataAccess = new DAO();
		dataAccess.exportCSVStudent_Inst_uploadGrades(userHomeFolder, instructorID, termID);
		
		
	}
	// For upload CSV 
	@FXML
	private void onSelectTermUpload(){	
		String selectedTermid = cbSelectTerm.getSelectionModel().getSelectedItem().toString();
		System.out.println("selected item "+ selectedTermid);
	}
	@FXML
	private void onSelectDeptUpload(){		
	}
	@FXML
	private void onSelectCourseNameUpload(){			
	}
	@FXML
	private void onSelectDeptNameUpload(){		
	}
	@FXML
	private void onSelectCareerUpload(){		
	}
	@FXML
	private void clickOnFileChooser(){
		String fileName = "Student_Result.csv";
		String selectedPath = null;
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Grade Template");
		File file = fileChooser.showOpenDialog(stage);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));		
		//fileChooser.getExtensionFilters().addAll(new ExtensionFilter("file","*.csv"));
		
		
		 if (file != null ) {
			 selectedPath  = file.getPath();
			 lblFileName.setText(selectedPath);
			// lblFileName.textProperty().setValue(selectedPath);
			 System.out.println("file path" + selectedPath +"   File Name   " + file.getName() +"label value " + lblFileName);
         }		
		
	
				
		
	}
	@FXML
	private void UploadCSV(){	
		String  filepath = null;
		filepath = lblFileName.getText();
		DAO dataAcess = new DAO();
		try {
			dataAcess.importCSVGPA_Instrutor(filepath);
		} catch (FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String instructorID = Singleton.getInstance().getUserAcessID().getText(); 
		DAO dataAccess = new DAO();
		ArrayList termListArray = dataAccess.termNames(instructorID);
		ObservableList termlist = FXCollections.observableArrayList(termListArray);
		cbSelectTerm.setItems(termlist);
		
		
		
		ArrayList departmentNames = dataAccess.Instructor_Dept(Integer.parseInt(instructorID));		
		ArrayList courseNames = dataAccess.Instructor_Courses(Integer.parseInt(instructorID));
		ArrayList careerNames = dataAccess.careerList_ins(Integer.parseInt(instructorID));
		
		
		
		
		
		ObservableList DepartmentNamelist = FXCollections.observableArrayList(departmentNames);
		ObservableList courseNamelist = FXCollections.observableArrayList(courseNames);
		ObservableList careerNameList = FXCollections.observableArrayList(careerNames);
		
		
		
		
		cbDeptName.setItems(DepartmentNamelist);
		cbCourseName.setItems(courseNamelist);
		cbCareerName.setItems(careerNameList);
		
		
	}
	
    
	
	
	

}
