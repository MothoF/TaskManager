package com.Mothof.TaskManager.Controllers;

import com.Mothof.TaskManager.Models.Users;
import com.Mothof.TaskManager.Repository.UsersRepository;
import com.Mothof.TaskManager.Services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepo;

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
    public String login(@ModelAttribute("user") Users user){
        if(!usersService.userIsAuthenticated(user)){
            System.out.println("Errors found");
            return "/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/changePassword")
    public String changeAccountPassword(Model model){
        model.addAttribute("user", new Users());
        return "changePasswordForm";
    }

    @PostMapping("/changePassword")
    public String changeAccountPassword(@ModelAttribute("user") Users user){
        if (usersService.usernameIsRecognisedInDb(user)){
            usersService.changeAccountPassword(user);
            return "redirect:/login";
        }
        return "changePasswordForm";
    }

    @GetMapping("/home")
    @ResponseBody
    public String homePage(Principal principalUser){
        String username = principalUser.getName();
        Users user = usersRepo.findByUserName(username);
        return "Welcome to the Home Page "+user.getFirstName()+" "+user.getLastName();
    }

}
