package bg.softuni.demo.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NonNull
    @Length(min = 3)
    private String username;

    @NonNull
    @Length(min = 3)
    private String password;

    @NonNull
    @Email
    private String email;

    @NonNull
    @Pattern(regexp = "^https:\\/github\\.com\\/[A-Za-z0-9_\\-\\.@]{3,30}\\/SpringTestData\\/.*")
    private String git;

    @NonNull
    @ManyToOne()
    private Role role;
}
