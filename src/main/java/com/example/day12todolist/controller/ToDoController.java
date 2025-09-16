package com.example.day12todolist.controller;

import com.example.day12todolist.dto.TodoDTO;
import com.example.day12todolist.entity.Todo;
import com.example.day12todolist.service.ToDoService;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FENGVE
 */
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping("/todos")
    public List<Todo> findAll(){
        return toDoService.findAll();
    }
    @GetMapping("/todos/{id}")
    public Todo findById(@PathVariable Long id){
        return toDoService.findById(id);
    }
    @PostMapping("/todos")
    public ResponseEntity<Todo> addTodo(@RequestBody TodoDTO todoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoService.addTodo(todoDto));
    }

}
