package controllers;

import java.util.ArrayList;

import dao.ShotDao;
import dao.ShotMatchDao;
import pojo.Match;
import pojo.Shot;

public class ShotController {
	public ShotController() {}
	public boolean addShot(int idBet, int idMatch, int winner) {
		Match match = new MatchController().getMatchById(idMatch);
		Shot shot = new Shot(idBet, match, winner);
		return new ShotDao().addShot(shot);
	}
	public boolean deleteShot(int idShot) {
		return new ShotDao().deleteShot(idShot);
	}
	public ArrayList<Shot> getListShotsByIdBet(int idBet){
		return new ShotMatchDao().getShotsAndMatchesByBetId(idBet);
	}
}
