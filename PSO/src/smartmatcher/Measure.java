package smartmatcher;

public interface Measure<T> {
	public double sim(T t1, T t2);
}
