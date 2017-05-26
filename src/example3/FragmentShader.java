package example3;

public class FragmentShader {
	public static final String source =
		  "#version 140\n"
		+ ""
		+ "in vec3 fragNormal;"
		+ "in vec3 vertexPosition;"
		+ "in vec3 eyeVector;"
		+ ""
		+ "out vec3 colour;"
		+ ""
		+ "void main() {"
		+ "    const vec3 ambient = vec3(0.1, 0.1, 0.1);"
		+ "    const vec3 lightPos = vec3(-1.0, 1.0, 0.0);"
		+ "    const vec3 lightColour = vec3(1.0, 1.0, 0.0);"
		+ "    const int specularCoefficient = 64;"
		+ "    vec3 N = normalize(fragNormal);"
		+ ""
		+ "    vec3 lightVector = normalize(lightPos - vertexPosition);"
		+ "    vec3 diffuse = lightColour * max(0.0, dot(lightVector, N));"
		+ ""
		+ "    vec3 R = reflect(lightVector, N);"
		+ "    vec3 V = normalize(eyeVector);"
		+ "    vec3 specular = vec3(1.0) * pow(dot(R, V), specularCoefficient);"
		+ ""
		+ "    colour = ambient + diffuse + specular;"
		+ "}";
}