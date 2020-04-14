package ru.itis.chat.websockets.services.interfaces;

import ru.itis.chat.websockets.dto.AuthDto;
import ru.itis.chat.websockets.exceptions.BadCredentialsException;

public interface SignInService {

    /**
     * @throws BadCredentialsException if authorization was unsuccessful
     */
    void signIn(AuthDto dto) throws BadCredentialsException;
}
