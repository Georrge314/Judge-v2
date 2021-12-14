package bg.softuni.demo.service;

import bg.softuni.demo.dao.ExerciseRepo;
import bg.softuni.demo.model.entity.Exercise;
import bg.softuni.demo.model.service.ExerciseCreateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepo exerciseRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepo exerciseRepo, ModelMapper modelMapper) {
        this.exerciseRepo = exerciseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(ExerciseCreateService exerciseService) {
        exerciseRepo.save(modelMapper.map(exerciseService, Exercise.class));
    }

    @Override
    public List<String> getAllExerciseNames() {
        return exerciseRepo.findAll().stream().map(Exercise::getName).collect(Collectors.toList());
    }
}
