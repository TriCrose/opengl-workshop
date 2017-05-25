package simple;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.joml.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.FloatBuffer;
import java.nio.file.Files;

public class OpenGLApp {
	public static void main(String[] args) {
		GLFWErrorCallback.createPrint(System.err).set();								// So GLFW can write errors to the console
		if (!glfwInit()) throw new IllegalStateException("Failed to initialise GLFW");	// Initialize
		
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		long window = glfwCreateWindow(1024, 768, "OpenGL Application", NULL, NULL);
		if (window == NULL) throw new RuntimeException("Failed to create window");
		
		glfwMakeContextCurrent(window);			// Set current OpenGL context
		GL.createCapabilities();				// TODO: research
		
		glfwSwapInterval(1);					// V-sync
		glEnable(GL_DEPTH_TEST);
		
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);	// This is what the colour buffer is set to on clear
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		// Vertex data
		float[] vertices = {
			0.0f, 0.0f,
			0.5f, 0.0f,
			0.5f, 0.5f
		};
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
		buffer.put(vertices).flip();
		
		int vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		
		// Shaders
		int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertexShader, VertexShader.source);
		glCompileShader(vertexShader);
		
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentShader, FragmentShader.source);
		glCompileShader(fragmentShader);
		
		// Shader program
		int shaderProgram = glCreateProgram();
		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glBindFragDataLocation(shaderProgram, 0, "colour");
		glLinkProgram(shaderProgram);
		glUseProgram(shaderProgram);
		
		// Bind "position" shader variable
		int positionLocation = glGetAttribLocation(shaderProgram, "position");
		glVertexAttribPointer(positionLocation, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(positionLocation);
		
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			glDrawArrays(GL_TRIANGLES, 0, 3);
			
			glfwPollEvents();
			glfwSwapBuffers(window);
		}
		
		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
}