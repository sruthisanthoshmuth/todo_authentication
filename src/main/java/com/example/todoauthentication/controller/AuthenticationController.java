package com.example.todoauthentication.controller;

import com.example.todoauthentication.model.Signup;
import com.example.todoauthentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private  AuthenticationService authenticationService;


    @PostMapping(value = "/signup")
   private void signup(@RequestBody Signup signupBean){
        authenticationService.signUpService(signupBean);


    }

}
