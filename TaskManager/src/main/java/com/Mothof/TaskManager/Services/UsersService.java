package com.Mothof.TaskManager.Services;

import com.Mothof.TaskManager.Models.Users;
import com.Mothof.TaskManager.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepo;

    public void RegisterUser(Users user){
        HashPassword(user);
        usersRepo.save(user);
        System.out.println("User registered successfully");
    }

    private void HashPassword(Users user){
        String usersRawPassword = user.getPassword();
        String usersHashedPassword = new BCryptPasswordEncoder(12).encode(usersRawPassword);
        user.setPassword(usersHashedPassword);
    }
}
