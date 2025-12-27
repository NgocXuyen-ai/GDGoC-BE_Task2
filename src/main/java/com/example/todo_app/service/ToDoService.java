package com.example.todo_app.service;

import com.example.todo_app.model.ToDoItem;
import com.example.todo_app.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository repository;

    public List<ToDoItem> getTodos() {
        return repository.findAll();
    }

    public ToDoItem addTodo(String title) {
        return repository.save(new ToDoItem(title));
    }

    public ToDoItem updateTodo(String id, ToDoItem updateData) {
        List<ToDoItem> todos = repository.findAll();
        Optional<ToDoItem> existingTodo = todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();

        if (existingTodo.isPresent()) {
            ToDoItem item = existingTodo.get();
            if (updateData.getTitle() != null) item.setTitle(updateData.getTitle());
            item.setCompleted(updateData.isCompleted());
            return item;
        }
        return null;
    }

    public void deleteTodo(String id) {
        repository.deleteById(id);
    }
}