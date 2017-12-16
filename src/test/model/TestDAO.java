package test.model;

import static org.junit.Assert.*;

import org.junit.Test;
import model.DAO;

public class TestDAO {
 
	DAO dataAccessObject=new DAO();

	
	@Test
	public void testGetCourseId() {
		String course_details_id="10";
		String courseId=dataAccessObject.getCourseId(course_details_id);
		assertEquals("1", courseId);
	}
	
	@Test
	public void test1GetTermId() {
		String term="Fall 2017";
		int termId=dataAccessObject.getTermId(term);
		assertEquals(1006, termId);
	}
	
	@Test
	public void test2GetTermId() {
		String term="Fall 2016";
		int termId=dataAccessObject.getTermId(term);
		assertEquals(1003, termId);
	}
	
	@Test
	public void testGetCurrentTermId() {
		int termId=dataAccessObject.getCurrentTermId();
		assertEquals(1006, termId);
	}
	
	@Test
	public void testGetProgramOfCourse() {
		String course_details_id="10";
		String program=dataAccessObject.getProgramOfCourse(course_details_id);
		assertEquals("SOEN", program);
	}
	
	@Test
	public void testGetProgramOfStuddent() {
		String student_id="1000004";
		String program=dataAccessObject.getSubjectCode(student_id);
		assertEquals("SOEN", program);
	}
	
	@Test
	public void testIsCourseCompleted() {
		String course_details_id="10";
		String student_id="1000004";
		boolean completed=dataAccessObject.isCourseCompleted(course_details_id, student_id);
		assertEquals(true, completed);
	}
	@Test
	public void testIsNotCourseCompleted() {
		String course_details_id="6";
		String student_id="1000004";
		boolean completed=dataAccessObject.isCourseCompleted(course_details_id, student_id);
		assertEquals(false, completed);
	} 
	
	@Test
	public void testGetDuePaymentNotZero() {
		String term="Winter 2018";
		String student_id="1000005";
		double dueAmount=dataAccessObject. getDuePayment(term, student_id);
		assertEquals(1900, dueAmount);
	} 
	
	@Test
	public void testGetDuePaymentZero() {
		String term="Summer 2017";
		String student_id="1000005";
		double dueAmount=dataAccessObject. getDuePayment(term, student_id);
		assertEquals(0, dueAmount);
	} 
	
	@Test
	public void testIfDegreeNotCompleted() {
		String term="Summer 2017";
		int student_id=1000005;
		int coursesCompleted=dataAccessObject.studentDegreeStatus(student_id);
		assertEquals(5, coursesCompleted);
	} 
	
	@Test
	public void testHasNoCourseList() {
		int term_id=1007;
		String level="Graduate";
		String program="SOEN";
		boolean hasCourseList=dataAccessObject.hasCourseList(term_id,level,program);
		assertEquals(false, hasCourseList);
	} 
	
	@Test
	public void testWaiveOffExists() {
		String student_id="1000006";
		boolean preRequisiteExists=dataAccessObject.WaiveOffExists(student_id);
		assertEquals(true, preRequisiteExists);
	} 
	
	@Test
	public void testWaiveOffDoesNotExist() {
		String student_id="1000005";
		boolean preRequisiteExists=dataAccessObject.WaiveOffExists(student_id);
		assertEquals(false, preRequisiteExists);
	}
	
	@Test
	public void testNegativeValidateStudentForGpa() {
		int student_id=1000005;
		boolean hasNoGrades=dataAccessObject.validateStudentForCGPA(student_id);
		assertEquals(true, hasNoGrades);
	}
	@Test
	public void testValidateStudentForGpa() {
		int student_id=1000004;
		boolean hasGrades=dataAccessObject.validateStudentForCGPA(student_id);
		assertEquals(true, hasGrades);
	}
}
