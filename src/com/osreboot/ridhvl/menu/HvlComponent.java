package com.osreboot.ridhvl.menu;

import org.lwjgl.input.Mouse;

public abstract class HvlComponent {
	
	private float x, y, width, height, heightInversion; 
	
	public HvlComponent(float xArg, float yArg, float wArg, float hArg, float heightInversionArg)
	{
		x = xArg;
		y = yArg;
		width = wArg;
		height = hArg;
		heightInversion = heightInversionArg;
	}
	
	public void update(float delta) {}
	public void draw(float delta) {}

	public final boolean isBeingPressed(int buttonArg){//TODO account for HvlDisplayMode
		return Mouse.isInsideWindow() && Mouse.isButtonDown(buttonArg) && Mouse.getX() > getX() && getHeightInversion() - Mouse.getY() > getY() && Mouse.getX() < getX() + getWidth() && getHeightInversion() - Mouse.getY() < getY() + getHeight();
	}

	public final boolean isHovering(){//TODO account for HvlDisplayMode
		return Mouse.isInsideWindow() && Mouse.getX() > getX() && getHeightInversion() - Mouse.getY() > getY() && Mouse.getX() < getX() + getWidth() && getHeightInversion() - Mouse.getY() < getY() + getHeight();
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getHeightInversion() {
		return heightInversion;
	}

	public void setHeightInversion(float heightInversion) {
		this.heightInversion = heightInversion;
	}
	
	
}