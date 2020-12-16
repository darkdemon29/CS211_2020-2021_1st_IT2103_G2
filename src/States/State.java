/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import java.awt.Graphics;

import Main.Main;

public abstract class State {
	
	public static State currentState = null;
	protected Main main;
	
	public State(Main main){
		this.main = main;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	
}
