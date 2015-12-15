package dev.deejai.rain.graphics;

public class Sprite
{
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	// Landscape tiles
	public static Sprite spr_tile_grass = new Sprite( 16, 0, 0, SpriteSheet.tiles );
	public static Sprite spr_tile_flower = new Sprite( 16, 1, 0, SpriteSheet.tiles );
	public static Sprite spr_tile_rock = new Sprite( 16, 2, 0, SpriteSheet.tiles );
	public static Sprite spr_tile_water = new Sprite( 16, 4, 0, SpriteSheet.tiles );
	public static Sprite spr_tile_brick = new Sprite( 16, 5, 0, SpriteSheet.tiles );
	public static Sprite spr_tile_smflowers = new Sprite( 16, 3, 0, SpriteSheet.tiles );
	public static Sprite spr_tile_void = new Sprite( 16, 0x0A43D5 );

	
	public static int s = 8;
	// Player sprites
	public static Sprite[][] spr_Player = new Sprite[][]{
	{
		new Sprite( 16, 0+s, 1, SpriteSheet.tiles ),
		new Sprite( 16, 0+s, 2, SpriteSheet.tiles ),
		new Sprite( 16, 0+s, 1, SpriteSheet.tiles ),
		new Sprite( 16, 0+s, 3, SpriteSheet.tiles )
	},{
		new Sprite( 16, 2+s, 1, SpriteSheet.tiles ),
		new Sprite( 16, 2+s, 2, SpriteSheet.tiles ),
		new Sprite( 16, 2+s, 1, SpriteSheet.tiles ),
		new Sprite( 16, 2+s, 3, SpriteSheet.tiles )
	},{
		new Sprite( 16, 3+s, 1, SpriteSheet.tiles ),
		new Sprite( 16, 3+s, 2, SpriteSheet.tiles ),
		new Sprite( 16, 3+s, 1, SpriteSheet.tiles ),
		new Sprite( 16, 3+s, 3, SpriteSheet.tiles )
	},{
		new Sprite( 16, 1+s, 1, SpriteSheet.tiles ),
		new Sprite( 16, 1+s, 2, SpriteSheet.tiles ),
		new Sprite( 16, 1+s, 1, SpriteSheet.tiles ),
		new Sprite( 16, 1+s, 3, SpriteSheet.tiles )
	} };
	
	public Sprite( int size, int x, int y, SpriteSheet sheet )
	{
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite( int size, int color )
	{
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		for( int i=0; i<SIZE*SIZE; i++ )
			pixels[i] = color;
	}
	
	private void load()
	{
		// System.out.println(SIZE + ", " + sheet.SIZE);
		for( int y=0; y<SIZE; y++ )
			for( int x=0; x<SIZE; x++ )
				pixels[x+y*SIZE] = sheet.pixels[(x+this.x) + (y+this.y)*sheet.SIZE];
	}
}
