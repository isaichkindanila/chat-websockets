package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.dto.AuthDto;
import ru.itis.chat.websockets.exceptions.AlreadyExistsException;
import ru.itis.chat.websockets.exceptions.BadRequestException;
import ru.itis.chat.websockets.models.User;
import ru.itis.chat.websockets.repositories.interfaces.UserRepository;
import ru.itis.chat.websockets.services.interfaces.AuthService;
import ru.itis.chat.websockets.services.interfaces.HashService;
import ru.itis.chat.websockets.services.interfaces.SignUpService;

@Service
@AllArgsConstructor
class SignUpServiceImpl implements SignUpService {

    private final AuthService authService;
    private final HashService hashService;
    private final UserRepository userRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void signUp(AuthDto dto) {
        final var username = dto.getUsername();
        final var password = dto.getPassword();

        if (!username.matches("[a-zA-Z0-9.-]{1,20}")) {
            throw new BadRequestException("invalid username");
        }

        if (password.isEmpty()) {
            throw new BadRequestException("password cannot be empty");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new AlreadyExistsException("username is already taken");
        }

        var user = userRepository.save(User.builder()
                .username(username)
                .passHash(hashService.hash(password))
                .build());

        authService.authenticate(user);
    }
}
