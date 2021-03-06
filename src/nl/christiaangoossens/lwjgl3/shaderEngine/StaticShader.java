package nl.christiaangoossens.lwjgl3.shaderEngine;

import nl.christiaangoossens.lwjgl3.entities.Camera;
import nl.christiaangoossens.lwjgl3.utils.Maths;
import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocations() {
		// Let's use the Matrix
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");	
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");		
		location_viewMatrix = super.getUniformLocation("viewMatrix");			
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}

	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}

	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
}
