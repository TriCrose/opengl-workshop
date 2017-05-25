package simple;
public class VertexShader {
	public static final String source =
		  "#version 140\n"
		+ "in vec2 position;"
		+ "void main() {"
		+ "    gl_Position = vec4(position, 0.0, 1.0);"
		+ "}";
}