package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import com.big.tuwien.smartmatcher.strategy.sreuse.IdGenerator;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLFragmentMapping;


public class FragmentMappingDAO extends XmlDAOImpl<XMLFragmentMapping> {
	
	public FragmentMappingDAO() {
		idGenerator = new IdGenerator<XMLFragmentMapping>() {
			@Override
			public String generateId(XMLFragmentMapping f) {
				return "FragmentMapping" + System.currentTimeMillis();
			}
			
		};
	}
	
	
	public FragmentMappingDAO(String fragmentContainer) {
		this();
		this.fragmentContainer = fragmentContainer;
	}
	
	
	public FragmentMappingDAO(IdGenerator<XMLFragmentMapping> idGenerator) {
		this.idGenerator = idGenerator;
	}
	
	
	public void save(XMLFragmentMapping f) {
		super.save(f);
	}
	

	
	
}
