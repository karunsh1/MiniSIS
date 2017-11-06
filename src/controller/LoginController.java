package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DTO.Admin;
import DTO.Student;
import database.MySQLAccess;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.AdminModel;
import model.StudentModel;
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
		return (Users) userProperty().get();
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
	private void ok() throws IOException {
		String userName = userNameField.getText();
		String password = passwordField.getText();
		boolean authStatus = authenticate(userName, password);
		if (authStatus) {
			setUser(new Users(userName, password));
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

			MySQLAccess obj = new MySQLAccess();
			Connection conn = null;
			conn = obj.getConnection();

			// SQL Query
			PreparedStatement login = conn.prepareStatement(" select * from users where email=? and password=?");

			login.setString(1, userName);
			login.setString(2, password);

			ResultSet result = login.executeQuery();

			String dbEmail = "null";
			int userId;
			String dbPassword = "null";
			String type = "null";
			String name = "null";	
			String userType = "null";
			// String errorMsg = "null";
			// HttpSession session = request.getSession();
			if (result.next()) {
				dbEmail = result.getString("email");
				dbPassword = result.getString("password");
				type = result.getString("type");
				System.out.println(type);
				System.out.println(userName + password);
				System.out.println(dbEmail + dbPassword);

				if (type.equals("1")) {
					StudentModel studentModel = new StudentModel();
					Student student = studentModel.selectStudent(dbEmail);
					name = student.getFirst_name() + " " + student.getLast_name();
					userType = "student";
					userId = student.getId();
					System.out.println("Role: " + name);

					// session.setAttribute("studentId", userId);
				} else {
					AdminModel adminModel = new AdminModel();
					Admin admin = adminModel.selectAdmin(dbEmail);
					name = admin.getFirst_name() + " " + admin.getLast_name();
					userType = "admin";
					userId = admin.getId();
				}

				errorLabel.setText("");

			} else {
				errorLabel.setText("Incorrect login details");
				System.out.println("Mismatch");
				System.out.println(dbEmail + dbPassword);
				System.out.println(userName + password);
				UserAuthStatus = false;

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