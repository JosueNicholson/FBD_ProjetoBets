package pojo;

public class Shot {
    private Bet bet;
    private int idShot;
    private Match match;
    private int winner;

    public Shot(Bet bet, int idShot, Match match, int winner) {
        this.bet = bet;
        this.idShot = idShot;
        this.match = match;
        this.winner = winner;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
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
