package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.Bet;
import pojo.User;

public class BetUserDao {
	Connection connection;
	
	public BetUserDao() {}
	
	public ArrayList<Bet> getListBetsById(int idUser){
		String sql = "SELECT * FROM BETS b, USERS u WHERE b.IDUSER=? and b.IDUSER = u.IDUSER ";
		ArrayList<Bet> listBets = new ArrayList<Bet>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idUser);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int iduser = rs.getInt("idUser");
				String nameUser = rs.getString("nameUser");
				User user = new User(iduser, nameUser);
				
				int idBet = rs.getInt("idBet");
				Bet bet = new Bet(idBet, user);
				
				listBets.add(bet);
				
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
		return listBets;
	}

}
