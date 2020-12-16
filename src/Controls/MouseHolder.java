/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHolder extends MouseAdapter{
	
	public static int x, y;
	public static boolean click1;
	
	public MouseHolder(){
		x = 0;
		y = 0;
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			click1 = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			click1 = false;
	}
//	@Override
//	public void mouseDragged(MouseEvent e){
//		x = e.getX();
//		y = e.getY();
//	}
	@Override
	public void mouseMoved(MouseEvent e){
		x = e.getX();
		y = e.getY();
	}
	
}
