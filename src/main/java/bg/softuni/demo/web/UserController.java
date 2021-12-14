package bg.softuni.demo.web;

import bg.softuni.demo.model.binding.UserLoginBinding;
import bg.softuni.demo.model.binding.UserRegisterBinding;
import bg.softuni.demo.model.service.UserRegisterService;
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

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        if (!model.containsAttribute("userLoginBinding")) {
            model.addAttribute("userLoginBinding", new UserLoginBinding());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginBinding userLoginBinding,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBinding", userLoginBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBinding", bindingResult);
            return "redirect:login";
        }

        boolean isUserRegistered = userService.findByUsernameAndPassword(userLoginBinding.getUsername(), userLoginBinding.getPassword());
        if (!isUserRegistered) {
            redirectAttributes.addFlashAttribute("userLoginBinding", userLoginBinding);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:login";
        }

        httpSession.setAttribute("user", userLoginBinding);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        if (!model.containsAttribute("userRegisterBinding")) {
            model.addAttribute("userRegisterBinding", new UserRegisterBinding());
            model.addAttribute("usernameExists", false);
            model.addAttribute("emailExists", false);
            model.addAttribute("invalidConfirmPassword", false);
        }
        return "register";
    }


    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterBinding userRegisterBinding,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBinding", userRegisterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBinding", bindingResult);
            return "redirect:register";
        }

        boolean isUsernameExists = userService.findByUsername(userRegisterBinding.getUsername());
        if (isUsernameExists) {
            redirectAttributes.addFlashAttribute("userRegisterBinding", userRegisterBinding);
            redirectAttributes.addFlashAttribute("usernameExists", true);
            return "redirect:register";
        }

        boolean isEmailExists = userService.findByEmail(userRegisterBinding.getEmail());
        if (isEmailExists) {
            redirectAttributes.addFlashAttribute("userRegisterBinding", userRegisterBinding);
            redirectAttributes.addFlashAttribute("emailExists", true);
            return "redirect:register";
        }

        boolean isConfirmPassEqualsPass = userRegisterBinding.getPassword().equals(userRegisterBinding.getConfirmPassword());
        if (!isConfirmPassEqualsPass) {
            redirectAttributes.addFlashAttribute("userRegisterBinding", userRegisterBinding);
            redirectAttributes.addFlashAttribute("invalidConfirmPassword", true);
            return "redirect:register";
        }

        userService.create(modelMapper.map(userRegisterBinding, UserRegisterService.class));
        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

}
