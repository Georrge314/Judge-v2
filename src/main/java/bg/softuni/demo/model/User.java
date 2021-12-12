package bg.softuni.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Length(min = 3)
    private String username;

    @Length(min = 3)
    private String password;

    @Email
    private String email;

    @Pattern(regexp = "^https:\\/github\\.com\\/[A-Za-z0-9_\\-\\.@]{3,30}\\/SpringTestData\\/.*")
    private String git;

    @ManyToOne()
    private Role role;
}
