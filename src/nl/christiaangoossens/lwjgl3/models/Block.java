package nl.christiaangoossens.lwjgl3.models;

import java.io.IOException;

import nl.christiaangoossens.lwjgl3.entities.Entity;
import nl.christiaangoossens.lwjgl3.renderEngine.Renderer;
import nl.christiaangoossens.lwjgl3.shaderEngine.StaticShader;
import nl.christiaangoossens.lwjgl3.textures.ModelTexture;
import org.lwjgl.util.vector.Vector3f;

import nl.christiaangoossens.lwjgl3.renderEngine.Loader;

public class Block {
	private float[] vertices = {			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,0.5f,-0.5f,		
			
			-0.5f,0.5f,0.5f,	
			-0.5f,-0.5f,0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			0.5f,0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			-0.5f,-0.5f,0.5f,	
			-0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,0.5f,
			-0.5f,0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			0.5f,0.5f,0.5f,
			
			-0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f
			
	};

	private float[] textureCoords = {
			
			0,0,
			0,1,
			1,1,
			1,0,			
			0,0,
			0,1,
			1,1,
			1,0,			
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0

			
	};

	private int[] indices = {
			0,1,3,	
			3,1,2,	
			4,5,7,
			7,5,6,
			8,9,11,
			11,9,10,
			12,13,15,
			15,13,14,	
			16,17,19,
			19,17,18,
			20,21,23,
			23,21,22
	};
	
	public Entity self;
	
	public Block(Loader loader, Vector3f position) {
		RawModel model = loader.loadToVAO(vertices, indices, textureCoords);
		
		ModelTexture texture;
        TexturedModel texturedModel = new TexturedModel(model, null);
        
        self = new Entity(texturedModel, position,0,0,0,1);
        
		try {
			texture = new ModelTexture(loader.loadTexture("texture"));
			texturedModel = new TexturedModel(model, texture);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Renderer renderer, StaticShader shader) {
		renderer.render(self, shader);
	}
	
	public Entity getEntity() {
		return self;
	}
}
