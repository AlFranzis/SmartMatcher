package com.big.tuwien.smartmatcher.strategy.sreuse;

import java.io.StringReader;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshallerImpl;

public class TMarshalUnlistener {

	public TMarshalUnlistener() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Test
	public void testUnmarshallingSimpleFragment() {
		String xml =
			"<fragment id=\"F1\">" +
			"<class id=\"C1\">" +
			"<name>C1</name>" +
			"<attribute id=\"A1\">" +
			"<name>a1</name>" +
			"</attribute>" +
			"</class>" +
			"<class id=\"C2\">" +
			"<name>C2</name>" +
			"<attribute id=\"A2\">" +
			"<name>a2</name>" +
			"</attribute>" +
			"<reference id=\"R1\" target=\"C1\">" +
			"<name>r1</name>" +
			"</reference>" +
			"</class>" +
			"</fragment>";

		XMLMarshallerImpl unmarshaller = new XMLMarshallerImpl();
		XMLFragment xmlFragment = (XMLFragment) unmarshaller.unmarshall(new StringReader(xml));

		

	}

}
