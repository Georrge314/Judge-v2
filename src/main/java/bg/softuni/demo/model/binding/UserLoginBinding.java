package bg.softuni.demo.model.binding;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginBinding {
    @NotBlank
    @Length(min = 2)
    private String username;
    @NotBlank
    @Length(min = 3)
    private String password;
}
