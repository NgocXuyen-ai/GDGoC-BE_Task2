package com.example.todo_app.model; // Dòng 1 bắt buộc phải có

import jakarta.persistence.*; // Import tất cả @Entity, @Id, @Table
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
public class ToDoItem {
    @Id
    private String id;
    private String title;
    private boolean completed;

    public ToDoItem(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.completed = false;
    }
}