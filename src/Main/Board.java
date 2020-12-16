/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import java.awt.Graphics;
import java.awt.Image;

import GUI.Resources.ImageHolder;
import Controls.KeyboardHolder;
import GUI.Resources.FontHolder;
import States.SelectLevelMenu;
import States.State;
import UI.Button;
import UI.Click;
import java.awt.Color;

public class Board {
	
	public static final int TILESIZE = 48;
	private final int[][] maze;
	private final int[][] copy;
	private int player_row, player_col, score;
	private Image texture;
        public int l1score, l2score, l3score, l4score, l5score;
	private int xOffset, yOffset;
	private long time, lastTime;
	private final int DELAY = 150;
	private Button restart, back;
	private boolean solved;
	private int plaStartRow, plaStartCol;
	private final SelectLevelMenu selectlevelMenu;
	public static int ID = 0;
	private final int id;
        
	public Board(int[][] maze, int player_row, int player_col, SelectLevelMenu selectlevelMenu){
		this.selectlevelMenu = selectlevelMenu;
		this.maze = maze;
		ID ++;
		id = ID;
		copy = new int[maze.length][maze[0].length];
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++)
				copy[row][col] = maze[row][col];
                l1score = 20;
                l2score = 120;
                l3score = 70;
                l4score = 80;
                l5score = 70;
                switch(id){
                    case 1:
                        score = l1score;
                        break;
                    case 2:
                        score = l2score;
                        break;
                    case 3:
                        score = l3score;
                        break;
                    case 4:
                        score = l4score;
                        break;
                    case 5:
                        score = l5score;
                        break;
                }
		plaStartRow = player_row;
		plaStartCol = player_col;
		this.player_row = player_row;
		this.player_col = player_col;
		if(ID == 1)
			solved = true;
		else
			solved = false;
		xOffset = (Main.WIDTH - maze[0].length*TILESIZE)/2;
		yOffset = (Main.HEIGHT - maze.length*TILESIZE)/2;
		texture = ImageHolder.PlayerFront;
		restart = new Button("RESTART", 100, Main.HEIGHT/2, new Click(){

			@Override
			public void onClick() {
				reset();
                               
				
			}},
			ImageHolder.font30);
                
		back = new Button("BACK", Main.WIDTH - 100, Main.HEIGHT/2, new Click(){

			@Override
			public void onClick() {
				State.currentState = selectlevelMenu;
				
			}},
			ImageHolder.font30);
		
		time = 0;
		lastTime = System.currentTimeMillis();
		}
	}
        

        
	private void reset(){
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				maze[row][col] = copy[row][col];
		
		player_row = plaStartRow;
		player_col = plaStartCol;
		texture = ImageHolder.PlayerFront;
	}
	
	public void update(){
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(KeyboardHolder.UP && time > DELAY){
			move(-1, 0);
			texture = ImageHolder.playerBack;
                        score--;
                        selectlevelMenu.overallscore--;
		}
		if(KeyboardHolder.LEFT && time > DELAY){
			move(0, -1);
			texture = ImageHolder.playerLeft;
                        score--;
                        selectlevelMenu.overallscore--;
		}
		if(KeyboardHolder.DOWN && time > DELAY){
			move(1, 0);
			texture = ImageHolder.PlayerFront;
                        score--;
                        selectlevelMenu.overallscore--;
		}
		if(KeyboardHolder.RIGHT && time > DELAY){
			move(0, 1);
			texture = ImageHolder.playerRight;
                        score--;
                        selectlevelMenu.overallscore--;
		}
		if(KeyboardHolder.RESTART && time > DELAY){
			reset();
		}
		restart.update();
		back.update();
                
		// check answer
		
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				if(maze[row][col] == 2)
					return;
		
		selectlevelMenu.getLevels()[id].setSolved(true);
		State.currentState = selectlevelMenu;
               
                
		
	}
	
	private void move(int row, int col){
		if(maze[player_row + row][player_col + col] != 1){
			if(maze[player_row + row][player_col + col] == 2 || maze[player_row + row][player_col + col] == 4){
				if(maze[player_row + row*2][player_col + col*2] == 1 ||
						maze[player_row + row*2][player_col + col*2] == 2 ||
						maze[player_row + row*2][player_col + col*2] == 4)
					return;
                                
				if(maze[player_row + row][player_col + col] == 4){
					maze[player_row + row][player_col + col] = 3;			
					if(maze[player_row + row*2][player_col + col*2] == 3)
						maze[player_row + row*2][player_col + col*2] = 4;
					else
						maze[player_row + row*2][player_col + col*2] = 2;
				}else{
					maze[player_row + row][player_col + col] = 0;
					if(maze[player_row + row*2][player_col + col*2] == 3)
						maze[player_row + row*2][player_col + col*2] = 4;
					else
						maze[player_row + row*2][player_col + col*2] = 2;
					
				}
				
				
			}
			player_row += row;
			player_col += col;
		}
		time = 0;
	}
	
	public void render(Graphics g){
		restart.render(g);
		back.render(g);
                g.setFont(ImageHolder.font30);
		FontHolder.drawString(g, "Steps Remaining: "+ score, Main.WIDTH/2, Main.HEIGHT/2 - 250, true, Color.WHITE);
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++){
				g.drawImage(ImageHolder.floor, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 1)
					g.drawImage(ImageHolder.wall, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 2)
					g.drawImage(ImageHolder.boxOff, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 3)
					g.drawImage(ImageHolder.spot, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 4)
					g.drawImage(ImageHolder.boxOn, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
//                                if(maze[row][col] == 5)
//					g.drawImage(ImageHolder.stone, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
			}
		}
		
		g.drawImage(texture, xOffset + player_col*TILESIZE, yOffset + player_row*TILESIZE, null);
		
	}
        
	public boolean isSolved(){return solved;}
	public void setSolved(boolean bool){solved = bool;}

   
}