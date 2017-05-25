package example1;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class ShaderProgram {
	private int vertexID, fragmentID, programID;
	public static final int positionLocation = 0;
	
	public ShaderProgram(String vertSource, String fragSource) {
		vertexID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexID, vertSource);
		glCompileShader(vertexID);
		String error = glGetShaderInfoLog(vertexID);
		if (!error.isEmpty()) throw new RuntimeException("Vertex shader failed to compile: " + error);
		
		fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentID, fragSource);
		glCompileShader(fragmentID);
		error = glGetShaderInfoLog(fragmentID);
		if (!error.isEmpty()) throw new RuntimeException("Fragment shader failed to compile: " + error);
		
		programID = glCreateProgram();
		glAttachShader(programID, vertexID);
		glAttachShader(programID, fragmentID);
		glBindFragDataLocation(programID, 0, "colour");
		glLinkProgram(programID);
		
		glDetachShader(programID, vertexID);
		glDetachShader(programID, fragmentID);
		
		glBindAttribLocation(programID, positionLocation, "position");
	}
	
	public void use() {
		glUseProgram(programID);
	}
	
	public void delete() {
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);
		glDeleteProgram(programID);
	}
}