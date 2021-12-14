package bg.softuni.demo.service;

import bg.softuni.demo.dao.ExerciseRepo;
import bg.softuni.demo.dao.HomeworkRepo;
import bg.softuni.demo.dao.UserRepo;
import bg.softuni.demo.model.entity.Exercise;
import bg.softuni.demo.model.entity.Homework;
import bg.softuni.demo.model.entity.User;
import bg.softuni.demo.model.service.HomeworkCreateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeworkServiceImpl implements HomeworkService{
    private final HomeworkRepo homeworkRepo;
    private final ExerciseRepo exerciseRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepo homeworkRepo, ExerciseRepo exerciseRepo, UserRepo userRepo, ModelMapper modelMapper) {
        this.homeworkRepo = homeworkRepo;
        this.exerciseRepo = exerciseRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(HomeworkCreateService homeworkCreateService, String username) {
        Optional<Exercise> optionalExercise = exerciseRepo.findByName(homeworkCreateService.getExercise());
        Optional<User> optionalUser = userRepo.findByUsername(username);

        if (optionalExercise.isPresent() && optionalUser.isPresent()) {
            Homework homework = new Homework();
            homework.setGitAddress(homeworkCreateService.getGitAddress());
            homework.setExercise(optionalExercise.get());
            homework.setAuthor(optionalUser.get());

            homeworkRepo.save(homework);
        }
    }
}
