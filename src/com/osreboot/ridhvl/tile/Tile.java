package com.osreboot.ridhvl.tile;

public abstract class Tile {	
	public Tile() {
		
	}
	
	public abstract void draw(TileMap.TileMapInfo info, float x, float y, float width, float height, float delta);

	// NOTE: This isn't "overridable", but to use it
	// it MUST be called "save" and take a tile parameter
	// of the containing class type (it's called with reflection).
	public static String save(Tile tile)
	{
		// NOTE: this should ONLY include the inside content, not the header.
		// The header is autogenerated and is kinda important to be correct.
		return "";
	}

	// NOTE: This is similar to 
	public static Tile load(String text)
	{
		return null; // Can't have a "Tile" by itself.
	}
}
