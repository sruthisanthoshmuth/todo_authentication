package com.example.todoauthentication.service;

import com.example.todoauthentication.DefaultResponse;
import com.example.todoauthentication.entity.TodoEntity;
import com.example.todoauthentication.entity.TodoListFrontModel;
import com.example.todoauthentication.model.TodoEventModel;
import com.example.todoauthentication.repository.TodoEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoEventRepository todoEventRepository;


    public String addTodoEvent(String todoTitle, HttpServletRequest request) throws JsonProcessingException {

        String status;
        HttpSession httpSession = request.getSession();
        if(todoTitle != null || todoTitle != "") {
            TodoEntity todoEntity = new TodoEntity();
            Date date = (Date) httpSession.getAttribute("date");
            todoEntity.setTodoDate(date);
            todoEntity.setUsername(httpSession.getAttribute("username").toString());
            todoEntity.setTodoTitle(todoTitle);
            todoEntity.setTodoStatus(false);
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

    public List<TodoEntity> getList(HttpServletRequest request) {//to get to-do
        List<TodoEntity> todoList = null;
        HttpSession httpSession = request.getSession();
        Date date = (Date)httpSession.getAttribute("date");
        String username = httpSession.getAttribute("username").toString();
        todoList = todoEventRepository.findAllByUsernameAndTodoDate(username,date);
        return todoList;
    }

    public List<TodoListFrontModel> getAllTodo(HttpServletRequest request) {
        List<TodoEntity> todoList = null;
        List<TodoListFrontModel> todoFront= new ArrayList<TodoListFrontModel>();;
        HttpSession httpSession = request.getSession();
        String username = httpSession.getAttribute("username").toString();
        todoList = todoEventRepository.findAllByUsername(username);
        System.out.println(todoFront.isEmpty());
        for (TodoEntity todols: todoList) {
            String color = null;
            if (todols.getTodoStatus()) {
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

    public String updateStatus(TodoEventModel todoEventModel, HttpServletRequest request ) throws JsonProcessingException {
        String status;
        HttpSession httpSession = request.getSession();
        if(todoEventModel!=null ) {
            TodoEntity todoEntity = new TodoEntity();
            todoEntity.setTodoDate((Date)httpSession.getAttribute("date"));
            todoEntity.setUsername(httpSession.getAttribute("username").toString());
            todoEntity.setTodoTitle(todoEventModel.getTodoTitle());
            todoEntity.setTodoStatus(todoEventModel.getTodoStatus());
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

    public String removeTodo(String todoTitle, HttpServletRequest request) {
        String status;
        HttpSession httpSession = request.getSession();
        if(todoTitle!=null||todoTitle!="" ) {
            TodoEntity todoEntity = new TodoEntity();
            Date date =(Date) httpSession.getAttribute("date");
            todoEntity.setTodoDate(date);
            todoEntity.setUsername(httpSession.getAttribute("username").toString());
            todoEntity.setTodoTitle(todoTitle);
            todoEventRepository.delete(todoEntity);
            status = "success";
        }else{
            status = "todo cannot be deleted.";


        }
        return status;
    }

    public String assignDate(Date date, HttpServletRequest request) throws JsonProcessingException {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("date",date);
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setMessage("success");
        ObjectMapper objmap = new ObjectMapper();
        return objmap.writeValueAsString(defaultResponse);
    }
}
