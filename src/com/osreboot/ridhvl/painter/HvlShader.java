package com.osreboot.ridhvl.painter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL20;

public class HvlShader {

	public static final String PATH_SHADER_DEFAULT = "shader\\", SUFFIX_VERTEX = ".hvlvt", SUFFIX_FRAGMENT = ".hvlfg";
	
	public static final String
	VERTEX_DEFAULT 							= PATH_SHADER_DEFAULT + "Default" + SUFFIX_VERTEX,
	FRAGMENT_SIMPLE_NEGATIVE				= PATH_SHADER_DEFAULT + "SimpleNegative" + SUFFIX_FRAGMENT,
	FRAGMENT_SIMPLE_GRAYSCALE				= PATH_SHADER_DEFAULT + "SimpleGrayscale" + SUFFIX_FRAGMENT,
	FRAGMENT_QUADRUPLE_DISPLACEMENT_BLUR	= PATH_SHADER_DEFAULT + "QuadrupleDisplacementBlur" + SUFFIX_FRAGMENT,
	FRAGMENT_HIGHLIGHTER					= PATH_SHADER_DEFAULT + "Highlighter" + SUFFIX_FRAGMENT,
	FRAGMENT_NEWSPAPER						= PATH_SHADER_DEFAULT + "Newspaper" + SUFFIX_FRAGMENT;

	public static void setCurrentShader(HvlShader shader){
		if(shader != null){
			ARBShaderObjects.glUseProgramObjectARB(shader.getID());
			int loc = GL20.glGetUniformLocation(shader.getID(), "texture1");
			GL20.glUniform1i(loc, 0);
		}else ARBShaderObjects.glUseProgramObjectARB(0);
	}

	private int shaderID;

	public HvlShader(String vertexArg, String fragmentArg){
		int vertexShader = ARBShaderObjects.glCreateShaderObjectARB(ARBVertexShader.GL_VERTEX_SHADER_ARB);
		ARBShaderObjects.glShaderSourceARB(vertexShader, readFile(vertexArg));
		ARBShaderObjects.glCompileShaderARB(vertexShader);

		int fragmentShader = ARBShaderObjects.glCreateShaderObjectARB(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
		ARBShaderObjects.glShaderSourceARB(fragmentShader, readFile(fragmentArg));
		ARBShaderObjects.glCompileShaderARB(fragmentShader);

		shaderID = ARBShaderObjects.glCreateProgramObjectARB();
		ARBShaderObjects.glAttachObjectARB(shaderID, vertexShader);
		ARBShaderObjects.glAttachObjectARB(shaderID, fragmentShader);
		ARBShaderObjects.glLinkProgramARB(shaderID);
		ARBShaderObjects.glValidateProgramARB(shaderID);
	}

	public void sendFloat(String key, float value){
		int loc = GL20.glGetUniformLocation(shaderID, key);
		GL20.glUniform1f(loc, value);
	}
	
	public void sendTexture(String key, int value){
		int loc = GL20.glGetUniformLocation(shaderID, key);
		GL20.glUniform1i(loc, value);
	}
	
	public void sendIntArray(String key, int[] value){
		IntBuffer buffer = BufferUtils.createIntBuffer(value.length);
		buffer.put(value);
		buffer.rewind();
		int loc = GL20.glGetUniformLocation(shaderID, key);
		GL20.glUniform1(loc, buffer);
	}
	
	private String readFile(String file){
		StringBuilder builder = new StringBuilder();
		try{
			FileInputStream input = new FileInputStream(file);
			BufferedReader reader = null;
			try{
				reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				String line;
				while((line = reader.readLine()) != null) builder.append(line).append('\n');
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(reader != null) reader.close();
				input.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return builder.toString();
	}

	public int getID(){
		return shaderID;
	}

}