package com.big.tuwien.smartmatcher.strategy.sreuse;

public interface IdGenerator<T extends XMLAny> {
	public String generateId(T xmlAny);
}
