/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

/**
 *
 * @author Lorcha
 */
import java.awt.Color;
import java.awt.Graphics;

import GUI.Resources.ImageHolder;
import GUI.Resources.FontHolder;
import Main.Main;
import UI.Button;
import UI.Click;
import Controls.DatabasaeCRUD;
public class ScoreboardMenu extends State{	
	private final Button back;
        public int overallscore;
	public DatabasaeCRUD DCRUD = new DatabasaeCRUD();
        private int score, score1, score2, score3, score4;
        private String name, name1, name2, name3, name4;
	public ScoreboardMenu(Main main){
		super(main);
                
                back = new Button("BACK", Main.WIDTH/2, Main.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				State.currentState = main.getMenu();
			}
			
		}, ImageHolder.font48);
                score = DCRUD.select_data_number(0);
                score1 = DCRUD.select_data_number(1);
                score2 = DCRUD.select_data_number(2);
                score3 = DCRUD.select_data_number(3);
                score4 = DCRUD.select_data_number(4);
                name = DCRUD.select_data_name(0);
                name1 = DCRUD.select_data_name(1);
                name2 = DCRUD.select_data_name(2);
                name3 = DCRUD.select_data_name(3);
                name4 = DCRUD.select_data_name(4);
                
	}
     @Override
	public void update() {
		back.update();
	}

	@Override
	public void render(Graphics g) {
                back.render(g);
		g.setFont(ImageHolder.font30);
		FontHolder.drawString(g, "#1 : "+ name +" - "+ score, Main.WIDTH/2, Main.HEIGHT/2 - 200, true, Color.WHITE);
                FontHolder.drawString(g, "#2 : "+ name1+" - "+ score1, Main.WIDTH/2, Main.HEIGHT/2 - 150, true, Color.WHITE);
                FontHolder.drawString(g, "#3 : "+ name2+" - "+ score2, Main.WIDTH/2, Main.HEIGHT/2 - 100, true, Color.WHITE);
                FontHolder.drawString(g, "#4 : "+ name3+" - "+ score3, Main.WIDTH/2, Main.HEIGHT/2 - 50, true, Color.WHITE);
                FontHolder.drawString(g, "#5 : "+ name4+" - "+ score4, Main.WIDTH/2, Main.HEIGHT/2, true, Color.WHITE);
	}
}