package pojo;

public class Shot {
    private int idBet;
    private int idShot;
    private Match match;
    private int winner;

    public Shot(int idBet, int idShot, Match match, int winner) {
        this.idBet = idBet;
        this.idShot = idShot;
        this.match = match;
        this.winner = winner;
    }
    public Shot(int idBet, Match match, int winner) {
        this.match = match;
        this.winner = winner;
        this.idBet = idBet;
    }

    public int getIdBet() {
        return idBet;
    }

    public void setIdBet(int idBet) {
        this.idBet = idBet;
    }

    public int getIdShot() {
        return idShot;
    }

    public void setIdShot(int idShot) {
        this.idShot = idShot;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
