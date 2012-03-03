package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.big.tuwien.SmartMatcher.strategy.pso.merge.TA2AMergerLHSgtRHS;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.TC2CMergerLHSeqRHS1;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.TC2CMergerLHSgtRHS;
import com.big.tuwien.SmartMatcher.strategy.pso.merge.TR2RMergerLHSgtRHS;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
  TC2CMergerLHSeqRHS1.class,
  TC2CMergerLHSgtRHS.class,
  TC2CMergerLHSltRHS.class,
  TC2CMergerStdImplLHSgtRHS.class,
  TC2CMergerStdImplLHSltRHS.class,
  
  TA2AMergerLHSgtRHS.class,
  TA2AMergerStdImplLHSgtRHS.class,
  
  TR2RMergerLHSgtRHS.class,
  TR2RMergerStdImplLHSgtRHS.class
})
public class AllMergeTestsSuite {
    // the class remains completely empty, 
    // being used only as a holder for the above annotations
}

