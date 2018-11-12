package com.example.todoauthentication.controller;

import com.example.todoauthentication.entity.TodoEntity;
import com.example.todoauthentication.entity.TodoListFrontModel;
import com.example.todoauthentication.model.SignupModel;
import com.example.todoauthentication.model.TodoEventModel;
import com.example.todoauthentication.service.AuthenticationService;
import com.example.todoauthentication.service.TodoService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
   private String signup(@RequestBody SignupModel signupModelBean) throws JsonProcessingException {

         return authenticationService.signUpService(signupModelBean);

    }

    @PostMapping(value = "/signin")
    private String signin(@RequestBody SignupModel signupModel) throws JsonProcessingException {
        return authenticationService.SignInService(signupModel);
    }

//        { "username":"user", "todoTitle":"todoos", "todoDate":"2012-08-20" }
    @PostMapping(value = "/addtodo")
    private String addTodo(@RequestBody TodoEventModel todoEventModel) throws JsonProcessingException {
        return todoService.addTodoEvent(todoEventModel);
    }


    @GetMapping(value = "/todolist")
    private List<TodoEntity> getTodoList(@RequestParam String username, @RequestParam Date date){
        return todoService.getList(username,date);
    }


    @GetMapping(value = "/todolistall")
    private  List<TodoListFrontModel> getCalendarList(@RequestParam String username){
        return todoService.getAllTodo(username);

    }


    @PostMapping(value = "/editstatus")
    private String changeStatus(@RequestBody TodoEventModel todoEventModel) throws JsonProcessingException {
        return todoService.updateStatus(todoEventModel);
    }

    @PostMapping(value = "/deletetodo")
    private String deleteTodo(@RequestBody TodoEventModel todoEventModel){
        return todoService.removeTodo(todoEventModel);
    }



}
