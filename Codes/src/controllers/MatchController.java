package controllers;

import dao.MatchDao;
import dao.MatchTeamDado;
import pojo.Match;
import pojo.Team;

import java.util.ArrayList;

public class MatchController {

    public boolean addMatch(Team homeTeam, Team awayTeam) {
        Match match = new Match(homeTeam, awayTeam, -1, "Agendado");
        return new MatchDao().addMatch(match);
    }

    public boolean deleteMatch(int id) {
        return new MatchDao().deleteMatch(id);
    }

    public ArrayList<Match> listMatches(){
        return new MatchTeamDado().getListMatches();
    }

    public Team getMatchByTeamId(int idTeam) {
        return null;//new //TeamDao().getTeamById(idTeam);
    }
}
