package dev.deejai.rain.entity;

import java.util.Random;

import dev.deejai.rain.level.Level;

public abstract class Entity
{
	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update()
	{
	}
	
	public void remove()
	{	// remove from level
		removed = true;
	}
	
	public boolean isRemoved()
	{
		return removed;
	}
}
