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
import Controls.ControlState;
/**
 *
 * @author Lorcha
 */
public class SettingsMenu extends State {
        private ControlState cstate = new ControlState();
        private final Button back, wasd, arrow;
	public SettingsMenu(Main main){
		super(main);
                back = new Button("BACK", Main.WIDTH/2, Main.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				State.currentState = main.getMenu();
			}
			
		}, ImageHolder.font48);
                wasd = new Button("WASD Keys", Main.WIDTH/2 + 250, Main.HEIGHT/2 - 100, new Click(){

			@Override
			public void onClick() {
				cstate.controlstate1 = 2;
                                System.out.println(cstate.getcState());
			}
			
		}, ImageHolder.font30);
                arrow = new Button("ARROW Keys", Main.WIDTH/2 + 250, Main.HEIGHT/2 - 50, new Click(){
			@Override
			public void onClick() {
				cstate.controlstate1 = 1;
                                System.out.println(cstate.getcState());
			}
			
		}, ImageHolder.font30);
        
                
                
	}
     @Override
	public void update() {
		back.update();
                wasd.update();
                arrow.update();
	}

	@Override
	public void render(Graphics g) {
                back.render(g);
                wasd.render(g);
                arrow.render(g);
		g.setFont(ImageHolder.font30);
		FontHolder.drawString(g, "SETTINGS", Main.WIDTH/2, Main.HEIGHT/2 - 200, true, Color.WHITE);
                FontHolder.drawString(g, "CHARCTER", Main.WIDTH/2 - 250, Main.HEIGHT/2 - 150, true, Color.WHITE);
                FontHolder.drawString(g, "CONTROLS", Main.WIDTH/2 + 250, Main.HEIGHT/2 - 150, true, Color.WHITE);
	}
}
