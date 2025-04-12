package com.example.demo.init;

import com.example.demo.model.Role;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public DataLoader(PasswordEncoder passwordEncoder, UserRepository userRepository, TaskRepository taskRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            User admin = new User("admin", passwordEncoder.encode("admin123"), "admin@gmail.com", Role.ADMIN);
            User user = new User("user1", passwordEncoder.encode("user1"), "user1@gmail.com", Role.USER);

            userRepository.saveAll(List.of(admin, user));
            System.out.println("User loaded");

            Task adminTask1 = new Task("Admin Task 1", "Admin Description 1", admin);
            Task adminTask2 = new Task("Admin Task 2", "Admin Description 2", admin);
            Task adminTask3 = new Task("Admin Task 3", "Admin Description 3", admin);
            Task adminTask4 = new Task("Admin Task 4", "Admin Description 4", admin);
            Task adminTask5 = new Task("Admin Task 5", "Admin Description 5", admin);
            Task adminTask6 = new Task("Admin Task 6", "Admin Description 6", admin);

            Task userTask1 = new Task("User Task 1", "User Description 1", user);
            Task userTask2 = new Task("User Task 2", "User Description 2", user);
            Task userTask3 = new Task("User Task 3", "User Description 3", user);
            Task userTask4 = new Task("User Task 4", "User Description 4", user);
            Task userTask5 = new Task("User Task 5", "User Description 5", user);
            Task userTask6 = new Task("User Task 6", "User Description 6", user);

            taskRepository.saveAll(List.of(adminTask1, adminTask2, adminTask3, adminTask4, adminTask5, adminTask6,
                    userTask1, userTask2, userTask3, userTask4, userTask5, userTask6));

            System.out.println("Task loaded");

        }
    }
}
