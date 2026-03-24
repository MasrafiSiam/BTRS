package controller;

import dao.UserDAO;

public class AuthController {

    UserDAO dao = new UserDAO();

    public boolean register(String name, String email, String password) {
        return dao.register(name, email, password);
    }

    public boolean login(String email, String password) {
        return dao.login(email, password);
    }
}