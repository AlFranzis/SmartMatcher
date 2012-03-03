package com.big.tuwien.SmartMatcher.model;

import com.big.tuwien.SmartMatcher.mmodel.MetaModel;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;


public class NewSimpleSimilarityMeasure {
    private BubbleView bubbleView;
    private MetaModel lhsMetaModel;
    private MetaModel rhsMetaModel;
    private Integer maxMappings = null;
	// private static Logger logger = Logger.getLogger(NewSimpleSimilarityMeasure.class);
    
    public NewSimpleSimilarityMeasure() {}

    
    public double getSimilarity() {
    	System.out.println("SIMILARITY: " + getCorrectMappingCt() / new Double(getMaxMappings()));
    	return (getCorrectMappingCt() / new Double(getMaxMappings()));
    }

    
    public void setBubbleView(BubbleView bubbleView) {
    	this.bubbleView = bubbleView;
    }
    
    
    public void setLHSMetaModel(MetaModel lhsMetaModel) {
    	this.lhsMetaModel = lhsMetaModel;
    }
    
    
    public void setRHSMetaModel(MetaModel rhsMetaModel) {
    	this.rhsMetaModel = rhsMetaModel;
    }
    

    
    /*
     * Returns the number of correct mappings the metamodel could 
     * contain theoretically.
     */
    private int calculateMaxMappings() {
    	int lhsElementCt = this.lhsMetaModel.getElements().size();
    	int rhsElementCt = this.rhsMetaModel.getElements().size();

    	return lhsElementCt >= rhsElementCt ? lhsElementCt : rhsElementCt;
    }
    
    
    /**
     * Returns the number of correct mappings in the matrix.
     */
    public int getCorrectMappingCt() {
    	return this.bubbleView.getBubbles(Bubble.STATE.eval2TRUE).size();
    }
    
    
    public int getMaxMappings() {
    	if(this.maxMappings == null) {
    		this.maxMappings = calculateMaxMappings();
    		assert this.maxMappings > 0 : "Maximal count of mappings should be greater than 0";
    	}
    	return this.maxMappings;
    }


	public double getUsedMetamodelSimilarity() {
		return 0;
	}
}
