package nl.christiaangoossens.lwjgl3;
import nl.christiaangoossens.lwjgl3.entities.Camera;
import nl.christiaangoossens.lwjgl3.models.Block;
import nl.christiaangoossens.lwjgl3.renderEngine.DisplayManager;
import nl.christiaangoossens.lwjgl3.renderEngine.Renderer;
import nl.christiaangoossens.lwjgl3.shaderEngine.StaticShader;
import org.lwjgl.*;
import org.lwjgl.util.vector.Vector3f;

import nl.christiaangoossens.lwjgl3.renderEngine.Loader;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

	// We need to strongly reference callback instances.
	private GLFWErrorHandler errorCallback;
    
    // PROGRAM VARS
    private String version = "1.2.3";
     
    public void run() {
    	// Log Versions
    	System.out.println("LWJGL3 3D Demo v" + version);
        System.out.println("This project is using LWJGL version " + Version.getVersion() + " and GLFW version " + glfwGetVersionString());
        
        // Setup an error callback.     
        glfwSetErrorCallback(errorCallback);
        
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        try {
        	if ( glfwInit() != GLFW_TRUE )
                throw new IllegalStateException("Unable to initialize GLFW");    
        } catch(IllegalStateException e) {
        	DisplayManager.MessageBox("Initalization Error", e.getMessage(), "error");
        }        
        
        // Create the Display        
        DisplayManager.createDisplay(); 
        
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        
        Block block = new Block(loader, new Vector3f(0,0,-5));
        Block block2 = new Block(loader, new Vector3f(2,0,-5));
        
        Block[] allObjects = {block, block2};
		
		Camera camera = new Camera();
		
		while(glfwWindowShouldClose(DisplayManager.window) == GLFW_FALSE) {
			camera.move();
			
			// Start the shader
			shader.start();
			shader.loadViewMatrix(camera);
			
			// TODO:: Maybe do this a bit better :0
			renderer.prepare(shader);
			
			// Update all objects, TODO: Turn this into an array
			for(Block object : allObjects) {
				object.update(renderer, shader);
			}

			// Stop the shader
			shader.stop();
			DisplayManager.updateDisplay();
    	}
        
        loader.cleanUp();
        shader.cleanUp();
        DisplayManager.closeDisplay();
    }
 
    public static void main(String[] args) {
    	System.setProperty("org.lwjgl.librarypath", "native");
    	try {
    		new Main().run();
    	} catch(RuntimeException e) {
    		DisplayManager.MessageBox("Runtime Error", e.getMessage(), "error");
    	} catch(ExceptionInInitializerError e) {
    		e.printStackTrace();
    		DisplayManager.MessageBox("Error", "Your computer encountred an error while loading the simulator:\n'" + e.getCause().getMessage() + "'.\n\nMore information about this error can be gathered in the console.", "error");
    		System.exit(0);
    	}
    }

}
