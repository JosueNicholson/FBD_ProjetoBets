package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.User;

public class UserDao {
	private Connection connection;
	
	public UserDao() {}
	
	public boolean addUser(User user) {
		String sql = "INSERT INTO USERS (NAMEUSER) VALUES(?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getNameUser());
			
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

	public boolean updateUser(User user) {
		String sql = "UPDATE USERS SET IDUSER=?, NAMEUSER=? WHERE IDUSER=?";
		this.connection = new ConnectionFactory().getConnection();

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, user.getIdUser());
			stmt.setString(2, user.getNameUser());
			stmt.setInt(3, user.getIdUser());

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
	
	public boolean deleteUser(int id) {
		String sql = "DELETE FROM USERS WHERE IDUSER = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			stmt.close();
			
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
	
	public ArrayList<User> getListUsers(){
		String sql = "SELECT * FROM USERS";
		ArrayList<User> listUsers = new ArrayList<User>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("idUser");
				String name = rs.getString("nameUser");
				
				User user = new User(id, name);
				
				listUsers.add(user);
				
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
		return listUsers;
	}
	public User getUserById(int idUser) {
		String sql = "SELECT * FROM USERS WHERE IDUSER = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idUser);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String nameUser = rs.getString("nameUser");
				User user = new User(idUser, nameUser);
				return user;
			}
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			
			try {
				this.connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return null;
	}
}
