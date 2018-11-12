package com.example.todoauthentication.service;

import com.example.todoauthentication.DefaultResponse;
import com.example.todoauthentication.entity.TodoEntity;
import com.example.todoauthentication.entity.TodoListFrontModel;
import com.example.todoauthentication.model.TodoEventModel;
import com.example.todoauthentication.repository.AuthenticationRepository;
import com.example.todoauthentication.repository.TodoEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoEventRepository todoEventRepository;


    public String addTodoEvent(TodoEventModel todoEventModel) throws JsonProcessingException {

        String status;

        if(todoEventModel!=null ) {
            TodoEntity todoEntity = new TodoEntity();
            todoEntity.setTodoDate(todoEventModel.getTodoDate());
            todoEntity.setUsername(todoEventModel.getUsername());
            todoEntity.setTodoTitle(todoEventModel.getTodoTitle());
            todoEntity.setTodoStatus("Not Completed");
            todoEventRepository.save(todoEntity);
            status = "success";
        }else{
            status = "title column cannot be null";


        }
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setMessage(status);
        ObjectMapper objmap = new ObjectMapper();
        status = objmap.writeValueAsString(defaultResponse);
        return status;



    }

    public List<TodoEntity> getList(String username, Date date) {//to get to-do
        List<TodoEntity> todoList = null;
        todoList = todoEventRepository.findAllByUsernameAndTodoDate(username,date);
        return todoList;
    }

    public List<TodoListFrontModel> getAllTodo(String username) {
        List<TodoEntity> todoList = null;
        List<TodoListFrontModel> todoFront= new ArrayList<TodoListFrontModel>();;
        todoList = todoEventRepository.findAllByUsername(username);
        System.out.println(todoFront.isEmpty());
        for (TodoEntity todols: todoList) {
            String color = null;
            if (todols.getTodoStatus().equalsIgnoreCase("completed")) {
                color= "green";
            }else{
                color ="red";
            }
            TodoListFrontModel todoListFrontModel = new TodoListFrontModel();
            todoListFrontModel.setTitle(todols.getTodoTitle());
            todoListFrontModel.setStart(todols.getTodoDate());
            todoListFrontModel.setCssColor(color);
            todoFront.add(todoListFrontModel);
        }
        System.out.println(todoFront.toString());
        return todoFront;
    }

    public String updateStatus(TodoEventModel todoEventModel ) throws JsonProcessingException {
        String status;

        if(todoEventModel!=null ) {
            TodoEntity todoEntity = new TodoEntity();
            todoEntity.setTodoDate(todoEventModel.getTodoDate());
            todoEntity.setUsername(todoEventModel.getUsername());
            todoEntity.setTodoTitle(todoEventModel.getTodoTitle());
            todoEntity.setTodoStatus("Completed");
            todoEventRepository.save(todoEntity);
            status = "success";
        }else{
            status = "status cannot be updated.";


        }
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setMessage(status);
        ObjectMapper objmap = new ObjectMapper();
        status = objmap.writeValueAsString(defaultResponse);
        return status;
    }

    public String removeTodo(TodoEventModel todoEventModel) {
        String status;

        if(todoEventModel!=null ) {
            TodoEntity todoEntity = new TodoEntity();
            todoEntity.setTodoDate(todoEventModel.getTodoDate());
            todoEntity.setUsername(todoEventModel.getUsername());
            todoEntity.setTodoTitle(todoEventModel.getTodoTitle());
            todoEventRepository.delete(todoEntity);
            status = "success";
        }else{
            status = "todo cannot be deleted.";


        }
        return status;
    }
}
