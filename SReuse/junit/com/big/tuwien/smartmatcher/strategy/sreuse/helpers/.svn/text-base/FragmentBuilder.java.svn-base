package com.big.tuwien.smartmatcher.strategy.sreuse.helpers;

import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.getAttributes;
import static com.big.tuwien.smartmatcher.strategy.sreuse.Helpers.getReferences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.smartmatcher.strategy.sreuse.Fragment;
import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.big.tuwien.smartmatcher.strategy.sreuse.fragmentize.ElementDecoratorFactory;

public class FragmentBuilder {
	private static int idGenerator = 0;
	private boolean idGeneratorEnabled = true;
	
	private MetaModel mm;
	
	
	public FragmentBuilder() {}
	
	
	public FragmentBuilder(MetaModel mm) {
		init(mm);
	}
	
	
	public void enableIdGenerator(boolean on) {
		idGeneratorEnabled = on;
	}
	
	
	public void init(MetaModel mm) {
		this.mm = mm;
	}
	
	
	public Fragment_ f(String ...eNames) {
		return f(Arrays.asList(eNames));
	}
	
	
	public Fragment_ f(List<String> eNames) {
		Set<Element> es = new HashSet<Element>();
		for(String eName : eNames) {
			Element e = mm.getElementByName(eName);
			if(e == null)
				throw new ReuseRuntimeException(
						"Metamodel does not contain element " + eName);
			es.add(e);
		}
		
		// decorate all elements
		Set<Element> decClasses = ElementDecoratorFactory.decorate(es);
		
		Fragment_ f = new Fragment_();
		if(idGeneratorEnabled)
			f.setId(new Integer(++idGenerator).toString());
		f.setRoot(true);
		f.setClasses(decClasses);
		return f;
	}
	
	
	public Fragment_ f(List<Fragment_> parents, String ...eNames) {
		return f(parents, Arrays.asList(eNames));
	}
	
	
	public Fragment_ f(List<Fragment_> parents, List<String> eNames) {
		Fragment_ f = f(eNames);
		f.setParents(new CopyOnWriteArraySet<Fragment>(parents));
		f.setRoot(false);
		return f;
	}
	
	
	/**
	 * Subclass of Fragment that allows to query it's contained
	 * elements with the method {@link Fragment_#getElementByName(String)}
	 * @author alex
	 *
	 */
	public static class Fragment_ extends Fragment {
		private Map<String,Element> elements =
					new HashMap<String, Element>();
		
		public Element getElementByName(String qname) {
			return elements.get(qname);
		}
		
		
		@Override
		public void setClasses(Set<Element> classes) {
			for(Element c : classes) {
				elements.put(c.getFullPathName(), c);
				for(Element a : getAttributes(c)) {
					elements.put(a.getFullPathName(), a);
				}
				for(Element r : getReferences(c)) {
					elements.put(r.getFullPathName(), r);
				}
			}
			
			super.setClasses(classes);
		}
		
		
	}
	
	
	public <E> List<E> li(E ...es) {
		return Arrays.asList(es);
	}
	
}
