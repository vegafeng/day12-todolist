package com.example.day12todolist.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 * @author FENGVE
 */
// service 和 service 之间传数据
@Getter
@Setter
@Builder
public class TodoDTO {
    private String text;
    private boolean done;

}
