package com.example.todo_app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_app.dto.TodoDto;
import com.example.todo_app.entity.Todo;
import com.example.todo_app.mapper.TodoMapper;
import com.example.todo_app.repository.TodoRepository;
import com.example.todo_app.service.TodoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

  private final TodoRepository todoRepository;

  @Override
  public TodoDto create(TodoDto todoDto) {
    Todo savedTodo = todoRepository.save(TodoMapper.toEntity(todoDto));
    return TodoMapper.toDto(savedTodo);
  }

  @Override
  public TodoDto getById(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("todo does not exist with id: " + id));
    return TodoMapper.toDto(todo);
  }

  @Override
  public TodoDto update(Long id, TodoDto todoDto) {

    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("todo does not exist with id: " + id));
    todo.setTitle(todoDto.title());
    todo.setDescirption(todoDto.description());
    todo.setCompleted(todoDto.completed());
    Todo savedTodo = todoRepository.save(todo);
    return TodoMapper.toDto(savedTodo);
  }

  @Override
  public void delete(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("todo does not exist with id: " + id));
    todoRepository.delete(todo);
  }

  @Override
  public List<TodoDto> get() {
    List<Todo> todos = todoRepository.findAll();
    return todos.stream().map(TodoMapper::toDto).toList();
  }

}
