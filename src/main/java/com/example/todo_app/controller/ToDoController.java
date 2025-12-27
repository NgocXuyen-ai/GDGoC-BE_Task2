package com.example.todo_app.controller;

import com.example.todo_app.model.ToDoItem;
import com.example.todo_app.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/todos") 
public class ToDoController {
    @Autowired
    private ToDoService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getTodos()); 
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) {
        try {
            String title = request.get("title");
            
            if (title == null || title.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Title is required"
                ));
            }

            ToDoItem newItem = service.addTodo(title.trim());
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "success", true,
                "data", newItem
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Internal Server Error"
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ToDoItem updateData) {
      ToDoItem updated = service.updateTodo(id, updateData);
     if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Task not found"));
         }
      return ResponseEntity.ok(Map.of("success", true, "data", updated));
}
}