package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import java.io.StringReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.big.tuwien.smartmatcher.strategy.sreuse.ResultHandler;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshaller;
import com.sleepycat.dbxml.XmlException;
import com.sleepycat.dbxml.XmlResults;
import com.sleepycat.dbxml.XmlValue;

public class FragmentMappingHandler implements ResultHandler<XMLFragmentMapping> {
	private XMLMarshaller unmarshaller;
	
	
	public FragmentMappingHandler(XMLMarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
	
	@Override
	public Collection<XMLFragmentMapping> convert(XmlResults result) 
										throws ReuseException {
		Set<XMLFragmentMapping> fms = new HashSet<XMLFragmentMapping>();
		try {
			XmlValue value;
			while((value = result.next()) != null) {
				String xml = value.asString();
				XMLFragmentMapping fm = (XMLFragmentMapping) 
								unmarshaller.unmarshall(new StringReader(xml));
				fms.add(fm);
			}
		} catch (XmlException e) {
			throw new ReuseException(
					"Error while processing query result", e);
		}
		
		return fms;
	}
}
