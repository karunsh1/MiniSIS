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
	public void testGetTermId() {
		String term="Fall 2017";
		int termId=dataAccessObject.getTermId(term);
		assertEquals(1, termId);
	}
	
	

}
