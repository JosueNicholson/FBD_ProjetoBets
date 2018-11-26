package controllers;

import dao.MatchDao;
import dao.MatchTeamDao;
import pojo.Match;
import pojo.Team;

import java.util.ArrayList;

public class MatchController {
	
	public MatchController() {}

    public boolean addMatch(Team homeTeam, Team awayTeam) {
        Match match = new Match(homeTeam, awayTeam, -1, "Agendada");
        return new MatchDao().addMatch(match);
    }

    public boolean deleteMatch(int id) {
        return new MatchDao().deleteMatch(id);
    }

    public boolean updateMatch(int id, Team homeTeam, Team awayTeam, int winner, String status) {
        Match match = new Match(id, homeTeam, awayTeam, winner, status);
        return new MatchDao().updateMatch(match);
    }

    public ArrayList<Match> listMatches(){
        return new MatchTeamDao().getListMatches();
    }

    public ArrayList<Match> getMatchByTeamId(int idTeam) {
        return new MatchTeamDao().getMatchByTeamId(idTeam);
    }
    public Match getMatchById(int idMatch) {
    	return new MatchTeamDao().getMatchById(idMatch);
    }
}
