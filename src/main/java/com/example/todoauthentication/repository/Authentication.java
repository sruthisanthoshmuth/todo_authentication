package com.example.todoauthentication.repository;

import com.example.todoauthentication.entity.SignupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Authentication extends JpaRepository<SignupEntity, String>{
}
