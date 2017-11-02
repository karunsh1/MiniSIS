package DTO;

public class StudentPayment {
	private int id;
	private int student_id;
	private double paid_amount;
	
	public StudentPayment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StudentPayment(int student_id) {
		super();
		this.student_id = student_id;
	}
	
	public StudentPayment(int student_id, double paid_amount) {
		super();
		this.student_id = student_id;
		this.paid_amount = paid_amount;
	}
	
	public StudentPayment(int id, int student_id, double paid_amount) {
		super();
		this.id = id;
		this.student_id = student_id;
		this.paid_amount = paid_amount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	
	public double getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}
	
	
}
