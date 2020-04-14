package ru.itis.chat.websockets.services.impl;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import ru.itis.chat.websockets.exceptions.AccessDeniedException;
import ru.itis.chat.websockets.models.User;
import ru.itis.chat.websockets.services.interfaces.AuthService;

import java.util.Optional;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

@Service
class AuthServiceImpl implements AuthService {

    @NonNull
    private RequestAttributes getAttributes() {
        var attributes = RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            throw new IllegalStateException("no request attributes found - should never be thrown");
        }

        return attributes;
    }

    @Nullable
    private User getNullableUser() {
        return (User) getAttributes().getAttribute("user", SCOPE_SESSION);
    }

    @Override
    public Optional<User> getUserOptional() {
        return Optional.ofNullable(getNullableUser());
    }

    @Override
    public User getUser() throws AccessDeniedException {
        var user = getNullableUser();

        if (user == null) {
            throw new AccessDeniedException();
        }

        return user;
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
        getAttributes().setAttribute("user", user, SCOPE_SESSION);
    }

    @Override
    public void invalidate() {
        getAttributes().removeAttribute("user", SCOPE_SESSION);
    }
}
