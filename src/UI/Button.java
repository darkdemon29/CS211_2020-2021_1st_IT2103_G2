/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import GUI.Resources.FontHolder;
import Controls.MouseHolder;

public class Button{
	
	private String text;
	private int x, y;
	private FontMetrics fm;
	private Rectangle bounds;
	private boolean hovering;
	private Click click;
	private Font font;
	
	public Button(String text, int x, int y, Click click, Font font){
		this.text = text;
		this.x = x;
		this.y = y;
		this.click = click;
		hovering = false;
		this.font = font;
	}
	
	public void update(){		
		if(bounds != null && bounds.contains(MouseHolder.x, MouseHolder.y)){
			hovering = true;
			if(MouseHolder.click1)
				click.onClick();
		}else
			hovering = false;
	}
	
	public void render(Graphics g){
		g.setFont(font);
		fm = g.getFontMetrics();
		if(hovering)
			FontHolder.drawString(g, text, x, y, true, Color.WHITE);
		else
			FontHolder.drawString(g, text, x, y, true, Color.BLACK);
		bounds = new Rectangle(x - fm.stringWidth(text)/2, y - fm.getHeight()/2, fm.stringWidth(text), fm.getHeight());
	}
	
}
