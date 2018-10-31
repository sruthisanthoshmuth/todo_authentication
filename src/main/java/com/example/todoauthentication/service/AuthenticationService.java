package com.example.todoauthentication.service;

import com.example.todoauthentication.model.Signup;
import com.example.todoauthentication.repository.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private Authentication authentication;
    public void signUpService(Signup signupBean) {
        String userName = signupBean.getUsername();
        if(authentication.findAllByUsername(userName)==null) {
            authentication.save(signupBean);
        }


    }
}
