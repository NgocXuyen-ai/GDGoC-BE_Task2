
const API_URL = 'http://localhost:8080/api/v1/todos'; 

async function fetchTodos() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Failed to fetch data');
        const todos = await response.json();
        renderTodos(todos);
    } catch (error) {
        console.error("Error fetching data:", error);
        alert("Error: Could not connect to the server.");
    }
}

async function addTodo() {
    const input = document.getElementById('todoInput');
    const title = input.value.trim();

    if (!title) {
        return alert("Please enter a task title!");
    }

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title: title }) 
        });

        if (response.ok) {
            input.value = '';
            fetchTodos();
        } else {
            const errorData = await response.json();
            alert("Error: " + (errorData.message || "Could not add task"));
        }
    } catch (error) {
        console.error("Error adding todo:", error);
    }
}

async function deleteTodo(id) {
    if (!confirm("Are you sure you want to delete this task?")) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        if (response.ok) {
            fetchTodos();
        }
    } catch (error) {
        console.error("Error deleting task:", error);
    }
}

async function toggleComplete(id, currentStatus) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ completed: !currentStatus })
        });
        
        if (response.ok) {
            fetchTodos();
        }
    } catch (error) {
        console.error("Error updating task:", error);
    }
}

async function editTodo(id, currentTitle) {
    const newTitle = prompt("Edit your task:", currentTitle);
    
    if (newTitle === null || newTitle.trim() === "") return;

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title: newTitle.trim() }) 
        });

        if (response.ok) {
            fetchTodos();
        } else {
            alert("Failed to update task.");
        }
    } catch (error) {
        console.error("Error updating title:", error);
    }
}

function renderTodos(todos) {
    const list = document.getElementById('todoList');
    list.innerHTML = '';
    
    todos.forEach(todo => {
        list.innerHTML += `
            <li>
                <span style="text-decoration: ${todo.completed ? 'line-through' : 'none'}">
                    ${todo.title}
                </span>
                <button onclick="toggleComplete('${todo.id}', ${todo.completed})">
                    ${todo.completed ? 'Undo' : 'Done'}
                </button>
                <button onclick="editTodo('${todo.id}', '${todo.title}')">Edit</button>
                <button onclick="deleteTodo('${todo.id}')">Delete</button>
            </li>
        `;
    });
}

fetchTodos();