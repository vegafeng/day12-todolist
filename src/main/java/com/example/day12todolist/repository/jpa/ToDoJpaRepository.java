package com.example.day12todolist.repository.jpa;

import com.example.day12todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author FENGVE
 */
@Repository
public interface ToDoJpaRepository extends JpaRepository<Todo, Long> {
}
