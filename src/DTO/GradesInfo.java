package DTO;

public class GradesInfo {

	String course;
	String course_Title;

	float attempted = 0f;
	String grade;
	String gpa;

	public GradesInfo() {

	}

	public GradesInfo(String course, String course_Title, float attempted, String grade, String gpa) {

		this.course = course;
		this.course_Title = course_Title;
		this.attempted = attempted;
		this.grade = grade;
		this.gpa = gpa;

	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public float getAttempted() {
		return attempted;
	}

	public void setAttempted(float attempted) {
		this.attempted = attempted;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCourse_Title() {
		return course_Title;
	}

	public void setCourse_Title(String course_Title) {
		this.course_Title = course_Title;
	}

	public String getGpa() {
		return gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

}
