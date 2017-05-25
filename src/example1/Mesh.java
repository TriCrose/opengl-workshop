package example1;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {
	private int vao, indexBuffer;
	private int positionVBO, colourVBO;
	private int vertexCount;
	
	private static int loadToBuffer(float[] data, int attribLocation, int size) {
		FloatBuffer temp = BufferUtils.createFloatBuffer(data.length);
		temp.put(data).flip();
		
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, temp, GL_STATIC_DRAW);
		glVertexAttribPointer(attribLocation, size, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(attribLocation);
		
		return vbo;
	}
	
	public Mesh(float[] vertices, float[] colours, int[] indices) {
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		positionVBO = loadToBuffer(vertices, ShaderProgram.positionLocation, 2);
		colourVBO = loadToBuffer(colours, ShaderProgram.colourLocation, 3);
		
		IntBuffer temp = BufferUtils.createIntBuffer(indices.length);
		temp.put(indices).flip();
		vertexCount = indices.length;
		
		indexBuffer = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, temp, GL_STATIC_DRAW);
	}
	
	public void draw() {
		glBindVertexArray(vao);
		glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
	}
	
	public void delete() {
		glDeleteBuffers(indexBuffer);
		glDeleteBuffers(positionVBO);
		glDeleteBuffers(colourVBO);
		glDeleteVertexArrays(vao);
	}
}