package com.example.todoauthentication.service;

import com.example.todoauthentication.DefaultResponse;
import com.example.todoauthentication.entity.SignupEntity;
import com.example.todoauthentication.model.SignupModel;
import com.example.todoauthentication.repository.AuthenticationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.LogRecord;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;  //Repository


    public String signUpService(SignupModel signupModelBean) throws JsonProcessingException {
        DefaultResponse defaultResponse = new DefaultResponse();
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
        defaultResponse.setMessage(status);
        ObjectMapper objmap = new ObjectMapper();
        status = objmap.writeValueAsString(defaultResponse);
        return status;

    }

    public String SignInService(SignupModel signupModel) throws JsonProcessingException {
        String status;
        List<SignupEntity> signinCredentials = authenticationRepository.findAllByUsernameAndPassword(signupModel.getUsername(),signupModel.getPassword());
        if(!signinCredentials.isEmpty()){
            status = "signin success";

        }else{
            status = "password or username error";

        }
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setMessage(status);
        ObjectMapper objmap = new ObjectMapper();
        status = objmap.writeValueAsString(defaultResponse);
        return status;
    }
}
