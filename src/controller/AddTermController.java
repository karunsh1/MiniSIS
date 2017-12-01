package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.DAO;

public class AddTermController implements Initializable {

	@FXML
	private DatePicker dateTermEnd, dateTermStart, dateRegStart;
	@FXML
	private ComboBox<String> cbSelectYear, cbSelectSession;
	@FXML
	private BorderPane bPaneAddTerm;
	@FXML
	private Button btnSave, btnReset;
	@FXML
	private Label lblError;
	public LocalDate termStartDate = null, termEndDate = null, regStartDate = null, regEndDate = null;
	public String term = null, termYear = null, termSession = null;
	public DAO dataAccess = new DAO();

	@FXML
	private void onSelectYear() {
		termYear = cbSelectYear.getSelectionModel().getSelectedItem();

	}

	@FXML
	private void onSelectSession() {
		termSession = cbSelectSession.getSelectionModel().getSelectedItem();

	}

	@FXML
	private void clickOnSaveButton() {
		if (!(termSession.equals(null) && termYear.equals(null))) {
			term = termSession + " " + termYear;
			if (!(termStartDate.equals(null) && termEndDate.equals(null) && regStartDate.equals(null))) {
				boolean addTermStatus = dataAccess.addtermDetail(term, termStartDate, termEndDate, regStartDate);
				if (addTermStatus) {
					reset();
					lblError.setText("Term has been added successfully!");
				} else {
					lblError.setText("This term allready exists!");
				}
			} else {
				lblError.setText("Please select dates!");
			}

		} else {
			lblError.setText("Please select year and Session in term field !");
		}

	}

	@FXML
	private void clickOnResetButton() {
		reset();

	}

	@FXML
	private void SelectTermStartDate() {
		termStartDate = dateTermStart.getValue();
		System.out.println("termstartdate  " + termStartDate);
	}

	@FXML
	private void selectTermEndDate() {
		termEndDate = dateTermEnd.getValue();
		/*
		 * if(!termEndDate.isAfter(termStartDate)){
		 * btnSave.disableProperty().set(false);
		 * lblError.setText("term End date should be greater than term end Date"
		 * ); }else{ btnSave.disableProperty().set(true); lblError.setText("");
		 * }
		 */
	}

	@FXML
	private void selectRegStartDate() {
		regStartDate = dateRegStart.getValue();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> obYearList = FXCollections.observableArrayList();
		obYearList.add("2018");
		obYearList.add("2017");
		obYearList.add("2016");
		obYearList.add("2015");
		ObservableList<String> obSessionList = FXCollections.observableArrayList();
		obSessionList.add("Winter");
		obSessionList.add("Fall");
		obSessionList.add("Summer");
		cbSelectYear.setItems(obYearList);
		cbSelectSession.setItems(obSessionList);
	}

	private void reset() {

		dateRegStart.getEditor().clear();
		dateTermStart.getEditor().clear();
		dateTermEnd.getEditor().clear();
		cbSelectSession.getSelectionModel().clearSelection();
		cbSelectYear.getSelectionModel().clearSelection();
		lblError.setText("");
	}

}
