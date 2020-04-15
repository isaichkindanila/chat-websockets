package ru.itis.chat.websockets.services.interfaces;

import org.springframework.lang.Nullable;

/**
 * This service provides common API to access both HTTP session and SiMP session attributes.
 */
public interface SessionService {

    @Nullable
    Object getAttribute(String name);

    void setAttribute(String name, Object value);

    void removeAttribute(String name);
}
