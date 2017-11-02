package DTO;

public class PaymentDue {
	private int Id;
	private int student_id;
	private int term_id;
	private double due_amount;
	private String due_type;
	
	public PaymentDue(){
		super();
	}
	public PaymentDue(int student_id, int term_id){
		this.student_id = student_id;
		this.term_id = term_id;
	}
	public PaymentDue(int id, int student_id, int term_id, double due_amount, String due_type) {
		super();
		Id = id;
		this.student_id = student_id;
		this.term_id = term_id;
		this.due_amount = due_amount;
		this.due_type = due_type;
	}
	public PaymentDue(int id, int student_id, int term_id, double due_amount) {
		super();
		this.student_id = student_id;
		this.term_id = term_id;
		this.due_amount = due_amount;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getTerm_id() {
		return term_id;
	}
	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}
	public double getDue_amount() {
		return due_amount;
	}
	public void setDue_amount(double due_amount) {
		this.due_amount = due_amount;
	}
	public String getDue_type() {
		return due_type;
	}
	public void setDue_type(String due_type) {
		this.due_type = due_type;
	}
}
