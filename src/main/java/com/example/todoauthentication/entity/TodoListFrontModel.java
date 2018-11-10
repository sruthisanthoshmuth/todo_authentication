package com.example.todoauthentication.entity;

import java.util.Date;

public class TodoListFrontModel {
    private String title;
    private Date start;
    private String cssColor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getCssColor() {
        return cssColor;
    }

    public void setCssColor(String cssColor) {
        this.cssColor = cssColor;
    }
}
