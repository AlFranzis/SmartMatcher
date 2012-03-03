
(:~

 : --------------------------------
 : The SReuse XQuery Function Library
 : --------------------------------

 : Copyright (C) 2010 Alex Hoermandinger
 : @version 1.0
 :) 
 
module namespace  sreusex = "http://www.big.tuwien.ac.at/smart_matcher/sreuse" ;

(: import functx functions :) 
import module namespace functx= "http://www.functx.com" at "functx.xq" ; 


(:~
 :Returns if the sequences have any common values
 : @author Alex Hoermandinger
 : @param   $seq1 first sequence
 : @param   $seq1 second sequence
 :)
declare function sreusex:have-common-value
 	( $seq1 as xs:anyAtomicType*,
 	$seq2 as xs:anyAtomicType* ) as xs:boolean {
 		some $v in $seq1
 		satisfies(functx:is-value-in-sequence($v, $seq2))	
 };
 