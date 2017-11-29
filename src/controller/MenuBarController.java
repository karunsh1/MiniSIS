
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;

import DTO.Users;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import util.PdfTranscript;
import util.Singleton;
import javafx.event.ActionEvent;

public class MenuBarController implements Initializable {

	private final ObjectProperty<Users> user = new SimpleObjectProperty<>();

	public final ObjectProperty<Users> userProperty() {
		return this.user;
	}

	public final Users getUser() {
		return this.userProperty().get();
	}

	public final void setUser(final Users user) {
		this.userProperty().set(user);
	}

	@FXML
	private BorderPane addGradesPane, BPaneMenuBar;
	@FXML
	private StackPane showItemPane;
	@FXML
	private MenuItem addGrades, mViewProfile;
	@FXML
	private MenuItem mItemViewCGPA;
	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem mItemViewTranscript,mItemAddUser,mItemUpdateSequrityQue;
	@FXML
	MenuItem menuitemPay;
	@FXML
	MenuItem MenuItemDropCourse;
	@FXML
	private MenuItem menuitemSearchCourse, mItemLogout, mItemViewGrade;
	@FXML
	private Menu mResult, mCourseDetail;
	
	
	@FXML
	private void onUpdateSeqQue(){
		
		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;		

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/AddSecurityQuestion.fxml"));
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		showItemPane.getChildren().add(newLoadedPane);
		
		
		
	}


	@FXML MenuItem mViewSchedule;
	@FXML Menu mMenuPayFee;

	@FXML
	private void onLogout() {

		user.set(null);
		Singleton.getInstance().setUserAcessID(null);
		Singleton.getInstance().setEmailID(null);
		Singleton.getInstance().setUserType(null);
		showItemPane.getChildren().clear();

	}
	@FXML
	private void clickOnAddUser(){
		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;		

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/AddUser.fxml"));
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		showItemPane.getChildren().add(newLoadedPane);
		
		
	}

	@FXML
	private void onSearchCourse() {

		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;
		try {

			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/SearchCourse2.fxml"));

		} catch (IOException e) {
			System.out.println("in catch");
			e.printStackTrace();
		}
		showItemPane.getChildren().add(newLoadedPane);
	}

	@FXML
	private void clickOnViewTranscript() {
		showItemPane.getChildren().clear();
<<<<<<< HEAD
		BorderPane newLoadedPane = null;		
=======
		BorderPane newLoadedPane = null;
		// if (!mItemViewCGPA.isDisable()) {


>>>>>>> 2bbd6001c6c30cf96be56568ddaa199c6427a32f

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/ViewTranscript.fxml"));
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		showItemPane.getChildren().add(newLoadedPane);

	}

	@FXML
	private void ClickOnViewCGPA() {

		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;
		// if (!mItemViewCGPA.isDisable()) {

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/ViewGradeDashBoard.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showItemPane.getChildren().add(newLoadedPane);
		// mItemViewCGPA.disableProperty().set(true);

		// }

	}

	@FXML
	private void ClickOnViewGrade() {
		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/ShowGrades.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showItemPane.getChildren().add(newLoadedPane);

	}

	@FXML
	private void clickOnAddGrades() {

		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;
		// if (!addGrades.isDisable()) {

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/GradesDashboard.fxml"));
			showItemPane.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// addGrades.disableProperty().set(true);

		// }

	}

	@FXML
	private void onViewProfile() {
		showItemPane.getChildren().clear();
		StackPane newLoadedPane = null;
		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/ProfileDashboard.fxml"));
			showItemPane.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void onDropCourse(ActionEvent event) {

		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;
		// if (!addGrades.isDisable()) {

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/DropCourse.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showItemPane.getChildren().add(newLoadedPane);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mMenuPayFee.visibleProperty().set(true);
		mViewSchedule.visibleProperty().set(true);
		String userType = null;

		userType = Singleton.getInstance().getUserType().getText();

		if (!userType.equals("1")) {
			mMenuPayFee.visibleProperty().set(false);
			mViewSchedule.visibleProperty().set(false);
		}

		System.out.println("User Type  " + userType);

		if (userType.equals("3")) {
			addGrades.visibleProperty().set(true);
			mCourseDetail.visibleProperty().set(false);
			mResult.visibleProperty().set(false);
			
		}else if (userType.equals("2")){
			mItemAddUser.visibleProperty().set(true);
		}

	}

	@FXML
	public void OnClickPay(ActionEvent event) {
		showItemPane.getChildren().clear();
		AnchorPane newLoadedPane = null;
		// if (!addGrades.isDisable()) {

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/PayFees.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showItemPane.getChildren().add(newLoadedPane);
	}

	@FXML public void onViewSchedule(ActionEvent event) {
		showItemPane.getChildren().clear();
		BorderPane newLoadedPane = null;

		try {
			newLoadedPane = FXMLLoader.load(getClass().getResource("/view/ViewSchedule.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showItemPane.getChildren().add(newLoadedPane);
	}

}
