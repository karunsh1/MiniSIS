package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.DAO;
import util.Singleton;

public class ViewTranscriptController extends StackPane implements Initializable  {
	
	@FXML
	private TextField txtSearchStudentID;
	@FXML
	private Button btnViewTranscript;	
	@FXML
	private HBox hBoxSearchStudent, hBoxDownload,hBoxView;
	@FXML
	private Label lblErrorTranscript;
	@FXML 
	private Hyperlink hLinkDownloadTranscript, hLinkViewTranscript;
	
	public String studentID = Singleton.getInstance().getUserAcessID().getText();
	public String userType = Singleton.getInstance().getUserType().getText();
	public DAO dataAccess = new DAO();	
	public int studentIDSearch = 0;
	
	@FXML
	private void ClickOnBtnSearchStudent(){
		
		lblErrorTranscript.setText(null);
		studentIDSearch = Integer.parseInt(txtSearchStudentID.getText());
		
	}
	@FXML
	private void clickOnDownLoadTranscript(){
		
		
		
		
	}
	@FXML
	private void clickOnViewTranscript(){
		
		
		
	}
	
	
	
	public void initialize(URL location, ResourceBundle resources){
		
	   if(userType.equals("1")){
		   if (dataAccess.validateStudentForCGPA(Integer.parseInt(studentID))) {
			   hBoxDownload.visibleProperty().set(true);
			   hBoxView.visibleProperty().set(true);
			   hBoxSearchStudent.visibleProperty().set(false);	
			  
			   
		   } else {
			   lblErrorTranscript.setText("Sorry!,No record is avaiable!");
		   }
		   
	   }else{
		   txtSearchStudentID.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*") || newValue.equals(null)) {
						txtSearchStudentID.setText(newValue.replaceAll("[^\\d]", ""));
					}

				}

			});
	   }
	   
		
	}

}
