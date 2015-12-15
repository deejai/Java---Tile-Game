package dev.deejai.rain.level;

import java.util.Random;

public class Level_Random extends Level
{
	private static final Random random = new Random();
	
	public Level_Random( int width, int height )
	{
		super( "RANDOOO!", width, height );
		generateLevel();
	}
	
	protected void generateLevel()
	{
		for( int y=0; y<height; y++ ){
			for( int x=0; x<height; x++ ){
				tiles[x+y*width] = random.nextInt(NUM_TILES-1);
			}
		}
	}
	
	
}
