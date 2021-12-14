package bg.softuni.demo.model.service;

import lombok.Data;

@Data
public class UserRegisterService {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String git;
}
