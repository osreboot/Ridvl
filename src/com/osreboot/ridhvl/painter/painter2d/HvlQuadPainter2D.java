package com.osreboot.ridhvl.painter.painter2d;


import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.painter.HvlAnimatedTextureArray;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.HvlRenderFrame;

class HvlQuadPainter2D {
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(x, y, xl, yl, 0, 0, t.getWidth(), t.getHeight());
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(x, y, xl, yl, 0, 0, t.getWidth(), t.getHeight());
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2);
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2);
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		glColor4f(1, 1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, renderFrame.getTextureID());
		constructTexturedQuad(x, y + yl, xl, -yl, 0, 0, 1, 1);
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x, y, xl, yl, 0, 0, texture.getCurrentTexture().getWidth(), texture.getCurrentTexture().getHeight());
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x, y, xl, yl, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x, y, xl, yl, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, 0, 0, t.getWidth(), t.getHeight());
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, 0, 0, t.getWidth(), t.getHeight());
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, uvx1, uvy1, uvx2, uvy2);
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, uvx1, uvy1, uvx2, uvy2);
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		glColor4f(1, 1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, renderFrame.getTextureID());
		constructTexturedQuad(x - (xl/2), y + yl - (yl/2), xl, -yl, 0, 0, 1, 1);
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, 0, 0, texture.getCurrentTexture().getWidth(), texture.getCurrentTexture().getHeight());
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	private static void constructTexturedQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2){
		if(HvlPainter2D.TEXMAGBLUR.isEnabled()){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		}else{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		}
		glBegin(GL_QUADS);
		glTexCoord2f(uvx1, uvy1);
		glVertex2f(x, y);
		glTexCoord2f(uvx2, uvy1);
		glVertex2f(x + xl, y);
		glTexCoord2f(uvx2, uvy2);
		glVertex2f(x + xl, y + yl);
		glTexCoord2f(uvx1, uvy2);
		glVertex2f(x, y + yl);
		glEnd();
	}

}
