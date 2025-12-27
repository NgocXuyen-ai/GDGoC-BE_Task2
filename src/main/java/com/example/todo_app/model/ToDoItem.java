package com.example.todo_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItem {
    private String id; 
    private String title; 
    private boolean completed;
    private long createdAt;

    public ToDoItem(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.completed = false;
        this.createdAt = Instant.now().getEpochSecond();
    }
}