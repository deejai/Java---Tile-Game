package dev.deejai.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import dev.deejai.rain.graphics.Screen;

public class Level
{
	protected final int[] tile_rgb = new int[256];{
		tile_rgb[  0] = 0xff64a200; // grass (green)
		tile_rgb[  1] = 0xffffc642; // flower (orange)
		tile_rgb[  2] = 0xff717171; // rock (gray)
		tile_rgb[  3] = 0xff8fb7d7; // water (l.blue)
		tile_rgb[  4] = 0xff7e421f; // brick (reddish brown)
		tile_rgb[  5] = 0xffbc41b8; // sm.flowers ( purple )
		tile_rgb[  6] = 0xff000000; // x
		tile_rgb[  7] = 0xff000000; // x
		tile_rgb[  8] = 0xff000000; // x
		tile_rgb[  9] = 0xff000000; // x
		tile_rgb[255] = 0x00000000;  // ERROR (black)
	}
	
	protected final int NUM_TILES = tile_rgb.length;
	
	protected int width, height;
	protected int[] tiles;
	
	protected String lv_title;
	
	public Level( String lv_title, int width, int height )
	{
		this.lv_title = lv_title;
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
	}
	
	public Level( String lv_title, String path )
	{
		this.lv_title = lv_title;
		loadLevel(path);
	}
	
	protected void loadLevel( String path )
	{
		try {
			BufferedImage image = ImageIO.read(Level_Spawn.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			System.out.printf("Loading level %s ... [width: %d, height%d]\n", lv_title, width, height);
			tiles = new int[width*height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch( IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		int slot;
		for( int x=0; x<width; x++ ){
			for( int y=0; y<height; y++ ){
				slot = x +y*width;
				tiles[slot] = rgbToID(tiles[slot]);
			}
		}
	}
	
	public void update()
	{
		
	}
	
	protected void time()
	{
		
	}
	
	public void render( int xScroll, int yScroll, Screen screen )
	{
		screen.setOffset(xScroll, yScroll);
		int x0 =  xScroll >> 4;
		int y0 =  yScroll >> 4;
		int x1 = (xScroll + screen.width ) >> 4;
		int y1 = (yScroll + screen.height) >> 4;
				
		for( int y=y0; y<y1+1; y++ ){
			if( y<0 || y >= height ){
				for( int x=x0; x<x1+1; x++ )
					Tile.tile_void.render(x, y, screen);
			}
			else{
			for( int x=x0; x<x1+1; x++ ){
				if( x<0 || x >= height ){
					Tile.tile_void.render(x, y, screen);
				} else {
					//System.out.println("idToTile(tiles[" + (x+y*width) + "])");
					idToTile(tiles[x + y*width]).render(x, y, screen);
				}
			}}
		}
	}

	public Tile getTile( int x, int y )
	{
		System.out.println("Get tile: " + x + ", " + y);
		if( x<0 || y<0 || x>=width || y>=height )
			return Tile.tile_void;

		return idToTile( tiles[x + y*width] );
	}
	
	public void setTile( int x, int y, int id )
	{
		System.out.println("Get tile: " + x + ", " + y);
		if( x<0 || y<0 || x>=width || y>=height )
			return;

		tiles[x + y*width] = id;
	}
	
	public Tile idToTile( int tile_id )
	{
		//System.out.println("Tile ID: " + tile_id);
		switch( tile_id ){
			case  0: return Tile.tile_grass;
			case  1: return Tile.tile_flower;
			case  2: return Tile.tile_rock;
			case  3: return Tile.tile_water;
			case  4: return Tile.tile_brick;
			case  5: return Tile.tile_smflowers;
			default: return Tile.tile_void;
		}
	}
	
	public int rgbToID( int rgb )
	{
		for( int i=0; i<NUM_TILES; i++ ){
			if( rgb == tile_rgb[i] )
				return i;
		}
		// TILE NOT FOUND
		return 255;
	}
}
