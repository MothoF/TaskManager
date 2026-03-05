package com.Mothof.TaskManager.Services;

import com.Mothof.TaskManager.Models.Users;
import com.Mothof.TaskManager.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepo;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    public void RegisterUser(Users user){
        HashPassword(user);
        usersRepo.save(user);
    }

    private void HashPassword(Users user){
        String usersRawPassword = user.getPassword();
        String usersHashedPassword = new BCryptPasswordEncoder(12).encode(usersRawPassword);
        user.setPassword(usersHashedPassword);
    }

    public boolean userIsAuthenticated(Users user){
        Authentication authenticationObject = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        if (Objects.requireNonNull(authenticationProvider.authenticate(authenticationObject)).isAuthenticated()){
            System.out.println("User authentication completed");
            return true;
        }
        System.out.println("Could not authenticate user");
        return false;
    }
}
