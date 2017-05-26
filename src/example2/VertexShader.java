package example2;

public class VertexShader {
	public static final String source =
		  "#version 140\n"
		+ "in vec3 position;"
		+ "in vec3 vertColour;"
		+ "out vec3 fragColour;"
		+ ""
		+ "uniform mat4 model;"
		+ "uniform mat4 view;"
		+ "uniform mat4 proj;"
		+ ""
		+ "void main() {"
		+ "    fragColour = vertColour;"
		+ "    gl_Position = proj * view * model * vec4(position, 1.0);"
		+ "}";
}