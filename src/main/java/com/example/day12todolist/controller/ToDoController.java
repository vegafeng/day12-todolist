package com.example.day12todolist.controller;

import com.example.day12todolist.entity.Todo;
import com.example.day12todolist.service.ToDoService;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/todos")
    public ResponseEntity<Void> addTodo(@RequestBody Todo todo){
        toDoService.addTodo(todo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
