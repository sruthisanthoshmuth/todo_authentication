package com.example.todoauthentication.service;

import com.example.todoauthentication.entity.TodoEntity;
import com.example.todoauthentication.model.TodoEventModel;
import com.example.todoauthentication.repository.AuthenticationRepository;
import com.example.todoauthentication.repository.TodoEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoEventRepository todoEventRepository;


    public String addTodoEvent(TodoEventModel todoEventModel) {

        String status;

        if(todoEventModel!=null ) {
            TodoEntity todoEntity = new TodoEntity();
            todoEntity.setTodoDate(todoEventModel.getTodoDate());
            todoEntity.setUsername(todoEventModel.getUsername());
            todoEntity.setTodoTitle(todoEventModel.getTodoTitle());
            todoEntity.setTodoStatus("Not Completed.");
            todoEventRepository.save(todoEntity);
            status = "success";
        }else{
            status = "title column cannot be null";


        }
        return status;



    }

    public List<TodoEntity> getList(String username, Date date) {
        List<TodoEntity> todoList = null;
        todoList = todoEventRepository.findAllByUsernameAndTodoDate(username,date);
        return todoList;
    }

    public List<TodoEntity> getAllTodo(String username) {
        List<TodoEntity> todoList = null;
        todoList = todoEventRepository.findAllByUsername(username);
        return todoList;
    }
}
