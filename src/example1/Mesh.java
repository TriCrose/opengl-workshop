package example1;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

public class Mesh {
	private int vao, vbo;
	private final int vertexCount;
	
	public Mesh(float[] vertices) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
		buffer.put(vertices).flip();
		vertexCount = vertices.length / 2;
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(ShaderProgram.positionLocation, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(ShaderProgram.positionLocation);
	}
	
	public void draw() {
		glBindVertexArray(vao);
		glDrawArrays(GL_TRIANGLES, 0, vertexCount);
	}
	
	public void delete() {
		glDeleteBuffers(vbo);
		glDeleteVertexArrays(vao);
	}
}