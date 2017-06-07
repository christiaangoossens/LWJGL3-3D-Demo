package nl.christiaangoossens.lwjgl3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

public class Extractor {

	public static void main(String[] args) {
		
		/*
		 * EXTRACT NATIVE FILES FROM THIS JAR FILE
		 */
		
		try {
			
			// Create a native folder
			File folder = new File("native");
			if(!folder.exists()) { 
				folder.mkdir();
			}
			
			extractFile("native/glfw.dll");
			extractFile("native/gflw32.dll");
			extractFile("native/jemalloc.dll");
			extractFile("native/jemalloc32.dll");
			extractFile("native/jemalloc32.dll");
			extractFile("native/libglfw.dylib");
			extractFile("native/libglfw.so");
			extractFile("native/libglfw32.so");
			extractFile("native/libjemalloc.dylib");
			extractFile("native/libjemalloc.so");
			extractFile("native/libjemalloc32.so");
			extractFile("native/liblwjgl.dylib");
			extractFile("native/liblwjgl.so");
			extractFile("native/liblwjgl32.so");
			extractFile("native/libopenal.dylib");
			extractFile("native/libopenal.so");
			extractFile("native/libopenal32.so");
			extractFile("native/lwjgl.dll");
			extractFile("native/lwjgl32.dll");
			extractFile("native/OpenAL.dll");
			extractFile("native/OpenAL32.dll");
			
			// All files good to go
			Main.main(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "An error occured during extraction of the required native files.");
			e.printStackTrace();
		}
	}
	
	private static void extractFile(String name) throws IOException
    {
        File target = new File(name);
        if (target.exists())
            return;

        FileOutputStream out = new FileOutputStream(target);
        ClassLoader cl = Extractor.class.getClassLoader();
        InputStream in = cl.getResourceAsStream(name);

        byte[] buf = new byte[8*1024];
        int len;
        while((len = in.read(buf)) != -1)
        {
            out.write(buf,0,len);
        }
        out.close();
        in.close();
    }

}
