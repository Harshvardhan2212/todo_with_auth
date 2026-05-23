package com.example.todo_app.service;

import java.util.List;

import com.example.todo_app.dto.TodoDto;

public interface TodoService {

  TodoDto create(TodoDto todoDto);

  TodoDto getById(Long id);

  TodoDto update(Long id, TodoDto todoDto);

  void delete(Long id);

  List<TodoDto> get();

  TodoDto complete(Long id);

  TodoDto inComplete(Long id);
}
