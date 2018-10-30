package com.example.todoauthentication.service;

import com.example.todoauthentication.model.Signup;
import com.example.todoauthentication.repository.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public void signUpService(Signup signupBean) {
        String userName = signupBean.getUsername();


    }
}
