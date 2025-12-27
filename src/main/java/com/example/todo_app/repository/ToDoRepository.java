package com.example.todo_app.repository;

import com.example.todo_app.model.ToDoItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class ToDoRepository {
    private List<ToDoItem> todoList = new ArrayList<>();
    private final String FILE_PATH = "data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            System.out.println(">>> File data.json not found. Initializing new list.");
            todoList = new ArrayList<>();
            return;
        }

        try {
            todoList = objectMapper.readValue(file, new TypeReference<List<ToDoItem>>() {});
            System.out.println(">>> Data loaded successfully.");
        } catch (IOException e) {
            System.err.println(">>> ERROR: data.json is corrupted! Initializing empty list to prevent crash.");
            todoList = new ArrayList<>();
        }
    }

    public List<ToDoItem> findAll() {
        return todoList;
    }

    public ToDoItem save(ToDoItem item) {
        todoList.add(item);
        saveToFileAsync(); 
        return item;
    }

    public void deleteById(String id) {
        todoList.removeIf(todo -> todo.getId().equals(id));
        saveToFileAsync(); 
    }

    public void saveToFileAsync() {
        CompletableFuture.runAsync(() -> {
            try {
                objectMapper.writeValue(new File(FILE_PATH), todoList);
                System.out.println(">>> Data saved to file (Asynchronously).");
            } catch (IOException e) {
                System.err.println(">>> Failed to save data: " + e.getMessage());
            }
        });
    }
}