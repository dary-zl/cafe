package com.example.cafe.Controller;

import com.example.cafe.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm(
            @RequestParam(required = false) boolean error,
            Model model) {

        if (error) {
            model.addAttribute("loginError", "Неверное имя пользователя или пароль");
            log.warn("Попытка входа с неверными данными");
        }

        return "login";
    }
}
