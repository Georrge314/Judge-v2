package bg.softuni.demo.init;

import bg.softuni.demo.dao.RoleRepo;
import bg.softuni.demo.dao.UserRepo;
import bg.softuni.demo.model.entity.Role;
import bg.softuni.demo.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public DatabaseInit(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        roleRepo.saveAll(List.of(roleAdmin, roleUser));

        User user1 = new User(
                "admin98",
                "112233",
                "admin@abv.bg",
                "https:/github.com/admin98/SpringTestData/",
                roleAdmin);

        User user2 = new User(
                "user98",
                "112233",
                "user@abv.bg",
                "https:/github.com/user98/SpringTestData/",
                roleUser);

        userRepo.saveAll(List.of(user1, user2));
    }
}
