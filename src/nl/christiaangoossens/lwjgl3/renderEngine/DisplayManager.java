package nl.christiaangoossens.lwjgl3.renderEngine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import javax.swing.JOptionPane;

import nl.christiaangoossens.lwjgl3.input.KeyboardInput;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

public class DisplayManager {
		
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static long window;
	@SuppressWarnings("unused")
	private static GLFWKeyCallback   keyCallback;
	
	// Method to create the display
	public static void createDisplay() {
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        
		window = glfwCreateWindow(WIDTH, HEIGHT, "LWJGL3 Demo", NULL, NULL);
		if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
		
		glfwSetKeyCallback(window, keyCallback = new KeyboardInput());
        
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (vidmode.width() - WIDTH) / 2,
            (vidmode.height() - HEIGHT) / 2
        );
        
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
        
        // Make the window visible
        glfwShowWindow(window);
        
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        System.out.println("Currently using OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
	}
	
	public static void updateDisplay() {
		int[] dimensions = getDimensions();
		int width = dimensions[0];
		int height = dimensions[1];
		// Display resizing
		GL11.glViewport(0, 0, width, height);

		// Buffer swap
		glfwSwapBuffers(window);
        glfwPollEvents();
	}
	
	public static void closeDisplay() {
		// Terminate GLFW and free the GLFWErrorCallback
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	public static float getAspectRatio() {
		int[] dimensions = getDimensions();
		int width = dimensions[0];
		int height = dimensions[1];
		
		return (float) width/height;		
	}
	
	public static int[] getDimensions() {
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		glfwGetWindowSize(window, w, h);
		int width = w.get(0);
		int height = h.get(0);
		
		return new int[] { width, height };
	}
	
	public static void MessageBox(String title, String message, String type) {
		if (type == "warning") {
			JOptionPane.showMessageDialog(null,
				    message,
				    title,
				    JOptionPane.WARNING_MESSAGE);
		} else if (type == "error") {
			JOptionPane.showMessageDialog(null,
				    message,
				    title,
				    JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,
				    message,
				    title,
				    JOptionPane.PLAIN_MESSAGE);		
		}
	}

}
