package com.Mothof.TaskManager.Services;

import com.Mothof.TaskManager.Models.Users;
import com.Mothof.TaskManager.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
//    @Autowired
//    private AuthenticationProvider authenticationProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void registerUser(Users user){
        hashRegistrationPassword(user);
        usersRepo.save(user);
    }

    /*
    This method is used to hash the new passwords of users who have forgotten their old password.
    Parameter Users user -> is a Users object with mostly null fields, save for username and password.

    The method uses the username field value from the parameter to get the fully detailed (i.e. no null fields)
    database record of a user with the same username, and then it continues to hash the raw text password
    of the parameter and set it as the new password of the fully detailed database record of the same username.
    * */
    private void hashPasswordChange(Users user){
        String newPassword = user.getPassword();// This password is raw text
        Users userRecordStoredInDb = getUserRecordStoredInTheDatabase(user);
        String currentPassword = userRecordStoredInDb.getPassword(); //This password is hashed
        String newPasswordHashed = new BCryptPasswordEncoder(12).encode(newPassword);
        if (!newPasswordHashed.equals(currentPassword)){
            userRecordStoredInDb.setPassword(newPasswordHashed);
        }
    }

    /*
    This method is used to hash the passwords of users who are registering an new user account
    Parameter Users user -> is a Users object with no null fields, it is fully detailed.
     */
    private void hashRegistrationPassword(Users user){
        String usersRawPassword = user.getPassword();
        String usersHashedPassword = new BCryptPasswordEncoder(12).encode(usersRawPassword);
        user.setPassword(usersHashedPassword);
    }

    /*
    This method reads the database to return a Users object with no null fields
    Parameter Users user -> is a Users object with mostly null fields save for username and password.
     */
    public Users getUserRecordStoredInTheDatabase(Users user){
        return usersRepo.findByUserName(user.getUserName());
    }

    /*
    This method uses regex patterns to check whether the user's password matches the required criteria
    Parameter User user -> is a Users object with no null fields

    The above mentioned password criteria is:
    - At least one instance of a lowercase character
    - At least one instance of a digit
    - At least one instance of an uppercase character
    - At least one instance of a non digit and non letter (i.e. a symbol)
    - At least 8 characters long
     */
    public boolean userPasswordMatchesCriteria(Users user){
        String password = user.getPassword();
        String passwordRegex = "^(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    /*
    This method authenticates a user through their username and password
    Parameter Users user -> is a Users object with mostly null fields save for username and password

    The authenticationProvider bean is connected to the database, it takes the user provided credentials
    (i.e. username and password) and compares them with database records to try and find a match in a
    quest to authenticate the user trying to login.
     */
    public boolean userIsAuthenticated(Users user){
//        Authentication authenticationObject = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//        if (Objects.requireNonNull(authenticationProvider.authenticate(authenticationObject)).isAuthenticated()){
//            return true;
//        }
        Authentication authenticationObject = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        if (authenticationObject.isAuthenticated()){
            return true;
        }
        return false;
    }

    /*
    This method checks whether the username provided by the user in their attempt to
    reset their password exists in the database.
    Parameter Users user -> is a Users object with mostly null fields save for username and password
     */
    public boolean usernameIsRecognisedInDb(Users user) {
        Users databaseUserWithSameUsername = getUserRecordStoredInTheDatabase(user);
        if (databaseUserWithSameUsername == null){
            return false;
        }
        return true;
    }

    /*
    This method updates/changes the user's password and stores the it in the database.
    Parameter Users user -> is a Users object with mostly null fields save for username and password.

    The method retrieves a record from the database with username matching the one that the user
    provided in the changePasswordForm. If that operation succeeds (i.e. userRecordStoredInDatabase
    is not null), then the user provided username is valid and the password can be updated and stored
    in the database record that was retrieved earlier.

    It is important to not that we can only save userRecordStoredInDatabase and not user, because the
    latter has null fields, which in the database are defined to not be nullable. So, trying to save
    user in the database will raise errors. Thus, we update and save userRecordStoredInDatabase, because
    all of its fields are not null.
     */
    public void changeAccountPassword(Users user){
        Users userRecordStoredInDatabase = getUserRecordStoredInTheDatabase(user);
        if (userRecordStoredInDatabase != null){
            hashPasswordChange(user);
            usersRepo.save(userRecordStoredInDatabase);
        }
    }
}
