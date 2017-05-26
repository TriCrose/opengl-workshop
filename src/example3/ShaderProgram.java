package example3;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public class ShaderProgram {
	private int vertexID, fragmentID, programID;
	public static final int positionLocation = 0;
	public static final int normalLocation = 1;
	
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
		glBindAttribLocation(programID, positionLocation, "position");
		glBindAttribLocation(programID, normalLocation, "normal");
		
		glLinkProgram(programID);
		glDetachShader(programID, vertexID);
		glDetachShader(programID, fragmentID);
	}
	
	public void use() {
		glUseProgram(programID);
	}
	
	public void setModel(Matrix4f model) {
		FloatBuffer temp = BufferUtils.createFloatBuffer(16);
		model.get(temp);		// TODO: test the other way
		glUniformMatrix4fv(glGetUniformLocation(programID, "model"), false, temp);
	}
	
	public void setView(Matrix4f view) {
		FloatBuffer temp = BufferUtils.createFloatBuffer(16);
		view.get(temp);		// TODO: test the other way
		glUniformMatrix4fv(glGetUniformLocation(programID, "view"), false, temp);
	}
	
	public void setProjection(Matrix4f proj) {
		FloatBuffer temp = BufferUtils.createFloatBuffer(16);
		proj.get(temp);		// TODO: test the other way
		glUniformMatrix4fv(glGetUniformLocation(programID, "proj"), false, temp);
	}
	
	public void delete() {
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);
		glDeleteProgram(programID);
	}
}