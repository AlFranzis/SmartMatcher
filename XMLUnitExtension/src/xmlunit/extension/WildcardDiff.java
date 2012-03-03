package xmlunit.extension;

import static java.util.Arrays.asList;

import java.io.IOException;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.xml.sax.SAXException;

public class WildcardDiff extends Diff {

	public WildcardDiff(String control, String test) throws SAXException,
			IOException {
		super(control, test);
		overrideElementQualifier(
				new RecursiveElementNameAndTextQualifier());
		overrideElementQualifier(
				new ExtElementQualifier(
						asList("class", "attribute", "reference"),
						"name"));
		overrideDifferenceListener(
				new WildcardDifferenceListener());
	}

}
