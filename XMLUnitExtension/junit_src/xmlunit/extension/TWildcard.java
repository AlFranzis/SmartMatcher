package xmlunit.extension;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TWildcard {
	
	@Test
	public void testWildcard() throws Exception {
		String oracle2 =
		//"<?xml version=\"1.0\" encoding=\"UTF-8\"?> " +
		"<fragment id=\"F1\">" +
		"<class id=\"C1\">" +
			"<name>C2</name>" +
			"<attribute id=\"A2\">" +
				"<name>a4</name>" +
			"</attribute>" +
			"<attribute id=\"A1\">" +
				"<name>a3</name>" +
			"</attribute>" +
			"<reference id=\"R3\" target=\"C3\">" +
				"<name>r3</name>" +
			"</reference>" +
			"<reference id=\"R2\" target=\"C2\">" +
				"<name>r2</name>" +
			"</reference>" +
		"</class>" +
		"<class id=\"C2\">" +
			"<name>C1</name>" +
			"<attribute id=\"A4\">" +
				"<name>a2</name>" +
			"</attribute>" +
			"<attribute id=\"A3\">" +
				"<name>a1</name>" +
			"</attribute>" +
			"<reference id=\"R1\" target=\"C1\">" +
				"<name>r1</name>" +
			"</reference>" +
		"</class>" +
		"</fragment>";
		
		String oracle3 = 
		"<fragment id=\"F1\">" +
	    "<class id=\"*\">" +
	        "<name>C1</name>" +
	        "<attribute id=\"*\">" +
	            "<name>a1</name>" +
	        "</attribute>" +
	        "<attribute id=\"*\">" +
	            "<name>a2</name>" +
	        "</attribute>" +
	        "<reference id=\"*\" target=\"*\">" +
	            "<name>r1</name>" +
	       "</reference>" +
	    "</class>" +
	    "<class id=\"*\">" +
	        "<name>C2</name>" +
	        "<attribute id=\"*\">" +
	            "<name>a3</name>" +
	        "</attribute>" +
	        "<attribute id=\"*\">" +
	            "<name>a4</name>" +
	        "</attribute>" +
	        "<reference id=\"*\" target=\"*\">" +
	            "<name>r3</name>" +
	        "</reference>" +
	        "<reference id=\"*\" target=\"*\">" +
	            "<name>r2</name>" +
	        "</reference>" +
	    "</class>" +
	    "</fragment>";
		
		WildcardDiff oracleDiff = new WildcardDiff(oracle3, oracle2);
		System.out.println("OracleDiff: " + oracleDiff);
		assertTrue(oracleDiff.similar());
		assertFalse(oracleDiff.identical());
			
	}
}
