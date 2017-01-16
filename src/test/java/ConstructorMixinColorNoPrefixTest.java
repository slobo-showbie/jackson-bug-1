import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.*;

import org.junit.*;

import static org.junit.Assert.*;

import java.awt.Color;


public class ConstructorMixinColorNoPrefixTest {
	@Test
	public void main() throws Exception {
		ObjectMapper om = new ObjectMapper();
		om.addMixIn(Color.class, ColorTripletMixin.class);

		Outer o = outer(new Color(5, 2, 3, 255));

		String ser = om.writeValueAsString(o);
		//String ser = "{\"pRed\":5,\"pGreen\":2,\"pBlue\":3,\"pAlpha\":255}";
		System.out.println("Serialized = " + ser);

		Outer o2 = om.readValue(ser, Outer.class);
		assertEquals(o.i.getRed(), o2.i.getRed());
	}
	static Outer outer(Color i) {
		Outer o = new Outer();
		o.i = i;
		return o;
	}
	static class Outer {
		@JsonUnwrapped(/*prefix="p"*/)
		public Color i;
	}
}
