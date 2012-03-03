/**
 * 
 */
package com.big.tuwien.SmartMatcher.strategy.pso.merge;

import com.big.tuwien.SmartMatcher.Element;
import com.big.tuwien.SmartMatcher.strategy.pso.C1;
import com.big.tuwien.SmartMatcher.strategy.pso.C2;
import com.big.tuwien.SmartMatcher.strategy.pso.Context;
import com.big.tuwien.SmartMatcher.strategy.pso.Correspondence;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOA2A;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOC2C;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOMapping;
import com.big.tuwien.SmartMatcher.strategy.pso.PSOR2R;
import com.big.tuwien.SmartMatcher.strategy.pso.PSORuntimeException;
import com.big.tuwien.SmartMatcher.strategy.pso.ReplacerUtil.Side;

public class NotMapped {

	public static <T extends Correspondence> T createNotMapped(Context cxt,
													Side side, Element lhs) {
		if(cxt instanceof PSOMapping) {
			return (T) new NotC2CMapped((PSOMapping)cxt, side, lhs);
		} else if(cxt instanceof C1<?>) {
			return (T) new NotA2AMapped((C1<PSOC2C>)cxt, side, lhs);
		} else if(cxt instanceof C2<?>) {
			return (T) new NotR2RMapped((C2<PSOC2C>)cxt, side, lhs);
		} else
			throw new PSORuntimeException("Not implemented for context type "
											+ cxt);
	}
	
	public static class NotC2CMapped extends PSOC2C {
		private Side side;
		private Element e;
		
		public NotC2CMapped(PSOMapping context, Side side, Element e) {
			super(context, null, null);
			this.e = e;
			this.side = side;
			if(side.equals(Side.LHS()))
				this.lhs = e;
			else if(side.equals(Side.RHS()))
				this.rhs = e;
			else throw new PSORuntimeException("Unknown side " + side);
		}
		
		@Override
		public boolean equals(Object other) {
			if(this == other) return true;
			if(!(other instanceof NotC2CMapped)) return false;
			NotC2CMapped that = (NotC2CMapped) other;
			return side.equals(that.side) 
				&& e.equals(that.e);
		}
		
		
		@Override
		public int hashCode() {
			return (97 * NotC2CMapped.class.hashCode() 
					+ 51 * side.hashCode() + 93 * e.hashCode());
		}

		
		@Override
		public void destroy() {}

		
		@Override
		public Class<? extends PSOC2C> getType() {
			return NotC2CMapped.class;
		}
	}
	
	
	public static class NotA2AMapped extends PSOA2A {
		private Side side;
		private Element e;
		
		public NotA2AMapped(C1<PSOC2C> context, Side side, Element e) {
			super(context, null, null);
			this.e = e;
			this.side = side;
			if(side.equals(Side.LHS()))
				this.lhs = e;
			else if(side.equals(Side.RHS()))
				this.rhs = e;
			else throw new PSORuntimeException("Unknown side " + side);
		}
		
		@Override
		public boolean equals(Object other) {
			if(this == other) return true;
			if(!(other instanceof NotA2AMapped)) return false;
			NotA2AMapped that = (NotA2AMapped) other;
			return side.equals(that.side) 
					&& e.equals(that.e);
		}
		
		
		@Override
		public int hashCode() {
			return (97 * NotA2AMapped.class.hashCode() 
					+ 51 * side.hashCode() + 93 * e.hashCode());
		}

		
		@Override
		public void destroy() {}

		
		@Override
		public Class<? extends PSOA2A> getType() {
			return NotA2AMapped.class;
		}
		
	}
	
	
	public static class NotR2RMapped extends PSOR2R {
		private Side side;
		private Element e;
		
		public NotR2RMapped(C2<PSOC2C> context, Side side, Element e) {
			super(context, null, null);
			this.e = e;
			this.side = side;
			if(side.equals(Side.LHS()))
				this.lhs = e;
			else if(side.equals(Side.RHS()))
				this.rhs = e;
			else throw new PSORuntimeException("Unknown side " + side);
		}
		
		@Override
		public boolean equals(Object other) {
			if(this == other) return true;
			if(!(other instanceof NotR2RMapped)) return false;
			NotR2RMapped that = (NotR2RMapped) other;
			return side.equals(that.side) 
				&& e.equals(that.e);
		}
		
		
		@Override
		public int hashCode() {
			return (97 * NotR2RMapped.class.hashCode() 
					+ 51 * side.hashCode() + 93 * e.hashCode());
		}

		
		@Override
		public void destroy() {}

		
		@Override
		public Class<? extends PSOR2R> getType() {
			return NotR2RMapped.class;
		}
		
	}
	
}