package com.example.day12todolist.controller;

import com.example.day12todolist.service.ToDoService;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author FENGVE
 */
@Controller
public class ToDoController {
    @Autowired
    private ToDoService toDoService;
}
