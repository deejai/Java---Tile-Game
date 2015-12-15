package dev.deejai.rain.entity.mob;

import dev.deejai.rain.input.Keyboard;
import dev.deejai.rain.level.Level;
import dev.deejai.rain.graphics.Sprite;

public class Player extends Mob
{
	private Keyboard input;
	
	public Player( int x, int y, Keyboard input, Level level )
	{
		this.x = x;
		this.y = y;
		this.input = input;
		this.level = level;
		this.sprite = Sprite.spr_Player;
		this.animated = true;
	}
	
	public void automove()
	{		
		int xa=0, ya=0;
		if( input.up    ) ya--;
		if( input.right ) xa++;
		if( input.down  ) ya++;
		if( input.left  ) xa--;
		
		this.move(xa, ya);
	}
}
