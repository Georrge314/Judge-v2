package bg.softuni.demo.web;

import bg.softuni.demo.model.binding.RoleUserBinding;
import bg.softuni.demo.model.entity.User;
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
@RequestMapping("/roles")
public class RoleController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("add")
    public String getRole(Model model, HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        Optional<User> optionalUser = userService.getUserByUsername(username);
        if (optionalUser.isPresent() && optionalUser.get().getRole().getName().equals("ADMIN")) {
            if (!model.containsAttribute("roleUserBinding")) {
                model.addAttribute("roleUserBinding", new RoleUserBinding());
                model.addAttribute("listOfUsernames", userService.getAllUsernames());
            }
            return "role-add";
        }

        return "redirect:/";
    }

    @PostMapping("add")
    public String postRole(@Valid RoleUserBinding roleUserBinding,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("roleUserBinding", redirectAttributes);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.redirectAttributes", bindingResult);
        }

        String username = roleUserBinding.getUsername();
        String roleName = roleUserBinding.getRole();
        userService.updateRole(username, roleName);
        return "redirect:/";
    }
}
