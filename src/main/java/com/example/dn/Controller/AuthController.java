package com.example.dn.Controller;

import com.example.dn.Model.User;
import com.example.dn.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Страница регистрации (GET)
     */
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // вернёт register.html
    }

    /**
     * Обработка формы регистрации (POST)
     */
    @PostMapping("/register")
    public String processRegister(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Model model
    ) {
        try {
            authService.register(username, email, password);
            model.addAttribute("message", "Регистрация прошла успешно! Можно войти.");
            return "login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    /**
     * Страница логина (GET)
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // login.html
    }

    /**
     * Обработка логина (POST)
     */
    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        User user = authService.login(username, password);
        if (user == null) {
            model.addAttribute("error", "Неверные учетные данные");
            return "login";
        }
        // Если всё ок, перенаправляем на страницу новостей
        return "redirect:/news";
    }

    /**
     * Страница новостей
     */
    @GetMapping("/news")
    public String showNewsPage() {
        // Здесь можно получить актуальные новости из БД или внешнего API
        return "news"; // news.html
    }

}
