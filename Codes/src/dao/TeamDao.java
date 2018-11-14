package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.Team;

public class TeamDao {
	private Connection connection;
	
	public TeamDao() {}
	
	public boolean addTeam(Team team) {
		String sql = "INSERT INTO USERS (NAMETEAM, SHORTNAME) VALUES(?,?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, team.getNameTeam());
			stmt.setString(2, team.getShortName());
			
			int rowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(rowsAffected>0) {
				return true;
			}
			return false;
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			
			try {
				this.connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	public boolean deleteTeam(int id) {
		String sql = "DELETE FROM TEAMS WHERE IDTEAM = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			
			if(rowsAffected > 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	public ArrayList<Team> getListTeams(){
		String sql = "SELECT * FROM TEAMS";
		ArrayList<Team> listTeams = new ArrayList<Team>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("idTeam");
				String name = rs.getString("nameTeam");
				String shortname = rs.getString("shortName");
				
				Team team = new Team(id, name, shortname);
				
				listTeams.add(team);
				
			}
			stmt.close();
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			
			try {
				this.connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return listTeams;
	}
}
