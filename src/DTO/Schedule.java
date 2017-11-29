package DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Schedule {

	String course;
	String day;
	String start_time;
	String end_time;
	Integer room_num;
	String building;

	public Schedule(String course, String day, String start_time, String end_time, Integer room_num, String building) {
		super();
		this.course = course;
		this.day = day;
		this.start_time = start_time;
		this.end_time = end_time;
		this.room_num = room_num;
		this.building = building;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Integer getRoom_num() {
		return room_num;
	}

	public void setRoom_num(Integer room_num) {
		this.room_num = room_num;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}


	
	
	

}
