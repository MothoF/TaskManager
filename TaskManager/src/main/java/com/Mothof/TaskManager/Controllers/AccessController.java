package com.Mothof.TaskManager.Controllers;

import com.Mothof.TaskManager.Models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessController {
    @GetMapping({"/", "/login"})
    public String login(Model model) {
        model.addAttribute("user", new Users());
        return "loginForm";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new Users());
        return "registrationForm";
    }

}
