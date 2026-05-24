package com.example.todo_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
  @PreAuthorize("permitAll()")
  public ResponseEntity<List<TodoDto>> get() {
    List<TodoDto> todos = todoService.get();
    return ResponseEntity.ok(todos);
  }

  @GetMapping("/{id}")
  @PreAuthorize("permitAll()")
  public ResponseEntity<TodoDto> getById(@PathVariable Long id) {
    TodoDto todo = todoService.getById(id);
    return ResponseEntity.ok(todo);
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<TodoDto> create(@RequestBody TodoDto todoDto) {
    TodoDto todo = todoService.create(todoDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(todo);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<TodoDto> update(@PathVariable Long id, @PathVariable TodoDto todoDto) {
    TodoDto todo = todoService.update(id, todoDto);
    return ResponseEntity.ok(todo);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
    todoService.delete(id);
    return ResponseEntity.ok(Map.of("Message", "Todo Deleted successfully"));
  }

  @PatchMapping("/{id}/complete")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<TodoDto> complete(@PathVariable Long id) {
    TodoDto todo = todoService.complete(id);
    return ResponseEntity.ok(todo);
  }

  @PatchMapping("/{id}/incomplete")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<TodoDto> inComplete(@PathVariable Long id) {
    TodoDto todo = todoService.inComplete(id);
    return ResponseEntity.ok(todo);
  }
}
