package com.big.tuwien.SmartMatcher.strategy.pso;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.big.tuwien.SmartMatcher.strategy.pso.merge.AllMergeTestsSuite;
import com.big.tuwien.SmartMatcher.strategy.pso.mutate.AllMutatorTestsSuite;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
  TPSOC2C.class,
  TCloner.class,
  TChecker.class,
  TChecker2.class,
  TMappingBuilder.class,
  TMetaModelBuilder.class,
  TQueryPSOMapping.class,
 
  AllMutatorTestsSuite.class,

  AllMergeTestsSuite.class
})
public class AllPSOTestsSuite {
    // the class remains completely empty, 
    // being used only as a holder for the above annotations
}

