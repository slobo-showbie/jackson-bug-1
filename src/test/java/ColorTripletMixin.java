import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.*;
import java.awt.Color;

@JsonAutoDetect(
	getterVisibility=Visibility.NONE,
	fieldVisibility=Visibility.NONE,
	isGetterVisibility= Visibility.NONE,
	creatorVisibility=Visibility.NONE,
	setterVisibility=Visibility.NONE)
public abstract class ColorTripletMixin extends Color {
	@JsonCreator public Color ColorMixinFactory(
		@JsonProperty("Red") int r,
		@JsonProperty("Green") int g,
		@JsonProperty("Blue") int b,
		@JsonProperty("Alpha") int a) {
		return new Color(r, g, b);
	}
	@JsonCreator public ColorTripletMixin(
		@JsonProperty("Red") int r,
		@JsonProperty("Green") int g,
		@JsonProperty("Blue") int b,
		@JsonProperty("Alpha") int a) {
		super(r, g, b, a);
	}

	@JsonProperty("Red") public int getRed() { return super.getRed(); };
	@JsonProperty("Green") public int getGreen() { return super.getRed(); };
	@JsonProperty("Blue") public int getBlue() { return super.getRed(); };
	@JsonProperty("Alpha") public int getAlpha() { return super.getRed(); };
}
