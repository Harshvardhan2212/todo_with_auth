package com.example.todo_app.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.todo_app.dto.TodoDto;
import com.example.todo_app.entity.Todo;
import com.example.todo_app.repository.TodoRepository;
import com.example.todo_app.service.TodoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

  private final TodoRepository todoRepository;

  private final ModelMapper modelMapper;

  @Override
  public TodoDto create(TodoDto todoDto) {
    Todo savedTodo = todoRepository.save(modelMapper.map(todoDto, Todo.class));
    return modelMapper.map(savedTodo, TodoDto.class);
  }

  @Override
  public TodoDto getById(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("todo does not exist with id: " + id));
    return modelMapper.map(todo, TodoDto.class);
  }

  @Override
  public TodoDto update(Long id, TodoDto todoDto) {

    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("todo does not exist with id: " + id));
    todo.setTitle(todoDto.title());
    todo.setDescirption(todoDto.description());
    todo.setCompleted(todoDto.completed());
    Todo savedTodo = todoRepository.save(todo);
    return modelMapper.map(savedTodo, TodoDto.class);
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
    return todos.stream().map(todo -> modelMapper.map(todo, TodoDto.class)).toList();
  }

  @Override
  public TodoDto complete(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("todo does not exist with id: " + id));
    todo.setCompleted(true);
    Todo savedTodo = todoRepository.save(todo);
    return modelMapper.map(savedTodo, TodoDto.class);
  }

  @Override
  public TodoDto inComplete(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("todo does not exist with id: " + id));
    todo.setCompleted(false);
    Todo savedTodo = todoRepository.save(todo);
    return modelMapper.map(savedTodo, TodoDto.class);
  }

}
