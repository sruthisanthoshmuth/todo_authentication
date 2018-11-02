package com.example.todoauthentication.service;

import com.example.todoauthentication.entity.SignupEntity;
import com.example.todoauthentication.model.SignupModel;
import com.example.todoauthentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;  //Repository


    public String signUpService(SignupModel signupModelBean) {
        String status;
        String userName = signupModelBean.getUsername();
        List<SignupEntity> signups =  authenticationRepository.findAllByUsername(userName);
        if(signups ==null || signups.isEmpty()) {
            status = "success";
            SignupEntity signupEntity = new SignupEntity();
            signupEntity.setFullname(signupModelBean.getFullname());
            signupEntity.setPassword(signupModelBean.getPassword());
            signupEntity.setUsername(signupModelBean.getUsername());
            authenticationRepository.save(signupEntity);

        }else{

            status = "username already exists";
        }
        return status;

    }

    public String SignInService(SignupModel signupModel) {
        String status;
        List<SignupEntity> signinCredentials = authenticationRepository.findAllByUsernameAndPassword(signupModel.getUsername(),signupModel.getPassword());
        if(!signinCredentials.isEmpty()||signinCredentials != null){
            status = "signin success";

        }else{
            status = "password or username error";

        }
        return status;
    }
}
