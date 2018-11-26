package pojo;

import java.time.LocalDate;

public class Match {
    private int idMatch;
    private Team homeTeam;
    private Team awayTeam;
    private String status;
    private int winner;

    public Match(int idMatch, Team homeTeam, Team awayTeam, int winner, String status) {
        this.idMatch = idMatch;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.winner = winner;
        this.status = status;
    }

    public Match(Team homeTeam, Team awayTeam, int winner, String status) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.winner = winner;
        this.status = status;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
