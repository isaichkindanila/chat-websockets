package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.itis.chat.websockets.exceptions.AccessDeniedException;
import ru.itis.chat.websockets.models.User;
import ru.itis.chat.websockets.services.interfaces.AuthService;
import ru.itis.chat.websockets.services.interfaces.SessionService;

import java.util.Optional;

@Service
@AllArgsConstructor
class AuthServiceImpl implements AuthService {

    private final SessionService sessionService;

    @Nullable
    private User getNullableUser() {
        return (User) sessionService.getAttribute("user");
    }

    @Override
    public Optional<User> getUserOptional() {
        return Optional.ofNullable(getNullableUser());
    }

    @Override
    public User getUser() throws AccessDeniedException {
        return getUserOptional().orElseThrow(AccessDeniedException::new);
    }

    @Override
    public boolean isAnonymous() {
        return getNullableUser() == null;
    }

    @Override
    public boolean isAuthenticated() {
        return getNullableUser() != null;
    }

    @Override
    public void assertAuthenticated() throws AccessDeniedException {
        if (getNullableUser() == null) {
            throw new AccessDeniedException();
        }
    }

    @Override
    public void authenticate(User user) {
        sessionService.setAttribute("user", user);
    }

    @Override
    public void invalidate() {
        sessionService.removeAttribute("user");
    }
}
