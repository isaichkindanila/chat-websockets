package ru.itis.chat.websockets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.chat.websockets.services.interfaces.AuthService;

@Controller
@AllArgsConstructor
public class HomeController {

    private final AuthService authService;

    @GetMapping("/")
    public ModelAndView getPage() {
        return new ModelAndView("home", "authenticated", authService.isAuthenticated());
    }
}
