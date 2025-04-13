package com.example.ToDoApp.controller;

import com.example.ToDoApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String email,
                                      RedirectAttributes redirectAttributes) {
        try {
            userService.registerNewUser(username, password, email);
            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/register?success"; // Przekieruj z powrotem do rejestracji z komunikatem sukcesu
            // return "redirect:/login?registered"; // Przekieruj od razu do logowania:
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage()); // Przekaż komunikat błędu
            return "redirect:/register?error"; // Przekieruj z powrotem z błędem
        } catch (Exception e) {
            // Ogólny błąd
            redirectAttributes.addFlashAttribute("error", "An unexpected error occurred during registration.");
            return "redirect:/register?error";
        }
    }
}