/**
 * 
 */
package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.big.tuwien.smartmatcher.strategy.sreuse.ResultHandler;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseException;
import com.sleepycat.dbxml.XmlException;
import com.sleepycat.dbxml.XmlResults;
import com.sleepycat.dbxml.XmlValue;

public class StringSequenceHandler implements ResultHandler<String> {
	@Override
	public Collection<String> convert(XmlResults result)
			throws ReuseException {
		Set<String> ss = new HashSet<String>();
		try {
			XmlValue value;
			while((value = result.next()) != null) {
				String s = value.asString();
				ss.add(s);
			}
		} catch (XmlException e) {
			throw new ReuseException(
					"Error while processing query result", e);
		}
		
		return ss;
	}
}