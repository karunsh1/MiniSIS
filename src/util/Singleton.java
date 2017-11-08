package util;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Singleton {
    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return instance;
    }

    private Label userType;
    private TextField emailID;
    private Label userRol;
    public Label getUserRol() {
		return userRol;
	}
	public void setUserRol(Label userRol) {
		this.userRol = userRol;
	}

	private Label userAcessID;
   
    
    public Label getUserType() {
		return userType;
	}
	public Label getUserAcessID() {
		return userAcessID;
	}
	public void setUserAcessID(Label userAcessID) {
		this.userAcessID = userAcessID;
	}
	public void setUserType(Label lblUserType) {
		this.userType = lblUserType;
	}
	public TextField getEmailID() {
		return emailID;
	}
	public void setEmailID(TextField userNameField) {
		this.emailID = userNameField;
	}
	

	
    
	

   

   
}