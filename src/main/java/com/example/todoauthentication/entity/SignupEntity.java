package com.example.todoauthentication.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name="authentication_details")
public class SignupEntity {
    @Column
    private String  username;
    @Column
    private  String fullname;
    @Column
    private String password;

}
