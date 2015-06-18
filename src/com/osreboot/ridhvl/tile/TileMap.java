package com.osreboot.ridhvl.tile;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.newdawn.slick.opengl.Texture;

public class TileMap {

	public class TileMapInfo {
		public TileMapInfo(Texture texture, int tileWidth, int tileHeight) {
			super();
			this.texture = texture;
			this.tileWidth = tileWidth;
			this.tileHeight = tileHeight;
		}

		public Texture texture;
		public int tileWidth, tileHeight;
	}

	private TileMapInfo info;
	private Tile[] tiles;
	private float tileWidth, tileHeight;
	private int mapWidth, mapHeight;
	private float x, y;

	public TileMap(Texture tArg, int tilesAcrossArg, int tilesTallArg,
			int mapWidthArg, int mapHeightArg, float xArg, float yArg,
			float tileWidthArg, float tileHeightArg) {
		this.info = new TileMapInfo(tArg, tilesAcrossArg, tilesTallArg);
		this.mapWidth = mapWidthArg;
		this.mapHeight = mapHeightArg;
		this.tileWidth = tileWidthArg;
		this.tileHeight = tileHeightArg;
		this.x = xArg;
		this.y = yArg;
		this.tiles = new Tile[mapWidth * mapHeight];
	}

	public void draw(float delta) {
		for (int currentX = 0; currentX < mapWidth; currentX++) {
			for (int currentY = 0; currentY < mapHeight; currentY++) {
				Tile current = tiles[mapWidth * currentY + currentX];
				if (current == null)
					continue;

				current.draw(info, x + (tileWidth * currentX), y
						+ (tileHeight * currentY), tileWidth, tileHeight, delta);
			}
		}
	}

	public Tile getTile(int xArg, int yArg) {
		return tiles[yArg * mapWidth + xArg];
	}

	public void setTile(int xArg, int yArg, Tile tile) {
		tiles[yArg * mapWidth + xArg] = tile;
	}

	public void fill(Tile tile) {
		for (int currentX = 0; currentX < mapWidth; currentX++) {
			for (int currentY = 0; currentY < mapHeight; currentY++) {
				setTile(currentX, currentY, tile);
			}
		}
	}

	public TileMapInfo getInfo() {
		return info;
	}

	public void setInfo(TileMapInfo info) {
		this.info = info;
	}

	public float getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(float tileWidth) {
		this.tileWidth = tileWidth;
	}

	public float getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(float tileHeight) {
		this.tileHeight = tileHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
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

	public static String save(TileMap map) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("[TileMap %d, %d, %d, %d] {%n",
				map.getInfo().tileWidth, map.getInfo().tileHeight,
				map.getMapWidth(), map.getMapHeight()));
		for (int currentX = 0; currentX < map.getMapWidth(); currentX++) {
			for (int currentY = 0; currentY < map.getMapHeight(); currentY++) {
				Tile tile = map.getTile(currentX, currentY);
				if (tile == null)
					continue;

				sb.append(String.format("[%s,%d,%d] {%n", tile.getClass()
						.getName(), currentX, currentY));
				try {
					sb.append((String) tile.getClass()
							.getMethod("save", tile.getClass())
							.invoke(null, tile));
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					e.printStackTrace();
				}
				sb.append(String.format("%n}"));
			}
		}

		sb.append(String.format("%n}"));

		return sb.toString();
	}

	public static TileMap load(String inArg, Texture texArg, float xArg,
			float yArg, float tileWidthArg, float tileHeightArg) {
		String[] tileMapHeaderArgs = inArg.split(System.lineSeparator())[0]
				.replaceFirst(Pattern.quote("[TileMap "), "")
				.replace("] {", "").split(",");

		int tilesAcross = Integer.parseInt(tileMapHeaderArgs[0].trim());
		int tilesTall = Integer.parseInt(tileMapHeaderArgs[1].trim());
		int mapWidth = Integer.parseInt(tileMapHeaderArgs[2].trim());
		int mapHeight = Integer.parseInt(tileMapHeaderArgs[3].trim());
		TileMap toReturn = new TileMap(texArg, tilesAcross, tilesTall,
				mapWidth, mapHeight, xArg, yArg, tileWidthArg, tileHeightArg);

		Pattern p = Pattern.compile("\\[(\\S+),(\\d+),(\\d+)\\] \\{([^}]*)\\}");
		Matcher m = p.matcher(inArg);
		while (m.find()) {
			int coordX = Integer.parseInt(m.group(2));
			int coordY = Integer.parseInt(m.group(3));

			try {
				Class<?> tileClass = Class.forName(m.group(1));
				Tile created = (Tile) tileClass.getMethod("load", String.class).invoke(null,
						m.group(4));
				toReturn.setTile(coordX, coordY, created);
				
			} catch (ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return toReturn;
	}

}
