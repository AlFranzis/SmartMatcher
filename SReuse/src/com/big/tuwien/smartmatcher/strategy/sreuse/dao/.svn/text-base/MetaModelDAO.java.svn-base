package com.big.tuwien.smartmatcher.strategy.sreuse.dao;

import com.big.tuwien.smartmatcher.strategy.sreuse.IdGenerator;
import com.big.tuwien.smartmatcher.strategy.sreuse.XMLMetaModel;


public class MetaModelDAO extends XmlDAOImpl<XMLMetaModel> {
	
	
	public MetaModelDAO() {
		idGenerator = new IdGenerator<XMLMetaModel>() {
			@Override
			public String generateId(XMLMetaModel mm) {
				return "MetaModel" + System.currentTimeMillis();
			}
			
		};
	}
	
	
	public MetaModelDAO(String fragmentContainer) {
		this();
		this.fragmentContainer = fragmentContainer;
	}
	
	
	public MetaModelDAO(IdGenerator<XMLMetaModel> idGenerator) {
		this.idGenerator = idGenerator;
	}
	
	
	public void save(XMLMetaModel mm) {
		super.save(mm);
	}
	
	
}
