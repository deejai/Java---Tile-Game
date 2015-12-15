package dev.deejai.rain.entity.mob;

import dev.deejai.rain.entity.Entity;
import dev.deejai.rain.graphics.Screen;
import dev.deejai.rain.graphics.Sprite;

public abstract class Mob extends Entity
{
	
	public Sprite[][] sprite;
	
	protected final static int UP    = 0;
	protected final static int RIGHT = 1;
	protected final static int DOWN  = 2;
	protected final static int LEFT  = 3;
	
	protected int frame = 0;
	protected final int fr_wait = 15;
	
	protected int dir = DOWN;
	protected boolean animated;
	protected boolean moving = false;
	
	public void move( int xa, int ya )
	{
		moving = true;
		if( xa == 0 ){
			     if( ya < 0 ) dir = UP;
			else if( ya > 0 ) dir = DOWN;
			else moving = false;
		}
		else if( ya == 0 ){
			     if( xa < 0 ) dir = LEFT;
			else if( xa > 0 ) dir = RIGHT;
		}

		
		boolean[] xyMove = collision(xa, ya);
		
		if( xyMove[0] )
			xa = 0;
		if( xyMove[1] )
			ya = 0;
		
		x += xa;
		y += ya;
	}
	
	public void update()
	{
		if(animated) animate();
		automove();
	}
	
	public void animate()
	{
		if(moving){
			if( frame == 0 )
				frame = fr_wait;
			else
				frame = (frame+1)&0xFFFFFF;
		}
		else
			frame = 0;
	}
	
	public void automove(){}
	
	public void render( Screen screen )
	{		
		int spr_frame = (frame/fr_wait)%4;
		
		     if( this.dir == UP    ) screen.render( x, y, sprite[0][spr_frame] );
		else if( this.dir == DOWN  ) screen.render( x, y, sprite[1][spr_frame] );
		else if( this.dir == LEFT  ) screen.render( x, y, sprite[2][spr_frame] );
		else if( this.dir == RIGHT ) screen.render( x, y, sprite[3][spr_frame] );
	}
	
	protected boolean[] collision( int xd, int yd )
	{
		boolean[] xyMove = { false, false };
		
		if( xd==0 ){
			if( yd==0 )
				// Skip logic if no movement attempted
				return xyMove;
		}
		else {
		// xCheck
			// check up xd
			if( level.getTile( (x+8+(xd*4))>>4, (y+12+3)>>4 ).solid() ){
				xyMove[0] = true;
			}
			// check down xd
			else if( level.getTile( (x+8+(xd*4))>>4, (y+12-3)>>4 ).solid() ){
				xyMove[0] = true;
			}
		}
		// yCheck
		if( yd != 0 ){
			// check right yd
			if( level.getTile( (x+8+3)>>4, (y+12+(yd*4))>>4 ).solid() ){
				xyMove[1] = true;
			}
			// check left yd
			else if( level.getTile( (x+8-3)>>4, (y+12+(yd*4))>>4 ).solid() ){
				xyMove[1] = true;
			}
		}
		
		// diagCheck
		// PREVENTS CUTTING INTO CORNER OF SOLID TILES
//		if( xyMove[0] && xyMove[1] )
//			if( level.getTile( (x+8+(xd*4))>>4, (y+12+(yd*4))>>4 ).solid() )
//				xyMove[0] = xyMove[1] = true;
				
		return xyMove;
	}
}
