package xmlunit.extension;

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceListener;
import org.w3c.dom.Node;

public class WildcardDifferenceListener implements DifferenceListener{
	public static String DEFAULT_WILDCARD = "*";
	private String wildcard = DEFAULT_WILDCARD;
	
	
	public WildcardDifferenceListener() {}
	
	
	public WildcardDifferenceListener(String wildcard) {
		this.wildcard = wildcard;
	}
	
	
	@Override
	public int differenceFound(Difference diff) {
		
		// Different attribute value
		if(diff.getId() == 3) {
			if(diff.getControlNodeDetail().getValue().equals(wildcard)) {
				// ignore difference
				return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
			}
			
		}
		
		// accept difference
		return 0;
	}

	@Override
	public void skippedComparison(Node control, Node test) {
		
	}

}
