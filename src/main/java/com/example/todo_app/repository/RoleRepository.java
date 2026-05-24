package com.example.todo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
