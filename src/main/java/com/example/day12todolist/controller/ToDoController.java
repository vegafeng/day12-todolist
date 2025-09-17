package com.example.day12todolist.controller;

import com.example.day12todolist.dto.TodoDTO;
import com.example.day12todolist.entity.Todo;
import com.example.day12todolist.exception.IdNotFoundException;
import com.example.day12todolist.service.ToDoService;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Todo> addTodo(@RequestBody @Validated TodoDTO todoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoService.addTodo(todoDto));
    }
    @PutMapping("/todos/{id}")
    public ResponseEntity<Void> updateById(@PathVariable Long id, @RequestBody TodoDTO todoDTO) throws IdNotFoundException {
        toDoService.updateById(id, todoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws IdNotFoundException {
        toDoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
