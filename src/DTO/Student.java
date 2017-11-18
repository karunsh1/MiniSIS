package DTO;

public class Student {
	int id;
	String first_name;
	String last_name;
	String email;
	String mobile;
	String address;
	String career_Name;
	String subject_Name;

	public Student() {
		super();
	}
	
	public Student(String first_name, String first_name2){
		this.first_name = first_name;
		this.last_name = last_name;
		
	}
	
	public Student(String first_name,String last_name, String email, String mobile, String address) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.mobile = mobile;
	}
	
	public Student(int id, String first_name,String last_name, String email, String mobile,String address) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.mobile = mobile;
	}


	public Student(String first_name, String last_name, String career_Level, String subject_Name) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.career_Name = career_Level;
		this.subject_Name = subject_Name;
		
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	public String getCareer_Name() {
		return career_Name;
	}


	public void setCareer_Name(String career_Name) {
		this.career_Name = career_Name;
	}


	public String getSubject_Name() {
		return subject_Name;
	}


	public void setSubject_Name(String subject_Name) {
		this.subject_Name = subject_Name;
	}
}
