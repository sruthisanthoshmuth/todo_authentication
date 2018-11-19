package com.example.todoauthentication.repository;

import com.example.todoauthentication.entity.SignupEntity;
import com.example.todoauthentication.entity.TodoEntity;
import com.example.todoauthentication.model.SignupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthenticationRepository extends JpaRepository<SignupEntity, String> {


    
     List<SignupEntity> findBy();

    void save(SignupModel signupModelBean);

    List<SignupEntity> findAllByUsername(String userName);
    List<SignupEntity> findAllByUsernameAndPassword(String userName, String passWord);


    List<SignupEntity> findAllByUsernameAndFullname(String username, String fullname);
}
