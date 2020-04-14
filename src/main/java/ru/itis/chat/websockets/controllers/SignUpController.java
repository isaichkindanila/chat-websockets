package ru.itis.chat.websockets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.chat.websockets.dto.AuthDto;
import ru.itis.chat.websockets.services.interfaces.AuthService;
import ru.itis.chat.websockets.services.interfaces.SignUpService;

@Controller
@AllArgsConstructor
public class SignUpController {

    private final AuthService authService;
    private final SignUpService signUpService;

    @GetMapping("/signUp")
    public String getPage() {
        if (authService.isAuthenticated()) {
            return "redirect:/";
        } else {
            return "sign_up";
        }
    }

    @PostMapping("/signUp")
    public String signUp(AuthDto dto) {
        if (authService.isAnonymous()) {
            signUpService.signUp(dto);
        }

        return "redirect:/";
    }
}
