package com.example.todo_app.mapper;

import com.example.todo_app.dto.TodoDto;
import com.example.todo_app.entity.Todo;

public class TodoMapper {
  public static TodoDto toDto(Todo todo) {
    return new TodoDto(
        todo.getId(),
        todo.getTitle(),
        todo.getDescirption(),
        todo.isCompleted());
  }

  public static Todo toEntity(TodoDto todoDto) {
    return new Todo(
        todoDto.id(),
        todoDto.title(),
        todoDto.description(),
        todoDto.completed());
  }
}
