package com.example.todo_app.controller;
import com.example.todo_app.model.ToDoItem;
import com.example.todo_app.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    @Autowired
    private ToDoService service;

    @GetMapping
    public List<ToDoItem> getAll() {
        return service.getTodos();
    }

    @PostMapping
    public ToDoItem create(@RequestBody Map<String, String> request) {
        return service.addTodo(request.get("content"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteTodo(id);
    }
}
