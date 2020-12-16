/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import GUI.Resources.FontHolder;
import GUI.Resources.ImageHolder;
import Main.Main;
import UI.Button;
import UI.Click;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Lorcha
 */
public class NameGetter extends State {
    
        private final Button back;
        public int overallscore;
	public NameGetter(Main main){
		super(main);
                back = new Button("ENTER", Main.WIDTH/2, Main.HEIGHT - 200, new Click(){

			@Override
			public void onClick() {
				State.currentState = main.getMenu();
			}
			
		}, ImageHolder.font48);
        
                
                
	}
     @Override
	public void update() {
		back.update();
	}

	@Override
	public void render(Graphics g) {
                back.render(g);
		g.setFont(ImageHolder.font30);
		FontHolder.drawString(g, "ENTER YOUR NAME:", Main.WIDTH/2, Main.HEIGHT/2 - 200, true, Color.WHITE);
	}
}
