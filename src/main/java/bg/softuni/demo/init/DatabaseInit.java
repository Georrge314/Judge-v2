package bg.softuni.demo.init;

import bg.softuni.demo.dao.RoleRepo;
import bg.softuni.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final RoleRepo roleRepo;

    @Autowired
    public DatabaseInit(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");
        Role roleUser = new Role();
        roleUser.setName("USER");

        roleRepo.saveAll(List.of(roleAdmin, roleUser));
    }
}
