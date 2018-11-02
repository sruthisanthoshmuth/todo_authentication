package com.example.todoauthentication.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Entity
@Table(name = "todo_list")
public class TodoEntity {
    @Column
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @NotNull
    private String username;

    @Column(name = "todo_date")
    @NotNull
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date todoDate;

    @Column(name = "todo_title")
    @NotNull
    private String todoTitle;

    @Column(name = "todo_status")
    @NotNull
    private String todoStatus;
}
