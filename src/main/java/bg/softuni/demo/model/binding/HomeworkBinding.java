package bg.softuni.demo.model.binding;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class HomeworkBinding {
    @NotNull
    @NotBlank
    private String exercise;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^https:\\/github\\.com\\/[A-Za-z0-9_\\-\\.@]{3,30}\\/[A-Za-z0-9_\\-\\.@]{2,30}\\/.*")
    private String gitAddress;
}
