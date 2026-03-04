package com.Mothof.TaskManager.Controllers;

import com.Mothof.TaskManager.Models.Users;
import com.Mothof.TaskManager.Services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;


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

    @PostMapping("/register")
    @ResponseBody
    public String register(@Valid Users user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("Errors found");
            return "/register";
        }
        usersService.RegisterUser(user);
        return "User has been successfully registered";
    }

}
