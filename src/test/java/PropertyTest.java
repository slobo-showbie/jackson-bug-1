import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.*;

import org.junit.*;

import static org.junit.Assert.*;


public class PropertyTest {
	@Test
	public void main() throws Exception {
		ObjectMapper om = new ObjectMapper();
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
		Inner i = new Inner();
		i.a = a;
		return i;
	}
	static class Outer {
		@JsonUnwrapped(prefix="p")
		public Inner i;
	}
	static class Inner {
		@JsonProperty("A")
		public int a ;
	}
}
