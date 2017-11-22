package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {
	
//	
//    public SimpleStringProperty program =new SimpleStringProperty();
//    public SimpleStringProperty courseTitle =new SimpleStringProperty();
//	public SimpleIntegerProperty courseId = new SimpleIntegerProperty();
//	public SimpleStringProperty level =new SimpleStringProperty();
//	public SimpleIntegerProperty numCredits = new SimpleIntegerProperty();
//	public SimpleStringProperty term =new SimpleStringProperty();
//	public SimpleStringProperty description =new SimpleStringProperty();
	String program;
	String courseTitle;
	String courseId;
	

	String level;
	Integer numCredits;
	String term;
	String description;
	String instructor;
	
	public Course(String program, String courseTitle, String courseId,String level,int numCredits,String term,String description, String instructor){
		super();
		this.program=program;
		this.courseTitle=courseTitle;
		this.courseId=courseId;
		this.level=level;
		this.numCredits=numCredits;
		this.term=term;
		this.description=description;
		this.instructor=instructor;
		System.out.println("in constructor" + courseId);
	}
	
	public Course(){
		super();
	}
	
	
	
//	public Term(String term, int termid){
//		super();
//		this.term = term;
//		this.termid = termid;
//		
//	}
	

	
//	public String getProgram() {
//		return program.get();
//	}
//	

//	public String getCourseTitle() {
//		return courseTitle.get() ;
//	}
//	
//
//	public int getCourseId() {
//		return courseId.get();
//	}
//	
//	
//	public String getLevel() {
//		return level.get() ;
//	}
//
//	
//	public int getNumCredits() {
//		return numCredits.get();
//	}
//	
//
//	public String getTerm() {
//		return term.get() ;
//	}
//	
//	
//	public String getDescription() {
//		return description.get();
//	}
	

	public String getProgram() {
		return program;
	}
	
	public void setProgram(String program) {
		this.program = program;
	}

	public String getCourseTitle() {
		return courseTitle ;
	}
	
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
    
	
	
	public String getLevel() {
		return level ;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public Integer getNumCredits() {
		return numCredits;
	}
	
	public void setNumCredits(int numCredits) {
		this.numCredits = numCredits;
	}
	
	
	public String getTerm() {
		return term ;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructor() {
		return instructor;
	}
	
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
	

}
