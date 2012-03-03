package com.big.tuwien.SmartMatcher.mmodel;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.common.util.URI;

import com.big.tuwien.ModelManager.imodel.InstanceElement;
import com.big.tuwien.SmartMatcher.Element;


/**
 * This filter filters all Meta-Model elements that have no instances,
 * e.g. do not occur in the given instance models.
 * @author alex
 *
 */
public class InstanceFilter implements ElementFilter {

	
	public boolean filter(Element e) {
		// HashMap<URI, Vector<EObject>> fileInstances = e.getRepresentedBy().getEObjects2Map();
		Map<URI,List<InstanceElement<?,?>>> fileInstances = e.getRepresentedBy().getEObjectsMap();
		List<? super InstanceElement<?,?>> instances = new Vector<InstanceElement<?,?>>();
		for (URI file: fileInstances.keySet()) {
			instances.addAll(fileInstances.get(file));
		}
		// meta-model elements that have no instances are filtered.
		return (instances == null || instances.isEmpty()); 
	}

}
