package com.example.day12todolist.service;

import ch.qos.logback.core.util.StringUtil;
import com.example.day12todolist.dto.TodoDTO;
import com.example.day12todolist.entity.Todo;
import com.example.day12todolist.exception.IdNotFoundException;
import com.example.day12todolist.exception.InvalidTextException;
import com.example.day12todolist.exception.NullValueException;
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

    public Todo addTodo(TodoDTO todo) {
        if (StringUtil.isNullOrEmpty(todo.getText())) {
            throw new InvalidTextException();
        }
        return toDoRepository.addTodo(todo);
    }

    public Todo findById(Long id) {
        return toDoRepository.findById(id);
    }

    public void updateById(Long id, TodoDTO todoDTO) throws IdNotFoundException {
        if (todoDTO==null || StringUtil.isNullOrEmpty(todoDTO.getText())) {
            throw new NullValueException();
        }

        Todo todo = toDoRepository.findById(id);
        if(todo==null) {
            throw new IdNotFoundException();
        }
        toDoRepository.updateById(id, todoDTO);
    }

    public void deleteById(Long id) throws IdNotFoundException {
        Todo todo = toDoRepository.findById(id);
        if(todo==null) {
            throw new IdNotFoundException();
        }
        toDoRepository.deleteById(id);
    }
}
