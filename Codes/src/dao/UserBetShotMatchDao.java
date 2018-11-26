package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.User;

public class UserBetShotMatchDao {
	Connection connection;
	
	public ArrayList<User> getListUsersWhoDidHitAShot(int idMatch){
		String sql = "SELECT U.IDUSER, U.NAMEUSER FROM MATCHES GAM, SHOTS S, BETS B, USERS U WHERE GAM.STATUS LIKE 'Finalizada' AND S.WINNER=GAM.WINNER AND B.IDBET=S.IDBET AND U.IDUSER=B.IDUSER AND GAM.IDMATCH=? AND S.IDMATCH=GAM.IDMATCH";
		ArrayList<User> listUsers = new ArrayList<User>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idMatch);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int idUser = rs.getInt("idUser");
				String nameUser = rs.getString("nameUser");
				User user = new User(idUser, nameUser);
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
}
