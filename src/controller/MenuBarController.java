package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MenuBarController implements Initializable {

	@FXML
	private StackPane showItemPane;
	@FXML
	private MenuItem addGrades;
	@FXML
	MenuBar menuBar;

	@FXML
	private void clickOnAddGrades() {
		BorderPane newLoadedPane = null;
		if (! addGrades.isDisable()) {

			try {
				newLoadedPane = FXMLLoader.load(getClass().getResource("/view/GradesDashboard.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			showItemPane.getChildren().add(newLoadedPane);
			addGrades.disableProperty().set(true);
			
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
