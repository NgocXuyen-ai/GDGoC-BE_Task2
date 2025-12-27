package com.example.todo_app.repository;

import com.example.todo_app.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoItem, String> {
    // Tự động có sẵn các hàm save(), findAll(), deleteById()
}