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

    public String getNameUser() {
        return nameUser;
    }
}
