package com.example.todoauthentication.repository;

import com.example.todoauthentication.entity.SignupEntity;
import com.example.todoauthentication.model.Signup;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
@Repository
public interface Authentication extends JpaRepository<SignupEntity, String> {


    
     List<SignupEntity> findBy();

    void save(Signup signupBean);

    List<SignupEntity> findAllByUsername(String userName);
}
