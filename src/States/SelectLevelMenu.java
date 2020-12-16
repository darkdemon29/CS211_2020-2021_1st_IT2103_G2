/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import GUI.Resources.ImageHolder;
import GUI.Resources.FontHolder;
import Controls.MouseHolder;
import Main.Main;
import Main.Board;
import Controls.DatabasaeCRUD;
import States.ScoreboardMenu;
import UI.Button;
import UI.Click;

public class SelectLevelMenu extends State{
	private final int DOUBLETILESIZE = 70;
	public final Board[] levels = new Board[30];
	private final int xOffset = (Main.WIDTH - DOUBLETILESIZE*5)/2;
	private final int yOffset = (Main.HEIGHT - DOUBLETILESIZE*2)/2;
	private final Button back;
        public int overallscore = 500;
        public DatabasaeCRUD DCRUD = new DatabasaeCRUD();
        
	public SelectLevelMenu(Main main) {
		super(main);
		for(int i = 0; i < levels.length; i++)
			levels[i] = loadLevel("/levels/"+i+".txt");
		
		back = new Button("BACK", Main.WIDTH/2, Main.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				State.currentState = main.getMenu();
			}
			
		}, ImageHolder.font48);	
		
	}
        
	@Override
	public void update(){
		back.update();
	}
	@Override
	public void render(Graphics g){
		back.render(g);
		int counter = 1;
                g.setFont(ImageHolder.font30);
		FontHolder.drawString(g, "CURRENT SCORE: " +overallscore , Main.WIDTH/2, Main.HEIGHT/2 - 250, true, Color.WHITE);
		g.setFont(ImageHolder.font30);
		for(int i = 0; i < 1; i++){
			for(int j = 0; j < 5; j++){
				Rectangle bounds = new Rectangle(xOffset + j*DOUBLETILESIZE,
						yOffset + i*DOUBLETILESIZE, DOUBLETILESIZE, DOUBLETILESIZE);
				if(bounds.contains(MouseHolder.x, MouseHolder.y)){
					if(MouseHolder.click1 && levels[counter-1].isSolved()){
						((Ingame)main.getIngame()).setLevel(levels[counter-1]);
						State.currentState = main.getIngame();
					}
					g.drawImage(ImageHolder.outline2, bounds.x, bounds.y, null);
					if(levels[counter-1].isSolved())
						FontHolder.drawString(g, counter+"", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.YELLOW);
					else
						FontHolder.drawString(g,"?", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
								yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.BLACK);
				}else{
					g.drawImage(ImageHolder.outline, bounds.x, bounds.y, null);
					if(levels[counter-1].isSolved())
						FontHolder.drawString(g, counter+"", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.WHITE);
					else
						FontHolder.drawString(g,"?", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
								yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.BLACK);
				}
				counter ++;
			}
		}
                if(levels[5].isSolved()){
                    DCRUD.create_data(overallscore, main.playername);
                    State.currentState = main.getScoreboardMenu();
                    main.scoreBoardMenu.overallscore = this.overallscore;
                }
		
	}
	private Board loadLevel(String path){
		
		String file = loadFileAsString(path);
		String[] numbers = file.split("\\s+");
		int cols = parseInt(numbers[0]);
		int rows = parseInt(numbers[1]);
		int player_col = parseInt(numbers[2]);
		int player_row = parseInt(numbers[3]);
		int[][] maze = new int[rows][cols];
		for(int row = 0; row < rows; row++)
			for(int col = 0; col < cols; col++)
				maze[row][col] = parseInt(numbers[(col + (row*cols)) + 4]);
		
		return new Board(maze, player_row, player_col, this);
	}
	
	public Board[] getLevels(){return levels;}
	
	
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		try{
		InputStream in = SelectLevelMenu.class.getResourceAsStream(path);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String line;
		while((line = br.readLine()) != null){
			builder.append(line+ "\n");
		}
		br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String number){
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}

}
