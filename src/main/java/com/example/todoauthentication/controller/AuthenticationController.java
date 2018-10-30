package com.example.todoauthentication.controller;

import com.example.todoauthentication.entity.SignupEntity;
import com.example.todoauthentication.model.Signup;
import com.example.todoauthentication.repository.Authentication;
import com.example.todoauthentication.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticationController {

    @PostMapping(path = "/Signup")
   private void signup(@RequestBody Signup signupBean){
        new AuthenticationService().signUpService(signupBean);


    }

}
