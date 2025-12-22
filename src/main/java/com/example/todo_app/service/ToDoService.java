package com.example.todo_app.service;
import com.example.todo_app.model.ToDoItem;
import com.example.todo_app.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository repository;

    public List<ToDoItem> getTodos() {
        return repository.findAll();
    }

    public ToDoItem addTodo(String content) {
        return repository.save(new ToDoItem(0, content, false));
    }

    public void deleteTodo(int id) {
        repository.deleteById(id);
    }
}
