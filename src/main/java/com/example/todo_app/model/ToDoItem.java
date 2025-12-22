package com.example.todo_app.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItem {
    private int id;
    private String content;
    private boolean completed;
}
