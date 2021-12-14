package bg.softuni.demo.web;

import bg.softuni.demo.model.binding.HomeworkBinding;
import bg.softuni.demo.model.service.HomeworkCreateService;
import bg.softuni.demo.service.ExerciseService;
import bg.softuni.demo.service.HomeworkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    private final HomeworkService homeworkService;
    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkController(HomeworkService homeworkService, ExerciseService exerciseService, ModelMapper modelMapper) {
        this.homeworkService = homeworkService;
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("add")
    public String getHomeworkAdd(Model model) {
        if (!model.containsAttribute("homeworkBinding")) {
            model.addAttribute("homeworkBinding", new HomeworkBinding());
            model.addAttribute("listOfExerciseNames", exerciseService.getAllExerciseNames());
        }
        return "homework-add";
    }

    @PostMapping("add")
    public String postHomeworkAdd(@Valid HomeworkBinding homeworkBinding,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkBinding", homeworkBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkBinding", bindingResult);
        }

        String username = (String) httpSession.getAttribute("username");
        homeworkService.create(modelMapper.map(homeworkBinding, HomeworkCreateService.class), username);
        return "redirect:/";
    }

    @GetMapping("check")
    public String getHomeworkCheck() {

    }

    @PostMapping("check")
    public String postHomeworkPost() {

    }
}
