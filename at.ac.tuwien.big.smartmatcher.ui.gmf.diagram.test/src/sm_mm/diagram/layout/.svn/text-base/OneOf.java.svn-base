package sm_mm.diagram.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


public class OneOf<T> extends TypeSafeMatcher<T> {
	private final Iterable<Matcher<? super T>> matchers;
	
	public OneOf(Iterable<Matcher<? super T>> matchers) {
		this.matchers = matchers;
	}
	
	
	@Override
	public boolean matchesSafely(Object number) {
		boolean oneOf = false;
		for(Matcher<? super T> m : matchers) {
			if(m.matches(number)) {
				if(oneOf) 
					return false;
				else 
					oneOf = true;
			}
		}
		return oneOf;
	}

	
	public void describeTo(Description description) {
		description.appendText("XOR");
	}

	
	public static <T> Matcher<T> oneOf(Iterable<Matcher<? super T>> matchers) {
		return new OneOf<T>(matchers);
	}
	
	

	public static <T> Matcher<T> oneOf(Matcher<T> first, Matcher<T> second) {
		List<Matcher<? super T>> matchers = new ArrayList<Matcher<? super T>>();
        matchers.add(first);
        matchers.add(second);
        return new OneOf<T>(matchers);
	}
	
	
	public static <T> Matcher<T> oneOf(Matcher<? super T>... matchers) {
		return new OneOf<T>(Arrays.asList(matchers));
	}

}

