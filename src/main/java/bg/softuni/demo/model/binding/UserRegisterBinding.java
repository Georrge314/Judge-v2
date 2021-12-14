package bg.softuni.demo.model.binding;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterBinding {
    @NotBlank
    @Length(min = 3)
    private String username;

    @NotBlank
    @Length(min = 3)
    private String password;

    @NotBlank
    @Length(min = 3)
    private String confirmPassword;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^https:\\/github\\.com\\/[A-Za-z0-9_\\-\\.@]{3,30}\\/SpringTestData\\/.*")
    private String git;
}
