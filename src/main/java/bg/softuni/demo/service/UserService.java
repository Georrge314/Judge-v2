package bg.softuni.demo.service;

import bg.softuni.demo.model.entity.User;
import bg.softuni.demo.model.service.UserRegisterService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void create(UserRegisterService userRegisterService);

    boolean findByUsername(String username);

    boolean findByEmail(String email);

    boolean findByUsernameAndPassword(String username, String password);

    Optional<User> getUserByUsername(String username);

    void updateRole(String username, String roleName);

    List<String> getAllUsernames();
}
