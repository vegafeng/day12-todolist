package com.example.day12todolist.repository.impl;

import com.example.day12todolist.entity.Todo;
import com.example.day12todolist.repository.ToDoRepository;
import com.example.day12todolist.repository.jpa.ToDoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author FENGVE
 */
@Repository
public class ToDoRepositoryImpl implements ToDoRepository {
    @Autowired
    private ToDoJpaRepository toDoJpaRepository;
    @Override
    public List<Todo> findAll() {
        return toDoJpaRepository.findAll();
    }

    @Override
    public Todo addTodo(Todo todo){
//        toDoJpaRepository.save(todo);
        return toDoJpaRepository.save(todo);
    }

    @Override
    public Todo findById(Long id) {
        return toDoJpaRepository.findById(id).get();
    }
}
