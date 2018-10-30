package com.example.todoauthentication.repository;

import com.example.todoauthentication.entity.SignupEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface Authentication extends JpaRepository<SignupEntity, String> {


    Iterable<SignupEntity> findAllByUsername();
     List<SignupEntity> findBy();

}
