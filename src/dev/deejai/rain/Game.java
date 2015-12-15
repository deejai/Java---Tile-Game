package dev.deejai.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import dev.deejai.rain.entity.mob.Player;
import dev.deejai.rain.graphics.Screen;
import dev.deejai.rain.input.Keyboard;
import dev.deejai.rain.input.Mouse;
import dev.deejai.rain.level.*;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1L;

    public static int width = 256;
    public static int height = (width*9) / 16;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private Mouse mouse;
    private boolean running = false;

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game()
    {
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        //level = new Level_Random(64, 64);
        level = new Level_Spawn();
        mouse = new Mouse();
        player = new Player(40,40,key,level);
        
        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public synchronized void start()
    {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop()
    {
        running = false;
        try{
        	 thread.join();
        } catch( InterruptedException e ){ 
                e.printStackTrace();
        }
    }

    public void run()
    {    	
    	double timer = System.currentTimeMillis() - 1000;
    	double timeNow;
    	final double msPerFrame = 1000.0 / 60.0;
    	
    	requestFocus();
        while( running )
        {
            if( (timeNow = System.currentTimeMillis()) - timer >= msPerFrame ){
            	timer = timeNow;
	            update();
            }
            render();
        }
    }

    
    public void update()
    {
        key.update();
        player.update();
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if( bs == null ){
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        int xMid = (screen.width >> 1)-8;
        int yMid = (screen.height >> 1)-8;
        level.render(player.x-xMid, player.y-yMid, screen);
        player.render(screen);
        
        for( int i=0; i<pixels.length; i++ ){
        	pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor( Color.BLACK );
        g.fillRect( 0, 0, getWidth(), getHeight() );
        g.setColor( Color.PINK );
        g.drawImage( image,  0, 0, getWidth(), getHeight(), null );
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", 0, 50));
        g.dispose();
        bs.show();
    }

    public static void main( String[] args )
    {
        Game game = new Game();

        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}