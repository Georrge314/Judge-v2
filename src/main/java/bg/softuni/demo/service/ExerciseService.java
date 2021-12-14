package bg.softuni.demo.service;

import bg.softuni.demo.model.service.ExerciseCreateService;

import java.util.List;

public interface ExerciseService {
    void create(ExerciseCreateService exerciseService);

    List<String> getAllExerciseNames();
}
