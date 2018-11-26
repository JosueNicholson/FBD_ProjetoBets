package controllers;

import java.util.ArrayList;

import dao.BetDao;
import dao.BetUserDao;
import dao.UserDao;
import pojo.Bet;
import pojo.User;

public class BetController {
	public BetController() {}
	
	public Bet addBet(int idUser, int idBet) {
		UserController uc = new UserController();
		User user = uc.getUserById(idUser);
		if(user==null) {
			return null;
		}
		Bet bet = new Bet(idBet, user);
		if(new BetDao().addBet(bet)) {
			return bet;
		}
		else return null;
	}
	public boolean deleteBet(int idBet) {
		return new BetDao().deleteBet(idBet);
	}
	public ArrayList<Bet> getBetsByUser(int idUser){
		return new BetUserDao().getListBetsById(idUser);
	}
}
