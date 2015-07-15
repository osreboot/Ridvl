package com.osreboot.ridhvl.particle.collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlColorUtil;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;

public class HvlSimpleParticle extends HvlParticle {

	private Color startColor, endColor;
	private Texture texture;
	private float xVel, yVel;
	private float xVelDecay, yVelDecay;
	private float rot;
	private float rotVel;
	private float rotVelDecay;
	private float baseWidth, baseHeight;
	private float scale;
	private float scaleDecay;
	private float lifetime;
	
	public HvlSimpleParticle(float xArg, float yArg,
			HvlParticleSystem parentArg, Color startColor, Color endColor,
			Texture texture, float xVel, float yVel, float xVelDecay,
			float yVelDecay, float rot, float rotVel, float rotVelDecay,
			float baseWidth, float baseHeight, float scale, float scaleDecay,
			float lifetime) {
		super(xArg, yArg, parentArg);
		this.startColor = startColor;
		this.endColor = endColor;
		this.texture = texture;
		this.xVel = xVel;
		this.yVel = yVel;
		this.xVelDecay = xVelDecay;
		this.yVelDecay = yVelDecay;
		this.rot = rot;
		this.rotVel = rotVel;
		this.rotVelDecay = rotVelDecay;
		this.baseWidth = baseWidth;
		this.baseHeight = baseHeight;
		this.scale = scale;
		this.scaleDecay = scaleDecay;
		this.lifetime = lifetime;
	}

	@Override
	public void update(float delta)
	{
		super.update(delta);
		
		setX(getX() + (xVel * delta));
		setY(getY() + (yVel * delta));
		
		xVel *= Math.pow(Math.E, delta * xVelDecay);
		yVel *= Math.pow(Math.E, delta * yVelDecay);
		
		rot += rotVel * delta;
		rotVel *= Math.pow(Math.E, delta * rotVelDecay);
		
		scale *= Math.pow(Math.E, delta * scaleDecay);
	}
	
	@Override
	public void draw(float delta)
	{
		super.draw(delta);
		
		HvlPainter2D.hvlRotate(getX(), getY(), rot);
		
		HvlPainter2D.hvlDrawQuad(getX() - (baseWidth * scale * 0.5f),
				getY() - (baseHeight * scale * 0.5f),
				baseWidth * scale, baseHeight * scale, texture, HvlColorUtil.lerpColor(startColor, endColor, timeAlive / lifetime));
		
		HvlPainter2D.hvlResetRotation();
	}

	@Override
	public boolean shouldBeDestroyed() {
		return timeAlive > lifetime;
	}

	public final Color getStartColor() {
		return startColor;
	}

	public final void setStartColor(Color startColor) {
		this.startColor = startColor;
	}

	public final Color getEndColor() {
		return endColor;
	}

	public final void setEndColor(Color endColor) {
		this.endColor = endColor;
	}

	public final Texture getTexture() {
		return texture;
	}

	public final void setTexture(Texture texture) {
		this.texture = texture;
	}

	public final float getxVel() {
		return xVel;
	}

	public final void setxVel(float xVel) {
		this.xVel = xVel;
	}

	public final float getyVel() {
		return yVel;
	}

	public final void setyVel(float yVel) {
		this.yVel = yVel;
	}

	public final float getxVelDecay() {
		return xVelDecay;
	}

	public final void setxVelDecay(float xVelDecay) {
		this.xVelDecay = xVelDecay;
	}

	public final float getyVelDecay() {
		return yVelDecay;
	}

	public final void setyVelDecay(float yVelDecay) {
		this.yVelDecay = yVelDecay;
	}

	public final float getRot() {
		return rot;
	}

	public final void setRot(float rot) {
		this.rot = rot;
	}

	public final float getRotVel() {
		return rotVel;
	}

	public final void setRotVel(float rotVel) {
		this.rotVel = rotVel;
	}

	public final float getRotVelDecay() {
		return rotVelDecay;
	}

	public final void setRotVelDecay(float rotVelDecay) {
		this.rotVelDecay = rotVelDecay;
	}

	public final float getBaseWidth() {
		return baseWidth;
	}

	public final void setBaseWidth(float baseWidth) {
		this.baseWidth = baseWidth;
	}

	public final float getBaseHeight() {
		return baseHeight;
	}

	public final void setBaseHeight(float baseHeight) {
		this.baseHeight = baseHeight;
	}

	public final float getScale() {
		return scale;
	}

	public final void setScale(float scale) {
		this.scale = scale;
	}

	public final float getScaleDecay() {
		return scaleDecay;
	}

	public final void setScaleDecay(float scaleDecay) {
		this.scaleDecay = scaleDecay;
	}

	public final float getLifetime() {
		return lifetime;
	}

	public final void setLifetime(float lifetime) {
		this.lifetime = lifetime;
	}

}
