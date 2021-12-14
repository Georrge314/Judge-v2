package bg.softuni.demo.model.binding;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleUserBinding {
    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String role;
}
