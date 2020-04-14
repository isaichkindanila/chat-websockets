package ru.itis.chat.websockets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.chat.websockets.dto.AuthDto;
import ru.itis.chat.websockets.services.interfaces.AuthService;
import ru.itis.chat.websockets.services.interfaces.SignInService;

@Controller
@AllArgsConstructor
public class SignInController {

    private final AuthService authService;
    private final SignInService signInService;

    @GetMapping("/signIn")
    public String getPage() {
        if (authService.isAuthenticated()) {
            return "redirect:/";
        } else {
            return "sign_in";
        }
    }

    @PostMapping("/signIn")
    public String signIn(AuthDto dto) {
        if (authService.isAnonymous()) {
            signInService.signIn(dto);
        }

        return "redirect:/";
    }
}
