package com.example.todo_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(String userName);

  Boolean existsByEmail(String email);

  Optional<User> findByUserNameOrEmail(String name, String email);
}
