package bg.softuni.demo.service;

import bg.softuni.demo.model.service.HomeworkCreateService;

public interface HomeworkService {
    void create(HomeworkCreateService homeworkCreateService, String username);
}
