package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.Match;
import pojo.Team;

public class MatchDao {
	private Connection connection;
	
	public MatchDao() {}
	
	public boolean addMatch(Match match) { 
		String sql = "INSERT INTO MATCHES(IDHOMETEAM, IDAWAYTEAM, STATUS, WINNER) VALUES(?,?,?,?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, match.getHomeTeam().getIdTeam());
			stmt.setInt(2, match.getAwayTeam().getIdTeam());
			stmt.setString(3, match.getStatus());
			stmt.setInt(4, match.getWinner());
			
			
			
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
	
	public boolean deleteMatch(int id) {
		String sql = "DELETE FROM MATCHES WHERE IDMATCH = ?";
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
}
