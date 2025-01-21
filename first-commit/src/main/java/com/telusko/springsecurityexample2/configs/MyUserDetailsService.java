package com.telusko.springsecurityexample2.configs;

import com.telusko.springsecurityexample2.entities.User;
import com.telusko.springsecurityexample2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    //charger les informations d'un utilisateur à partir d'une source de données (comme une base de données) en fonction de son nom d'utilisateur (username)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repo.findByUsername(username);
        if(user==null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new MyUserDetails(user);
    }
}
