package example3;

public class VertexShader {
	public static final String source =
		  "#version 140\n"
		+ ""
		+ "in vec3 position;"
		+ "in vec3 normal;"
		+ ""
		+ "out vec3 fragNormal;"
		+ "out vec3 vertexPosition;"
		+ "out vec3 eyeVector;"
		+ ""
		+ "uniform mat4 model;"
		+ "uniform mat4 view;"
		+ "uniform mat4 proj;"
		+ ""
		+ "void main() {"
		+ "    fragNormal = (model * vec4(normal, 0.0)).xyz;"
		+ "    vertexPosition = (model * vec4(position, 1.0)).xyz;"
		+ "    eyeVector = -(view * model * vec4(position, 1.0)).xyz;"
		+ "    gl_Position = proj * view * model * vec4(position, 1.0);"
		+ "}";
}