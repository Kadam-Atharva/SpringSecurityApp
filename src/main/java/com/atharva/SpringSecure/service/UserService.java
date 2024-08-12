package com.atharva.SpringSecure.service;

import com.atharva.SpringSecure.model.Users;
import com.atharva.SpringSecure.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;
    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return  repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

     if(authentication.isAuthenticated()) {
         return jwtService.generateToken(user.getUsername());
     }

     return "Failure";
    }
}
