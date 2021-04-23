package com.nofluffkitchen.sanders.security;

import com.nofluffkitchen.sanders.data.UserRepository;
import com.nofluffkitchen.sanders.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepo){

        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username);
        User user = userRepo.findByEmail(email);
        if (user == null)
            //throw new UsernameNotFoundException("User " + username + " not found");
            throw new UsernameNotFoundException("Email " + email + " not found");
        return user;
    }
}
