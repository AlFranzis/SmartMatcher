package com.big.tuwien.smartmatcher.strategy.sreuse.marshal;

import java.io.BufferedWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.XMLContext;

import com.big.tuwien.smartmatcher.strategy.sreuse.ReuseRuntimeException;
import com.big.tuwien.smartmatcher.strategy.sreuse.UnmarshalListenerImpl;
import com.big.tuwien.smartmatcher.strategy.sreuse.UnmarshalledElementResolver;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLAny;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLIdentifiable;

public class XMLMarshallerImpl implements XMLMarshaller {
	protected Marshaller marshaller;
	protected Unmarshaller unmarshaller;
	
	
	public XMLMarshallerImpl() {
		try {
			
			UnmarshalledElementResolver.create();
			
			//--load mapping
			Mapping mapping = new Mapping();
			mapping.loadMapping("castor-mapping.xml");

			XMLContext context = new XMLContext();
			context.addMapping(mapping);
			
			marshaller = context.createMarshaller();
			// no xml declaration 
			//TODO: does not work, xml declaration still in output
			marshaller.setSupressXMLDeclaration(true);
			marshaller.setMarshalAsDocument(false);
			
			// set marshalling listener
			//marshaller.setMarshalListener(new MarshalListenerImpl());

			unmarshaller = context.createUnmarshaller();
			// set unmarshalling listener
			unmarshaller.setUnmarshalListener(new UnmarshalListenerImpl());
			
			unmarshaller.setIDResolver(
					UnmarshalledElementResolver.getInstance());
		} catch (Exception e) {
			throw new ReuseRuntimeException(e);
		}
		
	}
	
	
	@Override
	public String marshall(XMLAny e) {
		if(e instanceof XMLIdentifiable) {
			checkIdSet((XMLIdentifiable) e);
		}
		
		try {		
			StringWriter pwriter = new StringWriter();
			BufferedWriter bw = new BufferedWriter(pwriter);
			marshaller.setWriter(bw);
			marshaller.marshal(e);
			return pwriter.toString();
		} catch (Exception ex) {
			throw new ReuseRuntimeException(ex);
		} 
	}
	
	
	public void marshall(XMLAny o, Writer w) {
		if(o instanceof XMLIdentifiable) {
			checkIdSet((XMLIdentifiable) o);
		}
		
		try {
			marshaller.setWriter(w);
			marshaller.marshal(o);
		} catch (Exception ex) {
			throw new ReuseRuntimeException(ex);
		} 
	}
	

	@Override
	public XMLAny unmarshall(Reader reader) {
		try {
			Object o = unmarshaller.unmarshal(reader);
			
			if(o instanceof XMLIdentifiable) {
				checkIdSet((XMLIdentifiable) o);
			}
			
			return (XMLAny) o;
		} catch (Exception ex) {
			throw new ReuseRuntimeException(ex);
		} 
	}
	
	
	/*
	 * Checks if an XMLIdentifiable instance has it's id set.
	 */
	private void checkIdSet(XMLIdentifiable identifiable) {
		if( identifiable.getId() == null)
			throw new ReuseRuntimeException
					("XMLIdentifable has no id set: " + identifiable);
	}
}
