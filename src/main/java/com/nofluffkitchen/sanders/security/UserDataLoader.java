package com.nofluffkitchen.sanders.security;

import com.nofluffkitchen.sanders.data.UserRepository;
import com.nofluffkitchen.sanders.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDataLoader implements CommandLineRunner {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataLoader(UserRepository userRepo, PasswordEncoder passwordEncoder ){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String...args) throws Exception{
        /*User user0 = new User("123@123.com", "User", passwordEncoder.encode("password"), "First", "Last");

        user0.setRoles(Set.of(User.Role.ROLE_ADMIN));
        user0.setEnabled(true);
        userRepo.save(user0);


        User user1 = new User("user1@123.com", "User1", passwordEncoder.encode("password"), "user1", "user1");

        user1.setRoles(Set.of(User.Role.ROLE_USER));
        user1.setEnabled(true);
        userRepo.save(user1);*/

    }
}
