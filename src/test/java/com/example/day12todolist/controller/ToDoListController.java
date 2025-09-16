package com.example.day12todolist.controller;

import com.example.day12todolist.dto.TodoDTO;
import com.example.day12todolist.entity.Todo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class ToDoListController {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void should_return_all_todos_when_get_given_null() throws Exception {
//        Todo todo = Todo.builder().text("brouhaha").done(false).build();
        mockMvc.perform(get("/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void should_return_matching_todo_when_get_given_id() throws Exception {
        Long id = createTodo();
        mockMvc.perform(get("/todos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

    }

    @Test
    void should_response_created_when_post_given_valid_todo() throws Exception {
        Todo todo = Todo.builder().text("brouhaha").done(false).build();
        String todoString = objectMapper.writeValueAsString(todo);
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoString))
                .andExpect(status().isCreated());
    }

    @Test
    void should_throw_exception_when_post_given_invalid_todo_text_null() throws Exception {
        Todo todo = Todo.builder().text("").done(false).build();
        String todoString = objectMapper.writeValueAsString(todo);
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoString))
                .andExpect(status().isBadRequest());
    }

    private Long createTodo() throws Exception {
        TodoDTO todo = TodoDTO.builder().text("brouhaha").done(false).build();
        String todoString = objectMapper.writeValueAsString(todo);
        ResultActions resultActions = mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoString))
                .andExpect(status().isCreated());
        MvcResult mvcResult = resultActions.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        return new ObjectMapper().readTree(contentAsString).get("id").asLong();
    }


}
