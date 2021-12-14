package bg.softuni.demo.service;

import bg.softuni.demo.model.service.UserRegisterService;

public interface UserService {
    void create(UserRegisterService userRegisterService);

    boolean findByUsername(String username);

    boolean findByEmail(String email);

    boolean findByUsernameAndPassword(String username, String password);
}
