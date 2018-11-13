package pojo;

public class Team {
    private int idTeam;
    private String name;
    private String shortName;

    public Team(int idTeam, String name, String shortName) {
        this.idTeam = idTeam;
        this.name = name;
        this.shortName = shortName;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
