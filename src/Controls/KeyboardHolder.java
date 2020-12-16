/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyboardHolder implements KeyListener{
	private boolean[] keys;
	public static boolean UP, LEFT, RIGHT, DOWN, RESTART;
	
	public KeyboardHolder(){
		keys = new boolean[256];
		UP = false;
		DOWN = false;
		RIGHT = false;
		LEFT = false;
                RESTART = false;
	}
	
	public void update(){
               UP = keys[KeyEvent.VK_UP];
               LEFT = keys[KeyEvent.VK_LEFT];
               RIGHT = keys[KeyEvent.VK_RIGHT];
               DOWN = keys[KeyEvent.VK_DOWN];
               RESTART = keys[KeyEvent.VK_R];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}


