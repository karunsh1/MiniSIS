package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DTO.Admin;
import DTO.Instructor;
import DTO.Student;
import database.MySQLAccess;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.AdminModel;
import model.DAO;
import model.StudentModel;
import util.Auth;
import util.Singleton;
import DTO.Users;

public class LoginController {
	public Boolean UserAuthStatus = true;

	public LoginController() {
	}

	private final ObjectProperty<Users> user = new javafx.beans.property.SimpleObjectProperty<Users>();

	public final ObjectProperty<Users> userProperty() {
		return user;
	}

	// public Observable userProperty() {
	// return user;
	// }
	public final Users getUser() {
		return userProperty().get();
	}

	public final void setUser(Users user) {
		userProperty().set(user);
	}

	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label errorLabel;
	@FXML
	private Label lblUserType;
	@FXML
	private Label lblUserRole;
	@FXML
	private Label lblUserAccessID;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnReset;

	@FXML
	private void ok() throws IOException {

		String userName = userNameField.getText();
		String password = passwordField.getText();
		boolean authStatus = authenticate(userName, password);
		if (authStatus) {
			setUser(new Users(userName, password));

		} else {
			
		}
		clearFields();

	}

	@FXML
	private void cancel() {
		setUser(null);
		clearFields();
		errorLabel.setText("");
	}

	private boolean authenticate(String userName, String password) throws IOException {
		try {
			UserAuthStatus = true;
			MySQLAccess obj = new MySQLAccess();
			Connection conn = null;
			conn = obj.getConnection();

			// SQL Query
			PreparedStatement login = conn.prepareStatement(" select * from users where email=? and password=?");

			login.setString(1, userName);
			login.setString(2, Auth.md5(password));
			System.out.println("Password id: " + Auth.md5(password));

			ResultSet result = login.executeQuery();

			String dbEmail = "null";
			int userId;
			String dbPassword = "null";
			String type = "null";
			String name = "null";
			String userType = "null";
			int userID = 0;
			if (result.next()) {
				dbEmail = result.getString("email");
				dbPassword = result.getString("password");
				type = result.getString("type");

				lblUserType.setText(type);
				
				System.out.println(type);
				System.out.println(userName + password);
				System.out.println(dbEmail + dbPassword);

				if (type.equals("1")) {
					StudentModel studentModel = new StudentModel();
					Student student = studentModel.selectStudent(dbEmail);
					name = student.getFirst_name() + " " + student.getLast_name();
					userType = "student";
					userID = student.getId();

					userId = student.getId();
					// String [] test = getSession();
					// System.out.println("testt "+ test);
					// System.out.println("Role: " + name);

					// session.setAttribute("studentId", userId);
				} else if(type.equals("2")) {
					AdminModel adminModel = new AdminModel();
					Admin admin = adminModel.selectAdmin(dbEmail);
					name = admin.getFirst_name() + " " + admin.getLast_name();

					userType = "admin";
					userId = admin.getId();
				}else{
					DAO dataAccess = new DAO();
					Instructor instructor = new Instructor();
					instructor = dataAccess.selectInstructor(dbEmail);
					name = instructor.getFirst_name() + " " + instructor.getLast_name();
					userType ="Instructor";
					userID = instructor.getId();					
					
				}
				lblUserAccessID.setText(Integer.toString(userID));
				lblUserRole.setText(userType);
				Singleton.getInstance().setEmailID(userName);
				Singleton.getInstance().setUserType(lblUserType);
				Singleton.getInstance().setUserAcessID(lblUserAccessID);
				Singleton.getInstance().setUserRol(lblUserRole);
				
				
				errorLabel.setText("");

			} else {
				errorLabel.setText("Incorrect login details");
				System.out.println("Mismatch");
				System.out.println(dbEmail + dbPassword);
				System.out.println(userName + password);
				UserAuthStatus = false;
				clearFields();

			}

		}

		catch (Exception e) {
			System.err.println(e.getMessage());

		}
		if (UserAuthStatus) {
			return true;

		} else {
			return false;
		}

	}
	private void clearFields() {
		userNameField.setText("");
		passwordField.setText("");
	}

}