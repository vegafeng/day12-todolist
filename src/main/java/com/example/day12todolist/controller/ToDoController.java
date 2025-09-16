package com.example.day12todolist.controller;

import com.example.day12todolist.service.ToDoService;
import org.hibernate.annotations.ConcreteProxy;

/**
 * @author FENGVE
 */
@ConcreteProxy
public class ToDoController {
    private ToDoService toDoService;
}
