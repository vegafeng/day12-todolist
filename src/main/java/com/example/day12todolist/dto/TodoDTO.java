package com.example.day12todolist.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author FENGVE
 */
@Getter
@Setter
@Builder
public class TodoDTO {
    private String text;
    private boolean done;
}
