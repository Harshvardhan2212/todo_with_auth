package com.example.todo_app.dto;

public record TodoDto(Long id, String title, String description, boolean completed) {
}
