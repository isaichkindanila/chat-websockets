package ru.itis.chat.websockets.services.interfaces;

import ru.itis.chat.websockets.dto.AuthDto;
import ru.itis.chat.websockets.exceptions.AlreadyExistsException;
import ru.itis.chat.websockets.exceptions.BadRequestException;

public interface SignUpService {

    /**
     * @throws AlreadyExistsException if username is already taken
     * @throws BadRequestException    if provided parameters are somehow wrong
     */
    void signUp(AuthDto dto) throws AlreadyExistsException, BadRequestException;
}
