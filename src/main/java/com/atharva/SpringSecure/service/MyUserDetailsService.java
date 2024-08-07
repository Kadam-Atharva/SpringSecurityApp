package com.atharva.SpringSecure.service;

import com.atharva.SpringSecure.model.UserPrincipal;
import com.atharva.SpringSecure.model.Users;
import com.atharva.SpringSecure.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user= repo.findByUsername(username);
        if(user==null){
            System.out.println("USE NOT FOUND !");
            throw new UsernameNotFoundException("USER NOT FOUND !");
        }

        return new UserPrincipal(user);
        }
    }

