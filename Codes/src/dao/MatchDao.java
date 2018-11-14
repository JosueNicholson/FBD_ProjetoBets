package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.Match;
import pojo.Team;

public class MatchDao {
	private Connection connection;
	
	public MatchDao() {}
	
	public boolean addMatch(Match match) { 
		String sql = "INSERT INTO MATCHES(IDHOMETEAM, IDAWAYTEAM, DATE, WINNER) VALUES(?,?,?,?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, match.getHomeTeam().getIdTeam());
			stmt.setInt(2, match.getAwayTeam().getIdTeam());
//			stmt.setDate;(3, match.getDate()); 	--IMPORTANTE: NAO EXISTE .SETLOCALDATE--  
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
	
	public ArrayList<Match> getListMatches(){
		String sql = "SELECT * FROM MATCHES";
		ArrayList<Match> listMatches = new ArrayList<Match>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("idMatch");
				Team homeTeam = (Team) rs.getObject("homeTeam");
				Team awayTeam = (Team) rs.getObject("awayTeam");
//				LocalDate date = rs.getDate("date"); OUTRO PROBLEMA COM LOCALDATE
				
				
//				Match match = new Match();
				
//				listMatches.add(match);
				
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
		return listMatches;
	}
}
