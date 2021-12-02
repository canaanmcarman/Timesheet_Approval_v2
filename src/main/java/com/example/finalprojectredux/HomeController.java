package com.example.finalprojectredux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;



    @RequestMapping("/")
    public String adminPage() {

        return "index";
    }

    @RequestMapping("/admin")
    public String index() {
        return "admin";
    }

    @PostMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/processregister")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.clearPassword();
            model.addAttribute("user", new User());
            return "register";
        } else {
            model.addAttribute("user", user);
            user.setEnabled(true);
            userRepository.save(user);

            Role role = new Role(user.getUsername(), "ROLE_USER");
            roleRepository.save(role);
        }
        return "redirect:/login";
    }
}
