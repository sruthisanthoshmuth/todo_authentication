package com.example.todoauthentication.controller;

import com.example.todoauthentication.entity.TodoEntity;
import com.example.todoauthentication.model.*;
import com.example.todoauthentication.service.AuthenticationService;
import com.example.todoauthentication.service.TodoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AuthenticationController {

    @Autowired
    private  AuthenticationService authenticationService;

    @Autowired
    private TodoService todoService;


    @PostMapping(value = "/signup")
   private String signup(@RequestBody SignupModel signupModelBean, HttpServletRequest request) throws JsonProcessingException {


         return authenticationService.signUpService(signupModelBean, request);

    }

    @PostMapping(value = "/signin")
    private String signin(@RequestBody SignupModel signupModel, HttpServletRequest request) throws JsonProcessingException {
        return authenticationService.SignInService(signupModel, request);
    }

//        { "username":"user", "todoTitle":"todoos", "todoDate":"2012-08-20" }
    @PostMapping(value = "/addtodo")
    private String addTodo(@RequestParam String todoTitle, HttpServletRequest request) throws JsonProcessingException {
        return todoService.addTodoEvent(todoTitle, request);
    }


    @GetMapping(value = "/todolist")
    private List<TodoListModel> getTodoList(HttpServletRequest request){
        return todoService.getList(request);
    }


    @GetMapping(value = "/todolistall")
    private  List<TodoListFrontModel> getCalendarList(HttpServletRequest request){
        return todoService.getAllTodo(request);

    }


    @PostMapping(value = "/editstatus")
    private String changeStatus(@RequestBody TodoEventModel todoEventModel, HttpServletRequest request) throws JsonProcessingException {
        return todoService.updateStatus(todoEventModel,request);
    }

    @GetMapping(value = "/deletetodo")
    private String deleteTodo(@RequestParam String todoTitle, HttpServletRequest request) throws JsonProcessingException {
        return todoService.removeTodo(todoTitle,request);
    }

    @PostMapping(value = "/adddate")
    private String addDate(@RequestBody DateOfEvent date, HttpServletRequest request) throws JsonProcessingException {
        return todoService.assignDate(date, request);
    }



}
