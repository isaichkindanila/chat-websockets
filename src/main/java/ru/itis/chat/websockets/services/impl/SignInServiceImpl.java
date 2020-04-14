package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.chat.websockets.dto.AuthDto;
import ru.itis.chat.websockets.exceptions.BadCredentialsException;
import ru.itis.chat.websockets.repositories.interfaces.UserRepository;
import ru.itis.chat.websockets.services.interfaces.AuthService;
import ru.itis.chat.websockets.services.interfaces.HashService;
import ru.itis.chat.websockets.services.interfaces.SignInService;

@Service
@AllArgsConstructor
class SignInServiceImpl implements SignInService {

    private final AuthService authService;
    private final HashService hashService;
    private final UserRepository userRepository;

    @Override
    public void signIn(AuthDto dto) {
        var user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new BadCredentialsException("username not found"));

        if (!hashService.matches(dto.getPassword(), user.getPassHash())) {
            throw new BadCredentialsException("incorrect password");
        }

        authService.authenticate(user);
    }
}
