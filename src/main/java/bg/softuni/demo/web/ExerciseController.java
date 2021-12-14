package bg.softuni.demo.web;

import bg.softuni.demo.model.binding.ExerciseBinding;
import bg.softuni.demo.model.entity.User;
import bg.softuni.demo.model.service.ExerciseCreateService;
import bg.softuni.demo.service.ExerciseService;
import bg.softuni.demo.service.UserService;
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
import java.util.Optional;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, UserService userService, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String getExercise(Model model, HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        Optional<User> optionalUser = userService.getUserByUsername(username);
        if (optionalUser.isPresent() && optionalUser.get().getRole().getName().equals("ADMIN")) {
            if (!model.containsAttribute("exerciseBinding")) {
                model.addAttribute("exerciseBinding", new ExerciseBinding());
            }
            return "exercise-add";
        }
        return "redirect:/";
    }

    @PostMapping("/add")
    public String postExercise(@Valid ExerciseBinding exerciseBinding,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseBinding", exerciseBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseBinding", bindingResult);
            return "redirect:add";
        }

        exerciseService.create(modelMapper.map(exerciseBinding, ExerciseCreateService.class));
        return "redirect:/";
    }
}
