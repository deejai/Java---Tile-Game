package dev.deejai.rain.level;

import dev.deejai.rain.graphics.Screen;
import dev.deejai.rain.graphics.Sprite;

public class Tile_Block extends Tile
{
	public Tile_Block( Sprite sprite )
	{
		super(sprite);
	}
	
	public void render( int x, int y, Screen screen )
	{
		screen.render( x*16, y*16, this.sprite );
	}
	
	public boolean solid(){
		return true;
	}
}
