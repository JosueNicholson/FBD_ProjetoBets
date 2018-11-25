package controllers;

import java.util.ArrayList;

import dao.BetDao;
import dao.BetUserDao;
import dao.UserDao;
import pojo.Bet;
import pojo.User;

public class BetController {
	public BetController() {}
	public boolean addBet(int idUser) {
		UserController uc = new UserController();
		User user = uc.getUserById(idUser);
		Bet bet = new Bet(user);
		return new BetDao().addBet(bet);
	}
	public boolean deleteBet(int idBet) {
		return new BetDao().deleteBet(idBet);
	}
	public ArrayList<Bet> getBetsByUser(int idUser){
		return new BetUserDao().getListBetsById(idUser);
	}
}
