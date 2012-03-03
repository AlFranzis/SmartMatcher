package com.big.tuwien.smartmatcher.strategy.sreuse.marshal;

import java.io.Reader;
import java.io.Writer;

import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElement;

public class ElementMarshaller extends XMLMarshallerImpl {
	
	public ElementMarshaller() {
		super();
	}
	
	
	public String marshall(XMLElement e) {
		return super.marshall(e);
	}
	
	
	public void marshall(XMLElement o, Writer w) {
		super.marshall(o, w);
	}
	
	
	public XMLElement unmarshall(Reader reader) {
		return (XMLElement) super.unmarshall(reader);
	}
}
