package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionFactory;
import pojo.Match;
import pojo.Shot;
import pojo.Team;

public class ShotMatchDao {
	Connection connection;
	
	public ArrayList<Shot> getShotsAndMatchesByBetId(int betId) {
		String sql = "SELECT GAM.IDMATCH, GAM.IDHOMETEAM, GAM.IDAWAYTEAM, GAM.STATUS, GAM.WINNER WINNERMATCH, GAM.NAMEHOMETEAM, GAM.NAMeAWAYTEAM, GAM.SHORTNAMEHOMETEAM, GAM. SHORTNAMEAWAYTEAM, S.IDSHOT, S.IDBET, S.WINNER, S.IDMATCH FROM GETALLMATCHES() GAM, SHOTS S WHERE S.IDBET=? AND S.IDMATCH = GAM.IDMATCH";
		ArrayList<Shot> listShots = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, betId);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
	            int idHomeTeam = rs.getInt("idHomeTeam");
	            String nameHomeTeam = rs.getString("nameHomeTeam");
	            String shortNameHomeTeam = rs.getString("shortNameHomeTeam");
	            Team homeTeam = new Team(idHomeTeam, nameHomeTeam, shortNameHomeTeam);
	
	            int idAwayTeam = rs.getInt("idAwayTeam");
	            String nameAwayTeam = rs.getString("nameAwayTeam");
	            String shortNameAwayTeam = rs.getString("shortNameAwayTeam");
	            Team awayTeam = new Team(idAwayTeam, nameAwayTeam, shortNameAwayTeam);
	
	            int id = rs.getInt("idMatch");
	            String status = rs.getString("status");
	            int winnerMatch = rs.getInt("winnerMatch");
	
	            Match match = new Match(id, homeTeam, awayTeam, winnerMatch, status);
	            
				int winnerShot = rs.getInt("winner");
				Shot shot = new Shot(betId, match, winnerShot);
				
				listShots.add(shot);
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
		return listShots;
	}
}
