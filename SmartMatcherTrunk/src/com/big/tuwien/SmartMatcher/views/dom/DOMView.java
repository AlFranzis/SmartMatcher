package com.big.tuwien.SmartMatcher.views.dom;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.big.tuwien.SmartMatcher.AttributeElement;
import com.big.tuwien.SmartMatcher.ClassElement;
import com.big.tuwien.SmartMatcher.EcoreElement;
import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.ReferenceElement;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.operators.Operator;
import com.big.tuwien.SmartMatcher.util.QueryUtil;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEvent;
import com.big.tuwien.SmartMatcher.views.bubble.MappingEventListener;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble.STATE;
import com.big.tuwien.SmartMatcher.views.iterator.Role;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;



public class DOMView implements MappingEventListener {
	private static Logger logger = Logger.getLogger(DOMView.class);
	
	private static final String ROOT = "matrix";
	// private static final String ISMAPPED = "isMapped";
	
	public static final String ID = "id";
	
	private static final String NAME = "name";
	
	private static final String CLASS = "class";
	// private static final String SIDE = "side";
	
	private static final String ATTRIBUTE = "attribute";
	
	private static final String MAPPINGS = "mappings";
	public static final String MAPPING_STATE = "state";
	public static final String ROLE = "role";
	public static final String ROLE_NAME = "name";
	public static final String ROLE_ELEMENT = "element";
	public static final String MAPPING = "mapping";
	private static final String LHS = "lhs";
	private static final String RHS = "rhs";
	// private static final String LHSFOCUSELEMENT = "lhsFocusElement";
	// private static final String RHSFOCUSELEMENT = "rhsFocusElement";
	public static final String OP = "op";
	
	private static final String REFERENCE = "reference";
	private static final String CONTAINER = "container";
	private static final String TARGET = "target";
	
	
	protected int elementId = 1;
	protected BiMap<Element,Integer> elements2Id = new HashBiMap<Element,Integer>();
	protected BiMap<Bubble<? extends Operator>,org.w3c.dom.Element> bubbles2mappingNodes2 = new HashBiMap<Bubble<? extends Operator>,org.w3c.dom.Element>();
	
	
	protected DOMViewBridge bridge;
	protected Document doc;
	protected org.w3c.dom.Element root;
	
	protected MetaModelFactory metaModelFactory;
	
	
	public DOMView() {
		this.bridge = new DOMViewBridge(this);
	}
	
	
	public void setMetaModelFactory(MetaModelFactory factory) {
		this.metaModelFactory = factory;
	}
	
	
	public DOMViewBridge getBridge() {
		return bridge;
	}
	
	
	public void buildView() throws ParserConfigurationException {
		 DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		 DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		 
         this.doc = docBuilder.newDocument();
         this.root = doc.createElement(ROOT);
         this.doc.appendChild(root);
         
         org.w3c.dom.Element mappings = doc.createElement(MAPPINGS);
         this.root.appendChild(mappings);
         
         org.w3c.dom.Element lhs = doc.createElement(LHS);
         this.root.appendChild(lhs);
         
         List<Element> lhsElements = metaModelFactory.getLHSMetaModel().getElements();
         List<Element> lhsClasses = QueryUtil.filterExceptClasses(lhsElements);
         for(Element lhsClass : lhsClasses) {
        	 if(!IsParsed(lhsClass)) {
        		 ClassElement _lhsClass = (ClassElement) lhsClass.getRepresentedBy();
        		 org.w3c.dom.Element clazz = generateClassNode(_lhsClass, true);
        		 lhs.appendChild(clazz);
        	 } 
         }
         
         org.w3c.dom.Element rhs = doc.createElement(RHS);
         this.root.appendChild(rhs);
         
         List<Element> rhsElements = metaModelFactory.getRHSMetaModel().getElements();
         List<Element> rhsClasses = QueryUtil.filterExceptClasses(rhsElements);
         for(Element rhsClass : rhsClasses) {
        	 if(!IsParsed(rhsClass)) {
        		 ClassElement _rhsClass = (ClassElement) rhsClass.getRepresentedBy();
        		 org.w3c.dom.Element clazz = generateClassNode(_rhsClass, false);
        		 rhs.appendChild(clazz);
        	 }
         } 

	}
	
	
	public void mappingEvent2(int eventType, Bubble<? extends Operator> bubble) {
		switch(eventType) {
		case MappingEvent.APPLIED_EVENT:
			org.w3c.dom.Element mappingNode = generateMappingNode2(bubble);
			this.root.getElementsByTagName(MAPPINGS).item(0).appendChild(mappingNode);
			this.bubbles2mappingNodes2.put(bubble, mappingNode);
			break;
		case MappingEvent.EVALUATION_EVENT:
			logger.debug("Evaluation event not implemented !!!");
			break;
		case MappingEvent.BUBBLE_STATE_CHANGED:
			STATE state = bubble.getState();
			logger.debug("Changing state of bubble " + bubble + " to " + state);
			this.bubbles2mappingNodes2.get(bubble).setAttribute(MAPPING_STATE, bubble.getState().toString());
			break;
		default:
			break;
		}
	}
	
	
	private org.w3c.dom.Element generateMappingNode2(Bubble<? extends Operator> bubble) {
		org.w3c.dom.Element mappingNode = this.doc.createElement(MAPPING);
		
		mappingNode.setAttribute(OP, bubble.getOperatorName());
		mappingNode.setAttribute(MAPPING_STATE, bubble.getState().toString());
		
		for(Entry<? extends Role,Element> entry : bubble.getConfiguration().getRoles().entrySet()) {
			Element e = entry.getValue();
			
			if(!containsElement(e)) 
				throw new IllegalArgumentException(
				"Given bubble contains an element that is not contained in the DOMView: " + e);
			
			String roleName = entry.getKey().getName();
			org.w3c.dom.Element roleNode = this.doc.createElement(ROLE);
			roleNode.setAttribute(ROLE_NAME, roleName);
			roleNode.setAttribute(ROLE_ELEMENT, getElementId(e).toString());
			mappingNode.appendChild(roleNode);
			
		}
		
		return mappingNode;
	}
	
	
	private org.w3c.dom.Element generateReferenceLink(ReferenceElement ref) {
		org.w3c.dom.Element referenceLink = this.doc.createElement(REFERENCE);
		referenceLink.setAttribute(ID, registerElement(ref));
		referenceLink.setAttribute(NAME, ref.getFullPathName());
		referenceLink.setAttribute(CONTAINER, String.valueOf(getElementId(ref.getContainedIn())));
		referenceLink.setAttribute(TARGET, String.valueOf(getElementId(ref.getPointsTo())));
		
		return referenceLink;
	}
	
	
	private org.w3c.dom.Element generateAttributeNode(AttributeElement a) {
		org.w3c.dom.Element attributeNode = this.doc.createElement(ATTRIBUTE);
		attributeNode.setAttribute(ID, registerElement(a));
		attributeNode.setAttribute(NAME, a.getFullPathName());
		return attributeNode;
	}
	
	
	private org.w3c.dom.Element generateClassNode(ClassElement clazz, boolean lhs) {
		org.w3c.dom.Element clazzNode = this.doc.createElement(CLASS);
		clazzNode.setAttribute(ID, registerElement(clazz));
		clazzNode.setAttribute(NAME, clazz.getFullPathName());
		
		for(AttributeElement ae : clazz.getAttributes()) {
			org.w3c.dom.Element attributeNode = generateAttributeNode(ae);
			clazzNode.appendChild(attributeNode);
		}
		
		for(ReferenceElement re : clazz.getReferences()) {
			ClassElement referencedClass = re.getPointsTo();
			if(!IsParsed(referencedClass)) {
				if(lhs)
					this.root.getElementsByTagName(LHS).item(0).appendChild(generateClassNode(referencedClass, true));
				else
					this.root.getElementsByTagName(RHS).item(0).appendChild(generateClassNode(referencedClass, false));
					
			} 
			org.w3c.dom.Element referenceNode = generateReferenceLink(re);
			clazzNode.appendChild(referenceNode);
		}
		
		return clazzNode;
	}
	
	
	public <T extends Element> Integer getElementId(T e) {
		return elements2Id.get(e);
	}
	
	
	private <T extends EcoreElement> Integer getElementId(T e) {
		return elements2Id.get(e.getRepresents());
	}
	
	
	private <T extends EcoreElement> boolean IsParsed(T e) {
		return elements2Id.containsKey(e.getRepresents());
	}
	
	private <T extends Element> boolean IsParsed(T e) {
		return elements2Id.containsKey(e);
	}
	
	
	private <T extends Element> String registerElement(T e) {
		Integer id = elementId++;
		this.elements2Id.put(e, id);
		return String.valueOf(id);
	}
	
	
	private <T extends EcoreElement> String registerElement(T e) {
		Integer id = elementId++;
		this.elements2Id.put(e.getRepresents(), id);
		return String.valueOf(id);
	}
	
	
	private boolean containsElement(Element e) {
		return elements2Id.containsKey(e);
	}
	
	
	public String getStringRepresentation() {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			TransformerFactory tfac = TransformerFactory.newInstance();
			Transformer t = tfac.newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.STANDALONE, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource dsource = new DOMSource(this.doc); 
			StreamResult sresult = new StreamResult(out);
			t.transform(dsource, sresult);
			
			return out.toString();
		} catch (TransformerConfigurationException e) {
			logger.error(e);
		} catch (TransformerFactoryConfigurationError e) {
			logger.error(e);
		} catch (TransformerException e) {
			logger.error(e);
		}
		
		return null;
	}
	
	
	public Element getElement(int id) {
		return this.elements2Id.inverse().get(id);
	}
	
	
	public Document getDOM() {
		return this.doc;
	}
	
	
	public Query createQuery() {
		Query q = new Query();
		q.setDomView(this);
		return q;
	}
	
	
	public Query createQuery(String xPathExp) {
		Query q = new Query(xPathExp);
		q.setDomView(this);
		return q;
	}
	
}