package bg.softuni.demo.service;

import bg.softuni.demo.dao.RoleRepo;
import bg.softuni.demo.dao.UserRepo;
import bg.softuni.demo.model.entity.Role;
import bg.softuni.demo.model.entity.User;
import bg.softuni.demo.model.service.UserRegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepo userRepo, RoleRepo roleRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    @Transactional
    public void create(UserRegisterService userRegisterService) {
        User user = modelMapper.map(userRegisterService, User.class);
        Optional<Role> role = roleRepo.findByName("USER");
        if (role.isPresent()) {
            user.setRole(role.get());
        } else {
            user.setRole(new Role("USER"));
        }
        userRepo.save(user);
    }

    @Override
    public boolean findByUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    @Override
    public boolean findByEmail(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    public boolean findByUsernameAndPassword(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password).isPresent();
    }
}
