/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import GUI.Resources.ImageHolder;
import GUI.Resources.FontHolder;
import Main.Main;
import UI.Button;
import UI.Click;

public class Menu extends State{	
	
	private final ArrayList<Button> buttons = new ArrayList<Button>();
	
	public Menu(Main main){
		super(main);
		buttons.add(new Button("PLAY", 105, Main.HEIGHT/2 - 50, new Click(){

			@Override
			public void onClick() {
				State.currentState = main.getSelectLevelMenu();
			}}, ImageHolder.font48));
		buttons.add(new Button("SCOREBOARD", 210, Main.HEIGHT/2 + 0, new Click(){

			@Override
			public void onClick() {
				State.currentState = main.getScoreboardMenu();
			}}, ImageHolder.font48));
               /* buttons.add(new Button("SETTINGS", 175, Main.HEIGHT/2 + 50, new Click(){

			@Override
			public void onClick() {
				State.currentState = main.getSettingsMenu();
			}}, ImageHolder.font48));*/
		buttons.add(new Button("EXIT", 105, Main.HEIGHT/2 + 150, new Click(){

			@Override
			public void onClick() {
				System.exit(1);
			}}, ImageHolder.font48));
	}
	
	@Override
	public void update() {
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).update();
	}

	@Override
	public void render(Graphics g) {
		g.setFont(ImageHolder.font80);
		FontHolder.drawString(g, "MATCHBOX", Main.WIDTH/2, Main.HEIGHT/2 - 200, true, Color.WHITE);
		for(int i = 0; i < buttons.size(); i++)
                        buttons.get(i).render(g);
	}

}
