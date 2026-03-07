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
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

    public boolean userPasswordMatchesCriteria(Users user){
        String password = user.getPassword();
        String passwordRegex = "^(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches() && password.length() >= 8){
            return true;
        }
        return false;
    }


    public boolean userIsAuthenticated(Users user){
        Authentication authenticationObject = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        if (Objects.requireNonNull(authenticationProvider.authenticate(authenticationObject)).isAuthenticated()){
            return true;
        }
        return false;
    }

    public boolean usernameIsRecognisedInDb(Users user) {
        String userProvidedUsername = user.getUsername();
        Users databaseUserWithSameUsername = usersRepo.findByUsername(userProvidedUsername);
        if (databaseUserWithSameUsername == null){
            return false;
        }
        return databaseUserWithSameUsername.equals(user);
    }
}
