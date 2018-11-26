package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionFactory;
import pojo.Shot;

public class ShotDao {
	Connection connection;
	
	public ShotDao() {}
	
	public boolean addShot(Shot shot) {
		String sql = "INSERT INTO SHOTS(IDBET, IDMATCH, WINNER) VALUES(?,?,?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, shot.getIdBet());
			stmt.setInt(2, shot.getMatch().getIdMatch());
			stmt.setInt(3, shot.getWinner());
			
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
		return true;
	}
	
	public boolean deleteShot(int id) {
		String sql = "DELETE FROM SHOTS WHERE IDSHOT = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			
			if(rowsAffected > 0) {
				return true;
			}
			stmt.close();
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
