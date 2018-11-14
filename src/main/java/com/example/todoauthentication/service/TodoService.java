package com.example.todoauthentication.service;

import com.example.todoauthentication.DefaultResponse;
import com.example.todoauthentication.entity.TodoEntity;
import com.example.todoauthentication.model.TodoListFrontModel;
import com.example.todoauthentication.model.DateOfEvent;
import com.example.todoauthentication.model.TodoEventModel;
import com.example.todoauthentication.model.TodoListModel;
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

    public List<TodoListModel> getList(HttpServletRequest request) {//to get to-do
        List<TodoListModel> todoList = new ArrayList<TodoListModel>();
        List<TodoEntity> entityList = null;
        HttpSession httpSession = request.getSession();
        Date date = (Date)httpSession.getAttribute("date");
        String username = httpSession.getAttribute("username").toString();
        entityList = todoEventRepository.findAllByUsernameAndTodoDate(username,date);
        for (TodoEntity todo:entityList) {
            TodoListModel todoListModel = new TodoListModel();
            todoListModel.setMessage(todo.getTodoTitle());
            todoListModel.setStatus(todo.getTodoStatus());
            todoList.add(todoListModel);
        }
        return todoList;
    }

    public List<TodoListFrontModel> getAllTodo(HttpServletRequest request) {
        List<TodoEntity> todoList = null;
        List<TodoListFrontModel> todoFront= new ArrayList<TodoListFrontModel>();;
        HttpSession httpSession = request.getSession();
        String username = httpSession.getAttribute("username").toString();
        todoList = todoEventRepository.findAllByUsername(username);
        System.out.println(todoFront.isEmpty());
        Date date = new Date();
//        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        for (TodoEntity todols: todoList) {
            String color = null;
            if (todols.getTodoStatus()) {
                color= "green";
            }else{
                if(todols.getTodoDate().after(date)){
                    color = "yellow";
                }else {
                    color = "red";
                }
            }
            TodoListFrontModel todoListFrontModel = new TodoListFrontModel();
            todoListFrontModel.setTitle(todols.getTodoTitle());
            todoListFrontModel.setStart(todols.getTodoDate());
            todoListFrontModel.setColor(color);
            todoListFrontModel.setTextColor("black");
            todoFront.add(todoListFrontModel);
        }
        System.out.println(todoFront.toString());
        return todoFront;
    }

    public String updateStatus(TodoEventModel todoEventModel, HttpServletRequest request ) throws JsonProcessingException {
        String status = null;
        HttpSession httpSession = request.getSession();
        List<TodoEntity> todoList = todoEventRepository.findAllByUsernameAndTodoDateAndTodoTitle(httpSession.getAttribute("username").toString(),(Date)httpSession.getAttribute("date"),todoEventModel.getTodoTitle());
        if(todoEventModel!=null && !todoList.isEmpty()) {
            for(TodoEntity todoEntity: todoList) {
                todoEntity.setTodoStatus(todoEventModel.getTodoStatus());
                todoEventRepository.save(todoEntity);
                status = "success";
            }
        }else{
            status = "status cannot be updated.";


        }
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setMessage(status);
        ObjectMapper objmap = new ObjectMapper();
        status = objmap.writeValueAsString(defaultResponse);
        return status;
    }

    public String removeTodo(String todoTitle, HttpServletRequest request) throws JsonProcessingException {
        String status = null;
        HttpSession httpSession = request.getSession();
        List<TodoEntity> todoList = todoEventRepository.findAllByUsernameAndTodoDateAndTodoTitle(httpSession.getAttribute("username").toString(),(Date)httpSession.getAttribute("date"),todoTitle);
        if(!todoList.isEmpty() ) {
            for(TodoEntity todoEntity: todoList) {
                todoEventRepository.delete(todoEntity);
                status = "success";
            }
        }else{
            status = "todo cannot be deleted.";


        }
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setMessage(status);
        ObjectMapper objmap = new ObjectMapper();
        status = objmap.writeValueAsString(defaultResponse);
        return status;
    }

    public String assignDate(DateOfEvent date, HttpServletRequest request) throws JsonProcessingException {
        HttpSession httpSession = request.getSession();
        System.err.println(date.toString());
        httpSession.setAttribute("date",date.getDate());
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setMessage("success");
        ObjectMapper objmap = new ObjectMapper();
        return objmap.writeValueAsString(defaultResponse);
    }
}
