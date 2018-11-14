package com.example.todoauthentication.repository;

import com.example.todoauthentication.entity.TodoEntity;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface TodoEventRepository extends JpaRepository<TodoEntity, Long>{


    List<TodoEntity> findAllByUsername(String username);
    List<TodoEntity> findAllByUsernameAndTodoDate(String username, Date date);
    List<TodoEntity> findAllByUsernameAndTodoDateAndTodoTitle(String username, Date date,String todoTitle);

}
