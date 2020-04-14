package ru.itis.chat.websockets.services.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.itis.chat.websockets.services.interfaces.HashService;

@Service
public class HashServiceBCryptImpl implements HashService {

    @Override
    public String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    @Override
    public boolean matches(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }
}
