package com.example.todoauthentication.service;

import com.example.todoauthentication.DefaultResponse;
import com.example.todoauthentication.entity.SignupEntity;
import com.example.todoauthentication.model.SignupModel;
import com.example.todoauthentication.repository.AuthenticationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.LogRecord;

@Service
public class AuthenticationService {


    @Autowired
    private AuthenticationRepository authenticationRepository;  //Repository


    public String signUpService(SignupModel signupModelBean, HttpServletRequest request) throws JsonProcessingException {
        DefaultResponse defaultResponse = new DefaultResponse();
        String status;
        String userName = signupModelBean.getUsername();
        HttpSession httpSession = request.getSession();
        List<SignupEntity> signups =  authenticationRepository.findAllByUsername(userName);
        if(signups ==null || signups.isEmpty()) {
            status = "success";
            SignupEntity signupEntity = new SignupEntity();
            signupEntity.setFullname(signupModelBean.getFullname());
            signupEntity.setPassword(signupModelBean.getPassword());
            signupEntity.setUsername(signupModelBean.getUsername());
            authenticationRepository.save(signupEntity);
            httpSession.setAttribute("username",signupEntity.getUsername());

        }else{

            status = "username already exists";
        }
        defaultResponse.setMessage(status);
        ObjectMapper objmap = new ObjectMapper();
        status = objmap.writeValueAsString(defaultResponse);
        return status;

    }

    public String SignInService(SignupModel signupModel, HttpServletRequest request) throws JsonProcessingException {
        String status;
        HttpSession httpSession = request.getSession();
        List<SignupEntity> signinCredentials = authenticationRepository.findAllByUsernameAndPassword(signupModel.getUsername(),signupModel.getPassword());
        if(!signinCredentials.isEmpty()){
            status = "success";
            httpSession.setAttribute("username", signupModel.getUsername());
        }else{
            status = "password or username error";

        }
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setMessage(status);
        ObjectMapper objmap = new ObjectMapper();
        status = objmap.writeValueAsString(defaultResponse);
        return status;
    }

    public String Logout(HttpServletRequest request) throws JsonProcessingException {
        request.getSession().invalidate();
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setMessage("success");
        ObjectMapper objmap = new ObjectMapper();
        return objmap.writeValueAsString(defaultResponse);
    }


//    public SignupModel forgotPassword(SignupModel signupModel) {
//
//        List<SignupEntity> signupEntities = authenticationRepository.findAllByUsernameAndFullname(signupModel.getUsername(),signupModel.getFullname());
//        for(SignupEntity signupEntity: signupEntities){
//            signupModel.setPassword(signupEntity.getPassword());
//        }
//        return signupModel;
//    }
}
