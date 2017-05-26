package example2;

public class FragmentShader {
	public static final String source =
		  "#version 140\n"
		+ "in vec3 fragColour;"
		+ "out vec3 colour;"
		+ "void main() {"
		+ "    colour = fragColour;"
		+ "}";
}