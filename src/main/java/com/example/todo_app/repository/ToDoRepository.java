package com.example.todo_app.repository;
import com.example.todo_app.model.ToDoItem;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository 
public class ToDoRepository {
    private List<ToDoItem> todoList = new ArrayList<>();
    
    private int currentId = 1;

    public List<ToDoItem> findAll() {
        return todoList;
    }
    public ToDoItem save(ToDoItem item) {
        item.setId(currentId++);
        todoList.add(item);
        return item;
    }

    public Optional<ToDoItem> findById(int id) {
        return todoList.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst();
    }

    public void deleteById(int id) {
        todoList.removeIf(todo -> todo.getId() == id);
    }
}

