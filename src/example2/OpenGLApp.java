package example2;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class OpenGLApp {
	private static int WIDTH = 1024;
	private static int HEIGHT = 768;
	
	public static void main(String[] args) {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit()) throw new IllegalStateException("Failed to initialise GLFW");
		
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		long window = glfwCreateWindow(1024, 768, "OpenGL Application", NULL, NULL);
		if (window == NULL) throw new RuntimeException("Failed to create window");
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glfwSwapInterval(1);
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		ShaderProgram shader = new ShaderProgram(VertexShader.source, FragmentShader.source);
		Mesh triangle = new Mesh(new float[] {
			-1.0f, 0.0f, -1.0f,
			-1.0f, 0.0f, 1.0f,
			1.0f, 0.0f, 1.0f,
			
			-1.0f, 0.0f, -1.0f,
			1.0f, 0.0f, 1.0f,
			1.0f, 0.0f, -1.0f
		}, new float[] {
			1.0f, 0.0f, 0.0f,
			1.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 1.0f,
			
			1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f,
			0.0f, 1.0f, 1.0f
		}, new int[] {
			0, 1, 2, 3, 4, 5
		});
		
		long t = System.currentTimeMillis();
		Matrix4f model = new Matrix4f();
		Matrix4f view = new Matrix4f().lookAt(
			new Vector3f(0.0f, 2.0f, 2.0f),
			new Vector3f(0.0f, 0.0f, 0.0f),
			new Vector3f(0.0f, 1.0f, 0.0f)
		);
		Matrix4f proj = new Matrix4f().perspective((float) Math.toRadians(90.0f), (float) WIDTH / (float) HEIGHT, 0.1f, 10.0f);
		
		while (!glfwWindowShouldClose(window)) {
			long dt = System.currentTimeMillis() - t;
			t += dt;
			model.rotate(dt * 0.001f, 0.0f, 1.0f, 0.0f);
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			shader.setModel(model);
			shader.setView(view);
			shader.setProjection(proj);
			shader.use();
			
			triangle.draw();
			
			glfwPollEvents();
			glfwSwapBuffers(window);
			try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		triangle.delete();
		shader.delete();
		
		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
}