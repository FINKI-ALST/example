package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.model.User;

public interface LoginService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    User login(String username, String password);
    User loadUserByUsername(String username);
}
