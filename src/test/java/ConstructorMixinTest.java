import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.*;

import org.junit.*;

import static org.junit.Assert.*;


public class ConstructorMixinTest {
	@Test
	public void main() throws Exception {
		ObjectMapper om = new ObjectMapper();
		om.addMixIn(Inner.class, InnerMixin.class);
		Outer o = outer(inner(5));
		String ser = om.writeValueAsString(o);
		System.out.println("Serialized = " + ser);
		Outer o2 = om.readValue(ser, Outer.class);
		assertEquals(o.i.a, o2.i.a);
		System.out.println("o2.i.a = " + o2.i.a);
	}
	static Outer outer(Inner i) {
		Outer o = new Outer();
		o.i = i;
		return o;
	}
	static Inner inner(int a) {
		return new Inner(a);
	}
	static class Outer {
		@JsonUnwrapped(prefix="p")
		public Inner i;
	}
	static class Inner {
		private final int a;
		final int anotherValue = 5555;
		public Inner(int a) {
			this.a = a;
		}
		public int getA() {
			return a;
		}
		public int getIrrelevant() {
			return 4444;
		}
	}
	@JsonAutoDetect(
		getterVisibility=Visibility.NONE,
		fieldVisibility=Visibility.NONE,
		isGetterVisibility= Visibility.NONE,
		creatorVisibility=Visibility.NONE,
		setterVisibility=Visibility.NONE)
	static abstract class InnerMixin {
		@JsonCreator public InnerMixin(@JsonProperty("A") int a) {}
		@JsonProperty("A") public abstract int getA();
	}
}
