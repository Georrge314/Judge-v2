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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void updateRole(String username, String roleName) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        Optional<Role> optionalRole = roleRepo.findByName(roleName);

        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            User user = optionalUser.get();
            Role role = optionalRole.get();

            user.setRole(role);
            userRepo.save(user);
        }
    }

    @Override
    public List<String> getAllUsernames() {
        return userRepo.findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }
}
