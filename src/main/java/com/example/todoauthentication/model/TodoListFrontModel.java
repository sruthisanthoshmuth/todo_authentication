package com.example.todoauthentication.model;

import lombok.Data;

import java.util.Date;
@Data
public class TodoListFrontModel {
    private String title;
    private Date start;
    private String color;
    private String textColor;

}
