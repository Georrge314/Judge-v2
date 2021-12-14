package bg.softuni.demo.model.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExerciseCreateService {
    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;
}
