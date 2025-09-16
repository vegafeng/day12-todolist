package com.example.day12todolist.service;

import com.example.day12todolist.entity.Todo;
import com.example.day12todolist.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FENGVE
 */
@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    public List<Todo> findAll() {
        return toDoRepository.findAll();
    }
}
