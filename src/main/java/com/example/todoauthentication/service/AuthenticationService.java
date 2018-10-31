package com.example.todoauthentication.service;

import com.example.todoauthentication.entity.SignupEntity;
import com.example.todoauthentication.model.Signup;
import com.example.todoauthentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;  //Repository


    public String signUpService(Signup signupBean) {
        String status;
        String userName = signupBean.getUsername();
        List<SignupEntity> signups =  authenticationRepository.findAllByUsername(userName);
        if(signups ==null || signups.isEmpty()) {
            status = "success";
            SignupEntity signupEntity = new SignupEntity();
            signupEntity.setFullname(signupBean.getFullname());
            signupEntity.setPassword(signupBean.getPassword());
            signupEntity.setUsername(signupBean.getUsername());
            authenticationRepository.save(signupEntity);

        }else{

            status = "username already exists";
        }
        return status;

    }
}
