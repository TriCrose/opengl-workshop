package example1;

public class VertexShader {
	public static final String source =
		  "#version 140\n"
		+ "in vec2 position;"
		+ "in vec3 vertColour;"
		+ "out vec3 fragColour;"
		+ "void main() {"
		+ "    fragColour = vertColour;"
		+ "    gl_Position = vec4(position, 0.0, 1.0);"
		+ "}";
}