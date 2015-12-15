package dev.deejai.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet
{
	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/test_spritesheet.png", 256);
	
	public SpriteSheet(String path, int size)
	{
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load()
	{
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			image.getRGB(0, 0, SIZE, SIZE, pixels, 0, SIZE);
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

}
