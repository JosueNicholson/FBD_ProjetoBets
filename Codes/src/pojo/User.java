package pojo;

public class User {
    private int idUser;
    private String nameUser;

    public User(int idUser, String nameUser) {
        super();
        this.idUser = idUser;
        this.nameUser = nameUser;
    }

    public User(String nameUser) {
        super();
        this.nameUser = nameUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
