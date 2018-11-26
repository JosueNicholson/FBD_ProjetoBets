package controllers;

import dao.UserDao;
import pojo.User;

import java.util.ArrayList;

public class UserController {

    public UserController() {}

    public boolean addUser(String nameUser) {
        User user = new User(nameUser);
        return new UserDao().addUser(user);
    }

    public boolean updateUser(int idUser, String nameUser) {
        User user = new User(idUser, nameUser);
        return new UserDao().updateUser(user);
    }

    public boolean deleteUser(int idUser) {
        return new UserDao().deleteUser(idUser);
    }

    public ArrayList<User> listUsers(){
        return new UserDao().getListUsers();
    }
    public User getUserById(int id) {
    	User user = new UserDao().getUserById(id);
    	return user;
    }
}
