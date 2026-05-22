package com.example.todo_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_app.dto.TodoDto;
import com.example.todo_app.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/todo")
@AllArgsConstructor
public class TodoController {
  private final TodoService todoService;

  @GetMapping
  public ResponseEntity<List<TodoDto>> get() {
    List<TodoDto> todos = todoService.get();
    return ResponseEntity.ok(todos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TodoDto> getById(@PathVariable Long id) {
    TodoDto todo = todoService.getById(id);
    return ResponseEntity.ok(todo);
  }

  @PostMapping
  public ResponseEntity<TodoDto> create(@RequestBody TodoDto todoDto) {
    TodoDto todo = todoService.create(todoDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(todo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TodoDto> update(@PathVariable Long id, @PathVariable TodoDto todoDto) {
    TodoDto todo = todoService.update(id, todoDto);
    return ResponseEntity.ok(todo);
  }
}
