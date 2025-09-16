package com.example.day12todolist.controller;

import com.example.day12todolist.dto.TodoDTO;
import com.example.day12todolist.entity.Todo;
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
public class ToDoControllerTest {

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
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void should_return_your_id_when_post_given_a_id() throws Exception {
        Todo todo = Todo.builder().text("brouhaha").done(false).id(123L).build();
        String todoString = objectMapper.writeValueAsString(todo);
        ResultActions resultActions = mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoString))
                .andExpect(status().isCreated());
        MvcResult mvcResult = resultActions.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        long id =  new ObjectMapper().readTree(contentAsString).get("id").asLong();
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").value(id));

    }


    @Test
    void should_throw_exception_when_put_given_invalid_id() throws Exception {
        TodoDTO todoDTO = TodoDTO.builder().text("Buy snacks").done(true).build();
        String todoDTOString = objectMapper.writeValueAsString(todoDTO);
        mockMvc.perform(put("/todos/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoDTOString))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_ok_when_put_given_id() throws Exception {
        Long id = createTodo();
        TodoDTO todoDTO = TodoDTO.builder().text("Buy snacks").done(true).build();
        String todoDTOString = objectMapper.writeValueAsString(todoDTO);
        mockMvc.perform(put("/todos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoDTOString))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_throw_exception_when_put_given_null_data() throws Exception {
        long id = createTodo();
        String todoDTOString = """
                {}
                """;
        System.out.println(todoDTOString);
        mockMvc.perform(put("/todos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoDTOString))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void should_return_no_content_when_delete_given_id() throws Exception {
        Long id = createTodo();
        TodoDTO todoDTO = TodoDTO.builder().text("Buy snacks").done(true).build();
        String todoDTOString = objectMapper.writeValueAsString(todoDTO);
        mockMvc.perform(delete("/todos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoDTOString))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_throw_exception_when_delete_given_no_id() throws Exception {
        mockMvc.perform(delete("/todos/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
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
