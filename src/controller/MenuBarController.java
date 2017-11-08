package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.Singleton;

public class MenuBarController implements Initializable {

	@FXML
	private StackPane showItemPane;
	@FXML
	private MenuItem addGrades;
	@FXML
	private MenuItem mItemViewCGPA;
	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem mItemViewTranscript;
	
	@FXML
	private void clickOnViewTranscript(){
		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;
		//if (!mItemViewCGPA.isDisable()) {

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("/view/ViewTranscript.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			showItemPane.getChildren().add(newLoadedPane);
			//mItemViewCGPA.disableProperty().set(true);

		//}
		
	}
	@FXML
	private void ClickOnViewCGPA() {
		
		
		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;
		//if (!mItemViewCGPA.isDisable()) {

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("/view/ViewGradeDashBoard.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			showItemPane.getChildren().add(newLoadedPane);
			//mItemViewCGPA.disableProperty().set(true);

		//}

	}

	@FXML
	private void clickOnAddGrades() {
		
		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;
		//if (!addGrades.isDisable()) {

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("/view/GradesDashboard.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			showItemPane.getChildren().add(newLoadedPane);
			//addGrades.disableProperty().set(true);

		//}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		String userType = null;
		
	   userType = Singleton.getInstance().getUserType().getText();
	    if(!userType.equals("3")){
	    	addGrades.visibleProperty().set(false);
	    }		

	}

}
