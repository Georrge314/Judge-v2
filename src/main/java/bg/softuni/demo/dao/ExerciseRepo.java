package bg.softuni.demo.dao;

import bg.softuni.demo.model.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, String> {
    Optional<Exercise> findByName(String name);
}
