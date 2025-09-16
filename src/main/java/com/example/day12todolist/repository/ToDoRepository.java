package com.example.day12todolist.repository;

import com.example.day12todolist.dto.TodoDTO;
import com.example.day12todolist.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author FENGVE
 */
@Repository
public interface ToDoRepository {
    public List<Todo> findAll();

    public Todo addTodo(TodoDTO todoDto);

    public Todo findById(Long id);

    public void updateById(Long id, TodoDTO todoDTO);
}
