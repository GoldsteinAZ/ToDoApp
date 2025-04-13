package com.example.ToDoApp.repository;

import com.example.ToDoApp.model.Task;
import com.example.ToDoApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUser(User user);
}