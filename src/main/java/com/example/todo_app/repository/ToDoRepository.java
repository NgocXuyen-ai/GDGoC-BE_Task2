package com.example.todo_app.repository;

import com.example.todo_app.model.ToDoItem;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDoRepository {
    private List<ToDoItem> todoList = new ArrayList<>();

    public List<ToDoItem> findAll() {
        return todoList;
    }

    public ToDoItem save(ToDoItem item) {
        todoList.add(item);
        return item;
    }

    public void deleteById(String id) {
        todoList.removeIf(todo -> todo.getId().equals(id));
    }

    public void updateList() {
    }
}