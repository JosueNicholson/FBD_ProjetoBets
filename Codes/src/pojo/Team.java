package pojo;

public class Team {
    private int idTeam;
    private String nameTeam;
    private String shortName;

    public Team(int idTeam, String nameTeam, String shortName) {
        this.idTeam = idTeam;
        this.nameTeam = nameTeam;
        this.shortName = shortName;
    }
    public Team(String nameTeam, String shortName) {
        this.nameTeam = nameTeam;
        this.shortName = shortName;
    } 

    public int getIdTeam() {
        return idTeam;
    }


    public String getNameTeam() {
        return nameTeam;
    }

    public String getShortName() {
        return shortName;
    }
}
