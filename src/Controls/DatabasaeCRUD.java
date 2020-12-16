/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Lorcha
 */
public class DatabasaeCRUD {
    
        
    
public void create_data(int oascore,String name){
	DatabaseHolder dbHolder = new DatabaseHolder();
	Connection connection=dbHolder.openConnection();
	PreparedStatement ps=null;
	try {
		String query="insert into scoreboard(overallscore, name) values (?,?)";
		ps=connection.prepareStatement(query);
		ps.setInt(1, oascore);
                ps.setString(2, name);
		ps.executeUpdate();
	} catch (Exception e) {
		System.out.println(e);
	}
}

public int select_data_number(int place){
	DatabaseHolder dbHolder = new DatabaseHolder();
	Connection connection=dbHolder.openConnection();
	PreparedStatement ps=null;
        int number = 0;
	try {
		String query="SELECT overallscore FROM scoreboard ORDER BY overallscore DESC LIMIT "+place+" , 1";
		ps=connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                number = rs.getInt(1);
                }
	} catch (Exception e) {
		System.out.println(e);
	}
    return number;
}
public String select_data_name(int place){
	DatabaseHolder dbHolder = new DatabaseHolder();
	Connection connection=dbHolder.openConnection();
	PreparedStatement ps=null;
        String name = null;
	try {
		String query="SELECT name FROM scoreboard ORDER BY overallscore DESC LIMIT "+place+" , 1";
		ps=connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                name = rs.getString(1);
                }
	} catch (Exception e) {
		System.out.println(e);
	}
    return name;
}
}


