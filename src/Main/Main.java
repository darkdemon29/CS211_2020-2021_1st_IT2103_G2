/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Lorcha
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JOptionPane;
import Controls.DatabaseHolder;
import javax.swing.JFrame;
import Controls.KeyboardHolder;

import GUI.Resources.ImageHolder;
import Controls.MouseHolder;
import States.Ingame;
import States.SelectLevelMenu;
import States.Menu;
import States.State;
import States.ScoreboardMenu;
import java.sql.Connection;
import States.SettingsMenu;
public class Main extends JFrame implements Runnable{
	
	public static final int HEIGHT = 600;
        public static final int WIDTH = 800;
	private final Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60;
	private final double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	
	private Ingame ingame;
	private SelectLevelMenu selectlevelMenu;
	private Menu menu;
        public ScoreboardMenu scoreBoardMenu;
        public SettingsMenu settingsMenu;
	private final DatabaseHolder dbHolder;
	private final KeyboardHolder keyBoardHolder;
	private final MouseHolder mouseHolder;
        public static String playername;
	
	public Main()
	{
		setTitle("Match Box");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		canvas = new Canvas();
		keyBoardHolder = new KeyboardHolder();
		mouseHolder = new MouseHolder();
                dbHolder = new DatabaseHolder();
		
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
		addMouseMotionListener(mouseHolder);
		addMouseListener(mouseHolder);
		canvas.addMouseListener(mouseHolder);
		canvas.addMouseMotionListener(mouseHolder);
		canvas.addKeyListener(keyBoardHolder);
		setVisible(true);
	}

	public static void main(String[] args) {
                playername = JOptionPane.showInputDialog(null, "Enter Name:","MatchBox Game", JOptionPane.INFORMATION_MESSAGE);
		new Main().start();            
	}
	
	private void update(){
		if(State.currentState instanceof Ingame)
			keyBoardHolder.update();

		if(State.currentState != null)
			State.currentState.update();
	}

	private void draw(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//-----------------------
		
		g.setColor(Color.white);
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < Main.WIDTH/Board.TILESIZE + 1; i++)
			for(int j = 0; j < Main.HEIGHT/Board.TILESIZE + 1; j++)
				g.drawImage(ImageHolder.floor2, i*Board.TILESIZE, j*Board.TILESIZE, null);
		
		if(State.currentState != null)
			State.currentState.render(g);
		
		//g.drawString(""+AVERAGEFPS, 10, 20);
		
		//---------------------
		g.dispose();
		bs.show();
	}
	
	private void init()
	{
		ImageHolder.init();
		menu = new Menu(this);
		ingame = new Ingame(this);
		selectlevelMenu = new SelectLevelMenu(this);
                scoreBoardMenu = new ScoreboardMenu(this);
                settingsMenu = new SettingsMenu(this);
		State.currentState = menu;
	}
	
	
	@Override
	public void run() {
		
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		init();
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/TARGETTIME;
			time += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1)
			{		
				update();
				draw();
				delta --;
				frames ++;
			}
			if(time >= 1000000000)
			{
				frames = 0;
				time = 0;
			}
		}
		
		stop();
	}
	
	private void start(){
		
		thread = new Thread(this);
		thread.start();
                dbHolder.openConnection();
		running = true;
	}
	private void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public State getIngame(){
		return ingame;
	}
	public State getSelectLevelMenu(){
		return selectlevelMenu;
	}
        public State getScoreboardMenu(){
                return scoreBoardMenu;
        }
	public State getMenu(){
		return menu;
	}
        public State getSettingsMenu(){
                return settingsMenu;
        }
	
	
}