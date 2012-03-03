package com.big.tuwien.SmartMatcher.views.dom;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.mmodel.MetaModelFactory;
import com.big.tuwien.SmartMatcher.util.MetaModelFactoryUtil;

public class DLinkFunction extends AbstractLinkFunction {
	protected MetaModel lhsMetaModel;
	protected MetaModel rhsMetaModel;
	protected NewDirectedLinkGraph lhsGraph;
	protected NewDirectedLinkGraph rhsGraph;
	
	
	public DLinkFunction() {
		MetaModelFactory factory = MetaModelFactoryUtil.getMetaModelFactory();
		lhsMetaModel = factory.getLHSMetaModel();
		rhsMetaModel = factory.getRHSMetaModel();
		
		lhsGraph = new NewDirectedLinkGraph();
		lhsGraph.setNodes(lhsMetaModel.getClasses());
		lhsGraph.build();
		rhsGraph = new NewDirectedLinkGraph();
		rhsGraph.setNodes(rhsMetaModel.getClasses());
		rhsGraph.build();
	}
	
	
	protected boolean linked(Element src, Element target, Integer maxLength) {
		// source and target must be both from the LHS or from the RHS
		if( lhsMetaModel.containsElement(src) && lhsMetaModel.containsElement(target) ) {
			int d = lhsGraph.getMinDistance(src, target);
			return (d != -1 && d <= maxLength) ? true : false; 
		} else if( rhsMetaModel.containsElement(src) && rhsMetaModel.containsElement(target) ) {
			int d = rhsGraph.getMinDistance(src, target);
			return (d != -1 && d <= maxLength) ? true : false; 
		} else {
			// source and target element are not from the same meta-model -> cannot be linked
			return false;
		}
	}


	

}
