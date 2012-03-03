package xmlunit.extension;

import java.util.List;
import java.util.Map;

import com.big.tuwien.SmartMatcher.strategy.pso.Tuples.T2;

import org.custommonkey.xmlunit.ElementNameQualifier;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ExtElementQualifier extends ElementNameQualifier {
	private String idElement;
	private List<String> elements;
	
	
	public ExtElementQualifier(List<String> elements, String idElement) {
		this.idElement = idElement;
		this.elements = elements;
	}
	
	
	public ExtElementQualifier(Map<String,T2<String,String>> idMap) {
		
		this.elements = elements;
	}
	
	
	@Override
	public boolean qualifyForComparison(Element control, Element test) {
		if(!elements.contains(control.getTagName()) || 
				!elements.contains(test.getTagName())) {
			return super.qualifyForComparison(control, test);
		}
	
		Element idElement1 = childNode(control, idElement);
		Element idElement2 = childNode(test, idElement);
		
		if(idElement1 == null || idElement2 == null) 
			return super.qualifyForComparison(control, test);
		
		String idElement1Value = idElement1.getTextContent();
		String idElement2Value = idElement2.getTextContent();
		
		return idElement1Value.equals(idElement2Value);
	}
	
	
	private Element childNode(Element e, String name) {
		NodeList nl = e.getChildNodes();
		for(int i = 0; i < nl.getLength(); i++) {
			Node child = nl.item(i);
			if(child.getNodeName().equals("name"))
				return (Element) child;
			
		}
		return null;
	}

}
