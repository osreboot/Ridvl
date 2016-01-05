package com.osreboot.ridhvl.painter;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.osreboot.ridhvl.action.HvlAction3;
import com.osreboot.ridhvl.external.HvlVerifier;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlRenderFrame {

	private static boolean hasPushed = false;

	private HvlAction3<Integer, Integer, Integer> actionInitialize = ACTION_INITIALIZE_DEFAULT;
	
	public static final HvlAction3<Integer, Integer, Integer> ACTION_INITIALIZE_DEFAULT = new HvlAction3<Integer, Integer, Integer>(){
		@Override
		public void run(Integer textureID, Integer width, Integer height){
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_INT, (ByteBuffer)null);
			EXTFramebufferObject.glFramebufferTexture2DEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_COLOR_ATTACHMENT0_EXT, GL11.GL_TEXTURE_2D, textureID, 0);
		}
	};
	
	@SuppressWarnings("deprecation")
	public static void setCurrentRenderFrame(HvlRenderFrame renderFrame){//TODO clean this up
		if(hasPushed){
			GL11.glPopAttrib();
			//GL11.glPopMatrix();
			hasPushed = false;
		}
		if(renderFrame != null){
			hasPushed = true;
			//GL11.glPushMatrix();
			//GL11.glTranslatef(-renderFrame.getX(), (Display.getHeight() - renderFrame.getHeight() - renderFrame.getY()), 0);
			EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, renderFrame.getID());
			GL11.glPushAttrib(GL11.GL_VIEWPORT_BIT);
			GL11.glViewport(0, 0, renderFrame.getWidth(), renderFrame.getHeight());
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			HvlPainter2D.hvlForceRefresh();
		}else{
			EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, 0);
		}
	}

	@SuppressWarnings("deprecation")
	public static void setCurrentRenderFrame(HvlRenderFrame renderFrame, boolean clear){//TODO clean this up
		if(hasPushed){
			GL11.glPopAttrib();
			//GL11.glPopMatrix();
			hasPushed = false;
		}
		if(renderFrame != null){
			hasPushed = true;
			//GL11.glPushMatrix();
			//GL11.glTranslatef(-renderFrame.getX(), (Display.getHeight() - renderFrame.getHeight() - renderFrame.getY()), 0);
			EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, renderFrame.getID());
			GL11.glPushAttrib(GL11.GL_VIEWPORT_BIT);
			GL11.glViewport(0, 0, renderFrame.getWidth(), renderFrame.getHeight());
			if(clear){
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				HvlPainter2D.hvlForceRefresh();
			}
		}else{
			EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, 0);
		}
	}

	private int frameID, textureID, width, height, x = 0, y = 0;

	public HvlRenderFrame(int widthArg, int heightArg) throws FBOUnsupportedException{
		if(!HvlVerifier.VFR_FBOENABLED.isValid()) throw new FBOUnsupportedException();
		width = widthArg;
		height = heightArg;
		frameID = EXTFramebufferObject.glGenFramebuffersEXT();
		textureID = GL11.glGenTextures();

		setCurrentRenderFrame(this);

		if(actionInitialize != null) actionInitialize.run(textureID, width, height);

		setCurrentRenderFrame(null);
	}

	public HvlRenderFrame(int xArg, int yArg, int widthArg, int heightArg) throws FBOUnsupportedException{
		if(!HvlVerifier.VFR_FBOENABLED.isValid()) throw new FBOUnsupportedException();
		x = xArg;
		y = yArg;
		width = widthArg;
		height = heightArg;
		frameID = EXTFramebufferObject.glGenFramebuffersEXT();
		textureID = GL11.glGenTextures();

		setCurrentRenderFrame(this);

		if(actionInitialize != null) actionInitialize.run(textureID, width, height);

		setCurrentRenderFrame(null);
	}

	public void bindTexture(int texture){
		GL13.glActiveTexture(texture);
		glBindTexture(GL_TEXTURE_2D, textureID);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
	}

	public int getID(){
		return frameID;
	}

	public int getTextureID(){
		return textureID;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public int getX(){
		return x;
	}

	public void setX(int xArg){
		x = xArg;
	}

	public int getY(){
		return y;
	}

	public void setY(int yArg){
		y = yArg;
	}
	
	@SuppressWarnings("serial")
	public static class FBOUnsupportedException extends Exception{}

}
