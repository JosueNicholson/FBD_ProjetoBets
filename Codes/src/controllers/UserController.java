package controllers;

import dao.UserDao;
import pojo.User;

public class UserController {

    public UserController() {}

    public boolean addUser(String nameUser) {
        User user = new User(nameUser);
        return new UserDao().addUser(user);
    }
}
