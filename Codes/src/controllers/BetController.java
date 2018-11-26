package controllers;

import java.util.ArrayList;

import dao.BetDao;
import dao.BetUserDao;
import dao.UserDao;
import pojo.Bet;
import pojo.User;

public class BetController {
	public BetController() {}
	public Bet addBet(int idUser) {
		UserController uc = new UserController();
		User user = uc.getUserById(idUser);
		Bet bet = new Bet(user);
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
