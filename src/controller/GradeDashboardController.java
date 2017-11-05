package controller;

import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.MySQLAccess;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class GradeDashboardController implements Initializable {
	
	
	@FXML
    private ComboBox cbSelectTerm;
	@FXML
    private ComboBox cbDeptName;
	@FXML
    private ComboBox cbCourseName;
	@FXML
    private ComboBox cbCareerName;
	
	@FXML
    private Button btnDownloadTemplate;
	
	
	
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
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MySQLAccess obj = new MySQLAccess();
		Connection conn = null;
		conn = obj.getConnection();
		 
		try {
			PreparedStatement termInfo = conn.prepareStatement("SELECT term FROM minisis.term_info");
			
			
			
		} catch (SQLException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
