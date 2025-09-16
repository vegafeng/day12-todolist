package com.example.day12todolist.controller;

import com.example.day12todolist.entity.Todo;
import com.example.day12todolist.service.ToDoService;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author FENGVE
 */
@RestController
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping("/todos")
    public List<Todo> findAll(){
        return toDoService.findAll();
    }
}
