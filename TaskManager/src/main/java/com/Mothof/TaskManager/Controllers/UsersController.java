package com.Mothof.TaskManager.Controllers;

import com.Mothof.TaskManager.Models.Users;
import com.Mothof.TaskManager.Services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String register(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("Errors found");
            return "registrationForm";
        }
        if (usersService.userPasswordMatchesCriteria(user)){
            usersService.registerUser(user);
            return "redirect:/login";
        }
        return "registrationForm";
    }

    @PostMapping({"/","/login"})
    @ResponseBody
    public String login(@ModelAttribute("user") Users user){
        if(!usersService.userIsAuthenticated(user)){
            System.out.println("Errors found");
            return "/login";
        }
        return "You have successfully logged in you doofus!!";
    }

    @GetMapping("/changePassword")
    public String changeAccountPassword(Model model){
        model.addAttribute("user", new Users());
        return "changePasswordForm";
    }

    @PostMapping("changePassword")
    public String changeAccountPassword(@ModelAttribute("user") Users user){
        if (usersService.usernameIsRecognisedInDb(user)){
            usersService.changeAccountPassword(user);
            return "redirect:/login";
        }
        return "changePasswordForm";
    }

}
