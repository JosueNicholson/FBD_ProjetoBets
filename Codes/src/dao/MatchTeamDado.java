package dao;

import jdbc.ConnectionFactory;
import pojo.Match;
import pojo.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatchTeamDado {
    private Connection connection;

    public ArrayList<Match> getListMatches(){
        String sql = "SELECT * FROM GETALLMATCHES()";
        ArrayList<Match> listMatches = new ArrayList<Match>();
        this.connection = new ConnectionFactory().getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
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
                int winner = rs.getInt("winner");

                Match match = new Match(id, homeTeam, awayTeam, winner, status);

                listMatches.add(match);
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

    public Match getMatcheByTeamId(){
        String sql = "SELECT * FROM GETMATCHEBYTEAMID(?)";
        ArrayList<Match> listMatches = new ArrayList<Match>();
        this.connection = new ConnectionFactory().getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
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
                int winner = rs.getInt("winner");

                Match match = new Match(id, homeTeam, awayTeam, winner, status);

                listMatches.add(match);
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
