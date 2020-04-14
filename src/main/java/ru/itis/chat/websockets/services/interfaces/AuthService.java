package ru.itis.chat.websockets.services.interfaces;

import org.springframework.lang.NonNull;
import ru.itis.chat.websockets.exceptions.AccessDeniedException;
import ru.itis.chat.websockets.models.User;

import java.util.Optional;

public interface AuthService {

    /**
     * @return {@code Optional} of currently authenticated user (empty if session is not authenticated)
     */
    @NonNull
    Optional<User> getUserOptional();

    /**
     * @return currently authenticated user
     * @throws AccessDeniedException if session is not authenticated
     */
    @NonNull
    User getUser() throws AccessDeniedException;

    /**
     * @return {@code true} if session is not authenticated, false otherwise
     */
    boolean isAnonymous();

    /**
     * @return {@code true} if session is authenticated, false otherwise
     */
    boolean isAuthenticated();

    /**
     * Asserts that current session is authenticated.
     *
     * @throws AccessDeniedException if session is not authenticated
     */
    void assertAuthenticated() throws AccessDeniedException;

    /**
     * Authenticates current session.
     *
     * @param user authenticated user
     */
    void authenticate(User user);

    /**
     * Invalidates current session's authentication.
     */
    void invalidate();
}
