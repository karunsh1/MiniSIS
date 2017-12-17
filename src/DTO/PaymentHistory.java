package DTO;

public class PaymentHistory {

private int studentId;
private double paymentDue;
private String semester;
public PaymentHistory(int studentId2, double d, String term2) {
	studentId=studentId2;
	paymentDue=d;
	semester=term2;
			
}
public int getStudentId() {
	return studentId;
}
public void setStudentId(int studentId) {
	this.studentId = studentId;
}
public double getPaymentDue() {
	return paymentDue;
}
public void setPaymentDue(double paymentDue) {
	this.paymentDue = paymentDue;
}
public String getSemester() {
	return semester;
}
public void setSemester(String semester) {
	this.semester = semester;
}




}
