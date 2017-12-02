package test.model;

import static org.junit.Assert.*;

import org.junit.Test;
import model.DAO;

public class TestDAO {
 
	DAO dataAccessObject=new DAO();

	
	@Test
	public void testGetCourseId() {
		String course_details_id="1";
		String courseId=dataAccessObject.getCourseId(course_details_id);
		assertEquals("13", courseId);
	}
	
	@Test
	public void test1GetTermId() {
		String term="Fall 2017";
		int termId=dataAccessObject.getTermId(term);
		assertEquals(1, termId);
	}
	
	@Test
	public void test2GetTermId() {
		String term="Fall 2016";
		int termId=dataAccessObject.getTermId(term);
		assertEquals(3, termId);
	}
	
	@Test
	public void testGetCurrentTermId() {
		int termId=dataAccessObject.getCurrentTermId();
		assertEquals(1, termId);
	}
//	@Test
//	public void testGetDuePayment() {
//		int term_id=1;
//		String StudentId="2";
//		double due_payment=dataAccessObject.getDuePayment( term,StudentId);
//		assertEquals(0.0, due_payment);
//	}

	
	
}
