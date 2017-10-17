package controller;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

public class LoginController
{
  public LoginController() {}
  
  private final ObjectProperty<User> user = new javafx.beans.property.SimpleObjectProperty<User>();
  
  public final ObjectProperty<User> userProperty() {
    return user;
  }
  
  public final User getUser() {
    return (User)userProperty().get();
  }
  
  public final void setUser(User user) {
    userProperty().set(user);
  }
  

  @FXML
  private TextField userNameField;
  @FXML
  private PasswordField passwordField;
  @FXML
  private Label errorLabel;
  @FXML
  private void ok()
  {
    String userName = userNameField.getText();
    String password = passwordField.getText();
    if (authenticate(userName, password)) {
      setUser(new User(userName));
      errorLabel.setText("");
    } else {
      errorLabel.setText("Incorrect login details");
    }
    clearFields();
  }
  
  @FXML
  private void cancel() {
    setUser(null);
    clearFields();
    errorLabel.setText("");
  }
  
  private boolean authenticate(String userName, String password)
  {
    if ((userName.isEmpty()) || (password.isEmpty())) {
      return false;
    }
    return true;
  }
  
  private void clearFields() {
    userNameField.setText("");
    passwordField.setText("");
  }
}