package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DTO.Course;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import model.CourseModel;
import model.DAO;
import util.Singleton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;

public class PaymentHistoryController implements Initializable {
	String userType;
	int studentID;
	public DAO dataAccess = new DAO();
	@FXML Button ViewPaymentHistory;

	@FXML TableView paymentHistoryTable;

	@FXML TableColumn ColumnSemester;

	@FXML TableColumn ColumnDuePayment;
	@FXML Label labelPaymentHistory;
	@FXML AnchorPane paymentHistoryAnchorPane;
	@FXML BorderPane paymentHistoryBorderPane;
	@FXML Button mPayFee;
     
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

			 studentID = Integer.parseInt(Singleton.getInstance().getUserAcessID().getText());
			System.out.println("student_id"+studentID);

			  
//				if((!dataAccess.ViewSchedule(Integer.parseInt(studentID)).isEmpty())) {
//				viewScheduleTableView.setItems(dataAccess.ViewSchedule(Integer.parseInt(studentID)));
//		

			
	}





	public void clear() {
//		TermCombobox.getSelectionModel().clearSelection();
//		searchCourseTableView.getItems().clear();
	}



	@FXML public void onViewPaymentHistory(ActionEvent event) {
		 // paymentHistoryTable.setItems(null);
	        ArrayList data = new ArrayList();
	        ColumnSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
	        ColumnDuePayment.setCellValueFactory(new PropertyValueFactory<>("paymentDue"));
	        paymentHistoryTable.setItems(dataAccess.ViewPaymentHistory(studentID));
	}





	@FXML public void onPayFee(ActionEvent event) {
		paymentHistoryBorderPane.getChildren().clear();
		AnchorPane newLoadedPane = null;
		// if (!addGrades.isDisable()) {

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/PayFees.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		paymentHistoryBorderPane.getChildren().add(newLoadedPane);
	}
	
	}