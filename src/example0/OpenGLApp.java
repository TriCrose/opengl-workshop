package example0;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class OpenGLApp {
	public static void main(String[] args) {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit()) throw new IllegalStateException("Failed to initialise GLFW");
		
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		long window = glfwCreateWindow(1024, 768, "OpenGL Application", NULL, NULL);
		if (window == NULL) throw new RuntimeException("Failed to create window");
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glfwSwapInterval(1);
		glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
			glfwPollEvents();
			glfwSwapBuffers(window);
		}
		
		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
}