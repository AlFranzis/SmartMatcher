package com.big.tuwien.smartmatcher.strategy.sreuse.marshal;

import java.io.Reader;

import com.big.tuwien.smartmatcher.strategy.sreuse.XMLAny;

public interface XMLMarshaller {
	public <T extends XMLAny> String marshall(T xmlAny);
	
	public XMLAny unmarshall(Reader reader);
}
