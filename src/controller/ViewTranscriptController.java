package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ViewTranscriptController extends StackPane implements Initializable  {
	
	@FXML
	private TextField txtSearchStudentID;
	@FXML
	private Button btnViewTranscript;	
	@FXML
	private HBox hBoxSearchStudent;
	@FXML
	private ScrollPane ScrlPaneVewTranscript;	
	@FXML
	private void ClickOnBtnViewTranscript(){
		
		
	}
	/*public ViewTranscriptController(){
		
		
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TranscriptComponent.fxml"));
            //loader.setController(this);
            loader.setRoot(this);
            try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
		
	}
	*/
	
	public void initialize(URL location, ResourceBundle resources){
		
	}

}
