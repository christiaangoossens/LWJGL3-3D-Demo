package nl.christiaangoossens.lwjgl3;

import org.lwjgl.glfw.GLFWErrorCallback;

public class GLFWErrorHandler extends GLFWErrorCallback {
	public void invoke(int error, long description) {
		System.out.println(error);
		System.out.println(description);
	}
}
