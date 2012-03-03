package com.big.tuwien.smartmatcher.strategy.sreuse.proxy;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import com.big.tuwien.smartmatcher.strategy.sreuse.XMLElement;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLIdentifiable;
import com.big.tuwien.smartmatcher.strategy.sreuse.proxy.ProxyWrapper.Provider;
import com.big.tuwien.smartmatcher.strategy.sreuse.proxy.ProxyWrapper.DefaultProvider;

public class TProxyWrapper {
	private final static Logger logger = Logger.getLogger(TProxyWrapper.class);
	
	
	public TProxyWrapper() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Test
	public void testProxyWrapping() {
		XMLElement e1 = new XMLElement();
		e1.setId("C1");
		
		XMLElement e2 = new XMLElement();
		e2.setId("C2");
		
		Provider<XMLElement> provider = new DefaultProvider<XMLElement>() {
			@Override
			public boolean equals(Object other) {
				logger.debug(
						"Provider.equals(Object), wrapped is: " + wrapped );
				return true;
			}
			
			@Override
			public int hashCode() {
				logger.debug(
						"Provider.hashCode(), wrapped is: " + wrapped );
				return 1;
			}
		};
		
		
		XMLIdentifiable proxy1 = ProxyWrapper.getProxyWrapper(XMLIdentifiable.class, 
										e1, provider);
		
		XMLIdentifiable proxy2 = ProxyWrapper.getProxyWrapper(XMLIdentifiable.class, 
										e2, provider);
		
		String proxyId = proxy1.getId();
		assertNotNull(proxyId);
		String e1Id = e1.getId();
		assertNotNull(e1Id);
		
		assertEquals(e1Id, proxyId);
		
		boolean eq = proxy1.equals(proxy2);
		assertTrue(eq);
		
		int hc1 = proxy1.hashCode();
		assertEquals(1, hc1);
		int hc2 = proxy2.hashCode();
		assertEquals(1, hc2);	
	}
	
	
	@Test
	public void testProxyWrappingEquals() {
		XMLElement e1 = new XMLElement();
		e1.setId("C1");
		
		Provider<XMLElement> provider = new DefaultProvider<XMLElement>() {
			@Override
			public boolean equals(Object other) {
				logger.debug(
						"Provider.equals(Object), other is: " + other 
						+ ", wrapped is: " + wrapped );
				if(wrapped == other)
					return true;
				if(other == null || !(other instanceof XMLIdentifiable))
					return false;
				
				XMLIdentifiable that = (XMLIdentifiable) other;
				
				boolean eq = wrapped.getId().equals(that.getId());
				return eq;
			}
			
			@Override
			public int hashCode() {
				logger.debug(
						"Provider.hashCode(), wrapped is: " + wrapped );
				return 1;
			}
		};
		
		
		XMLIdentifiable proxy1 = ProxyWrapper.getProxyWrapper(XMLIdentifiable.class, 
										e1, provider);
		// proxy2 wraps same object
		XMLIdentifiable proxy2 = ProxyWrapper.getProxyWrapper(XMLIdentifiable.class, 
										e1, provider);
		
		String proxyId = proxy1.getId();
		assertNotNull(proxyId);
		
		String e1Id = e1.getId();
		assertNotNull(e1Id);
		
		assertEquals(e1Id, proxyId);
		
		boolean eq = proxy1.equals(proxy2);
		assertTrue(eq);
		
		int hc1 = proxy1.hashCode();
		assertEquals(1, hc1);
		int hc2 = proxy2.hashCode();
		assertEquals(1, hc2);	
	}
}
