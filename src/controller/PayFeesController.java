package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import model.DAO;
import util.Singleton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class PayFeesController implements Initializable {
	
	
	
	@FXML Label LabelDebitCardNum;
	@FXML TextField TextFieldDebitCardNum;
	@FXML ComboBox MonthCombobox;
	@FXML ComboBox YearCombobox;
	@FXML Label LabelCVV;
	@FXML TextField TextFieldCVV;
	@FXML Button ButtonPay;
	@FXML TextField TextFieldAmountDue;

	@FXML Label LabelTerm;
	@FXML TextField TextFieldPayAmount;
	@FXML ComboBox ComboboxTerm;
	 String studentID=null;
	String term=null;
	Integer moneyToBePaid;
	DAO dataAccess = new DAO();
	String AmountDueString;
	@FXML Label labelExpiryDate;
	@FXML Label LabelAmountDue;
	@FXML Label LabelAmountToPay;
	@FXML Label messageLabel;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LabelDebitCardNum.setVisible(false);
		LabelTerm.setVisible(true);
		ComboboxTerm.setVisible(true);
		disableAll();
	    ObservableList<String> monthsObservableList = FXCollections.observableArrayList();
	    ObservableList<Integer> yearObservableList = FXCollections.observableArrayList();
	 
	   
	    monthsObservableList.addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        for (int i = 1980; i <= 2020; i++)
        {
            yearObservableList.add(i);
        }
        MonthCombobox.setItems(monthsObservableList);
        YearCombobox.setItems(yearObservableList);
    	
		ArrayList termListArray = dataAccess.termNames();
		ObservableList termlist = FXCollections.observableArrayList(termListArray);
		ComboboxTerm.setItems(termlist);
		
		
		 
		 ComboboxTerm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> ov, String t, String t1)
				{   
					 term=(String) ComboboxTerm.getValue();
					  studentID = Singleton.getInstance().getUserAcessID().getText();
					 
	      
         //data = new ObservableList<ObservableList>();
         //data = FXCollections.observableArrayList();
         try{
         
           ResultSet rs = dataAccess.getPaymentDetails(term,studentID);
           System.out.println("resultset" +rs);
  
           if(rs.next())
           {   if(rs.getString("amount_due").equals("0")) {
        	   messageLabel.setText("\"Braavo!!!!!  You have already paid the fees:))\"");
        	   disableAll();
        	   }
           else {
                  	   TextFieldAmountDue.setText(rs.getString("amount_due"));
        	   System.out.println(rs.getString("amount_due"));
        	   messageLabel.setText("");
        	   enableAll();
           }
           }
           else {
        	   messageLabel.setText("\"Braavo!!!!!  You have already paid the fees:))\"");
        	   disableAll();
           }
	}
 catch(SQLException ex) {
        	 
         }
				}
			});
        
	}
	@FXML public void onClickPay(ActionEvent event) {
		
//		TextFieldDebitCardNum.focusedProperty().addListener((arg0, oldValue, newValue) -> {
//	        if (!newValue) { //when focus lost
		System.out.println(TextFieldDebitCardNum.getText().length());
	            //if((!TextFieldDebitCardNum.getText().matches("\"[0-9]*\""))|| (TextFieldDebitCardNum.getText().length()!=16))
		       
	            if(validateData()) {
	            	
					if(TextFieldPayAmount.getText()!= null )
	            	moneyToBePaid= Integer.parseInt(TextFieldPayAmount.getText());
					Integer AmountDue=Integer.parseInt(TextFieldAmountDue.getText());
					if(moneyToBePaid<=AmountDue)
					{
						AmountDue= AmountDue-moneyToBePaid;
						AmountDueString=AmountDue.toString();
						dataAccess.setPaymentDetails(term, studentID, AmountDueString);
						 Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information Dialog");
							alert.setHeaderText("Payment");
							alert.setContentText("Payment Done Successfully ");
							alert.showAndWait();
					}
					else
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText("Payment");
						alert.setContentText("Amount to be paid cannot be greater than amount due");
						alert.showAndWait();
						
					}
					
	            }
	            reset();

	}
	
    public boolean validateData() {
        if(!TextFieldDebitCardNum.getText().matches("[0-9]*")||TextFieldDebitCardNum.getText().length()!=16 )
        {
        	TextFieldDebitCardNum.setText("");
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Payment");
			alert.setContentText("Invalid debit card info.Enter numbers and credit card num length should be 16");
			alert.showAndWait();
			return false;
        }
         if(!TextFieldCVV.getText().matches("[0-9]*")||TextFieldCVV.getText().length()>3 )
        {
        	TextFieldCVV.setText("");
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Payment");
			alert.setContentText("Invalid CVV Number.CVV Number should be digits and length ==3 ");
			alert.showAndWait();
			return false;
        }
        if(MonthCombobox.getValue()==null || YearCombobox.getValue()==null)
        {
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Payment");
			alert.setContentText("Wrong Expiry Date.Please choose both month and year ");
			alert.showAndWait();
			return false;
        }
        
        return true;
    }
	
	
	public void disableAll() {
		LabelDebitCardNum.setVisible(false);
		TextFieldDebitCardNum.setVisible(false);
		MonthCombobox.setVisible(false);
		YearCombobox.setVisible(false);
		LabelCVV.setVisible(false);
		TextFieldCVV.setVisible(false);
		 ButtonPay.setVisible(false);
		 TextFieldAmountDue.setVisible(false);
		LabelTerm.setVisible(false);
		TextFieldPayAmount.setVisible(false);
		labelExpiryDate.setVisible(false);
		LabelAmountDue.setVisible(false);
		LabelAmountToPay.setVisible(false);
		
	
	}
	public void enableAll() {
		LabelDebitCardNum.setVisible(true);
		TextFieldDebitCardNum.setVisible(true);
		MonthCombobox.setVisible(true);
		YearCombobox.setVisible(true);
		LabelCVV.setVisible(true);
		TextFieldCVV.setVisible(true);
		 ButtonPay.setVisible(true);
		 TextFieldAmountDue.setVisible(true);
		LabelTerm.setVisible(true);
		TextFieldPayAmount.setVisible(true);
		labelExpiryDate.setVisible(true);
		LabelAmountDue.setVisible(true);
		LabelAmountToPay.setVisible(true);
		
		
	}
	public void reset(){
		TextFieldDebitCardNum.setText(" ");
		MonthCombobox.getSelectionModel().clearSelection();
		YearCombobox.getSelectionModel().clearSelection();
		TextFieldCVV.setText(" ");
	     TextFieldAmountDue.clear();
		TextFieldPayAmount.clear();
		ComboboxTerm.getSelectionModel().clearSelection();
		messageLabel.setText("");
	}

}
