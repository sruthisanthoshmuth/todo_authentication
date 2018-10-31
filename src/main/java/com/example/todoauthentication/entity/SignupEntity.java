package com.example.todoauthentication.entity;



import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="authentication_details")
public class SignupEntity {
    @Column
    @NotNull
    @Id
    private String  username;
    @NotNull
    @Column
    private  String fullname;
    @Column
    @NotNull
    private String password;

}
