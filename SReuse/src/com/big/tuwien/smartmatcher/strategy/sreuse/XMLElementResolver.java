package com.big.tuwien.smartmatcher.strategy.sreuse;

import com.big.tuwien.SmartMatcher.Element;

public interface XMLElementResolver {
	public void bind(Element e, XMLElement xmlE);
	public XMLElement resolve(Element e);
	public Element revResolve(XMLElement e);
	public boolean isBound(Element e);
}
