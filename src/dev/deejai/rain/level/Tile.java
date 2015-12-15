package dev.deejai.rain.level;

import dev.deejai.rain.graphics.Screen;
import dev.deejai.rain.graphics.Sprite;

public class Tile
{
	public int x, y;
	public Sprite sprite;

	public static Tile tile_grass = new Tile_Floor(Sprite.spr_tile_grass);
	public static Tile tile_flower = new Tile_Floor(Sprite.spr_tile_flower);
	public static Tile tile_rock = new Tile_Block(Sprite.spr_tile_rock);
	public static Tile tile_water = new Tile_Block(Sprite.spr_tile_water);
	public static Tile tile_brick = new Tile_Block(Sprite.spr_tile_brick);
	public static Tile tile_smflowers = new Tile_Floor(Sprite.spr_tile_smflowers);
	public static Tile tile_void = new Tile_Void(Sprite.spr_tile_void);
	
	public Tile( Sprite sprite )
	{
		
		this.sprite = sprite;
	}
	
	public Tile( Sprite[] sprite )
	{
		
	}
	
	public void render( int x, int y, Screen screen )
	{
	}
	
	public boolean solid()
	{
		return false;
	}
}
