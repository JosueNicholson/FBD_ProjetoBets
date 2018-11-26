package pojo;

import java.util.ArrayList;

public class Bet {
    private int idBet;
    private User user;
    private ArrayList<Shot> listShots;

    public Bet(int idBet, User user) {
        this.idBet = idBet;
        this.user = user;
        this.listShots = new ArrayList<>();
    }
    public Bet(User user) {
    	this.user = user;
    	this.listShots = new ArrayList<>();
    }

    public int getIdBet() {
        return idBet;
    }

    public void setIdBet(int idBet) {
        this.idBet = idBet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public ArrayList<Shot> getListShots(){
    	return listShots;
    }
}
