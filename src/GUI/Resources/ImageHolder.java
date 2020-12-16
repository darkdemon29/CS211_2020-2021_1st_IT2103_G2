/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.GraphicsEnvironment;

import Main.Board;

public class ImageHolder {
	
	public static Image playerLeft, playerBack, playerRight, PlayerFront;
	
	public static Image floor, floor2, wall, boxOn, boxOff, spot, outline, outline2, stone;
	public static Font font80;
	public static Font font48;
	public static Font font30;
	public static Font font22;
	
	public static void init()
	{
		playerLeft = loadImage("/player/left.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerBack = loadImage("/player/back.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		PlayerFront = loadImage("/player/front.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerRight = loadImage("/player/right.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		
		floor = loadImage("/blocks/ground.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		floor2 = loadImage("/blocks/ground2.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		wall = loadImage("/blocks/redBrick.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
                stone = loadImage("/blocks/2.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOn = loadImage("/blocks/boxOn.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOff = loadImage("/blocks/boxOff.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		spot = loadImage("/blocks/spot.png").getScaledInstance(Board.TILESIZE, Board.TILESIZE, BufferedImage.SCALE_DEFAULT);
		outline = loadImage("/blocks/outline.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		outline2 = loadImage("/blocks/outline2.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		font80 = loadFont("res/fonts/square.ttf", 120);
		font48 = loadFont("res/fonts/square.ttf", 48);
		font22 = loadFont("res/fonts/square.ttf", 22);
		font30 = loadFont("res/fonts/square.ttf", 30);
		
	}
	
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(ImageHolder.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Font loadFont(String path, int size){
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e ) {
			e.printStackTrace();
		}
		return null;
	}
	
}
