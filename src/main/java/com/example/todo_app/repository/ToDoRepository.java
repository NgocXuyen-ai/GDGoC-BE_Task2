package com.example.todo_app.repository;
import com.example.todo_app.model.ToDoItem;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // Đánh dấu đây là tầng dữ liệu
public class ToDoRepository {
    // Đây chính là "biến mảng global" lưu trữ dữ liệu trên RAM
    private List<ToDoItem> todoList = new ArrayList<>();
    
    // Biến để tự động tăng ID mỗi khi thêm mới
    private int currentId = 1;

    // Lấy toàn bộ danh sách
    public List<ToDoItem> findAll() {
        return todoList;
    }

    // Lưu một Todo mới
    public ToDoItem save(ToDoItem item) {
        item.setId(currentId++);
        todoList.add(item);
        return item;
    }

    // Tìm một Todo theo ID (dùng cho Sửa hoặc Xóa)
    public Optional<ToDoItem> findById(int id) {
        return todoList.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst();
    }

    // Xóa Todo theo ID
    public void deleteById(int id) {
        todoList.removeIf(todo -> todo.getId() == id);
    }
}
