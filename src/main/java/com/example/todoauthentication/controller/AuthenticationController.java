package com.example.todoauthentication.controller;

import com.example.todoauthentication.entity.TodoEntity;
import com.example.todoauthentication.model.SignupModel;
import com.example.todoauthentication.model.TodoEventModel;
import com.example.todoauthentication.service.AuthenticationService;
import com.example.todoauthentication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AuthenticationController {

    @Autowired
    private  AuthenticationService authenticationService;

    @Autowired
    private TodoService todoService;


    @PostMapping(value = "/signup")
   private String signup(@RequestBody SignupModel signupModelBean){

         return authenticationService.signUpService(signupModelBean);

    }

    @PostMapping(value = "/signin")
    private String signin(@RequestBody SignupModel signupModel){
        return authenticationService.SignInService(signupModel);
    }

//        { "username":"user", "todoTitle":"todoos", "todoDate":"2012-08-20" }
    @PostMapping(value = "/addtodo")
    private String addTodo(@RequestBody TodoEventModel todoEventModel){
        return todoService.addTodoEvent(todoEventModel);
    }


    @GetMapping(value = "/todolist")
    private List<TodoEntity> getTodoList(@RequestParam String username, @RequestParam Date date){
        return todoService.getList(username,date);
    }


    @GetMapping(value = "/todolistall")
    private  List<TodoEntity> getCalendarList(@RequestParam String username){
        return todoService.getAllTodo(username);
    }


}
