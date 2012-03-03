/**
 * 
 */
package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import java.io.StringReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.big.tuwien.smartmatcher.strategy.sreuse.ResultHandler;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.marshal.XMLMarshaller;
import com.sleepycat.dbxml.XmlException;
import com.sleepycat.dbxml.XmlResults;
import com.sleepycat.dbxml.XmlValue;

public class FragmentHandler implements ResultHandler<XMLFragment> {
	private XMLMarshaller unmarshaller;
	
	
	public FragmentHandler(XMLMarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
	
	@Override
	public Collection<XMLFragment> convert(XmlResults result) 
										throws ReuseException {
		Set<XMLFragment> frags = new HashSet<XMLFragment>();
		try {
			XmlValue value;
			while((value = result.next()) != null) {
				String xml = value.asString();
				XMLFragment frag = (XMLFragment) unmarshaller.unmarshall(new StringReader(xml));
				frags.add(frag);
			}
		} catch (XmlException e) {
			throw new ReuseException(
					"Error while processing query result", e);
		}
		
		return frags;
	}
}