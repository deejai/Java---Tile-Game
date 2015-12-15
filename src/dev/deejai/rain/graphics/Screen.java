package dev.deejai.rain.graphics;

import java.util.Random;

public class Screen
{
    public int width, height;
    public int[] pixels;
    private final int mapsize = 8; 
    public int[] tiles = new int[mapsize * mapsize];
    private int xOffset, yOffset;
    
    private Random random = new Random();

    public Screen( int width, int height )
    {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        
        for( int i=0; i<mapsize*mapsize; i++ ){
        	tiles[i] = random.nextInt(0xffffff);
        }
    }
    
    public void clear()
    {
    	for( int i=0; i<pixels.length; i++ )
    		pixels[i] = 0;
    }
    
    public void render( int x0, int y0, Sprite sprite )
    {
    	x0 -= xOffset;
    	y0 -= yOffset;
    	int size = sprite.SIZE;
    	int px_val;
    	for( int y=y0; y<(y0 + size); y++ ){
    		if( y < 0 || y >= height ) continue;
    		for( int x=x0; x<(x0 + size); x++ ){
    			if( x<0 || x>= width ) continue;
    			if( (px_val = sprite.pixels[(x-x0) + (y-y0)*size]) != -524033 )
    				pixels[x + y*width] = px_val;
    		}
    	}
    }
    
    public void setOffset( int xOffset, int yOffset )
    {
    	this.xOffset = xOffset;
    	this.yOffset = yOffset;
    }
}