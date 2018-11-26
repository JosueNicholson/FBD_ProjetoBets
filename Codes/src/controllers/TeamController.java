package controllers;

import java.util.ArrayList;

import dao.TeamDao;
import pojo.Team;

public class TeamController {
	
	public TeamController() {}
	
	public boolean addTeam(String nameTeam, String shortName) {
		Team team = new Team(nameTeam, shortName);
		return new TeamDao().addTeam(team);
	}

	public boolean editTeam(int idTeam, String nameTeam, String shortName) {
		Team team = new Team(idTeam, nameTeam, shortName);
		return new TeamDao().editTeam(team);
	}

	public boolean deleteTeam(int id) {
		return new TeamDao().deleteTeam(id);
	}

	public ArrayList<Team> listTeams(){
		return new TeamDao().getListTeams();
	}

	public Team getTeamById(int idTeam) {
	    return new TeamDao().getTeamById(idTeam);
    }
}
