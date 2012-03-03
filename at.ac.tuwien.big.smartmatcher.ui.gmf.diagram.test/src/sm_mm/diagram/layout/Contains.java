package sm_mm.diagram.layout;

import java.util.Collection;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Contains<T> extends BaseMatcher {
	protected T elem;
	protected Collection<T> c;
	
	public Contains(T elem) {
		this.elem = elem;
	}

	
	@Override
	public void describeTo(Description description) {
		description.appendText("is element in");
	}
	
	
	public static <T> Contains<T> contains(T elem) {
		return new Contains<T>(elem);
	}

	
	public Matcher before(T other) {
		return new BaseMatcher() {

			@Override
			public boolean matches(Object item) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void describeTo(Description description) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

	@Override
	public boolean matches(Object co) {
		c = (Collection<T>) co;
		return c.contains(elem);
	}
	
}
