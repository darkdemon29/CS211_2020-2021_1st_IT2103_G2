/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import java.awt.Graphics;

import Main.Main;
import Main.Board;

public class Ingame extends State{
	
	private Board board;
	
	public Ingame(Main main) {
		super(main);
	}
	
	@Override
	public void update() {
		board.update();
	}

	@Override
	public void render(Graphics g) {
		board.render(g);
	}
	
	public void setLevel(Board board){
		this.board = board;
	}
	
}
