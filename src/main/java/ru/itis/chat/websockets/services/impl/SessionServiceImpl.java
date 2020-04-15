package ru.itis.chat.websockets.services.impl;

import org.springframework.messaging.simp.SimpAttributesContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import ru.itis.chat.websockets.services.interfaces.SessionService;

@Service
class SessionServiceImpl implements SessionService {

    @Override
    public Object getAttribute(String name) {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            var attribute = requestAttributes.getAttribute(name, RequestAttributes.SCOPE_SESSION);
            if (attribute != null) {
                return attribute;
            }
        }

        var simpAttributes = SimpAttributesContextHolder.getAttributes();
        if (simpAttributes != null) {
            return simpAttributes.getAttribute(name);
        }

        return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            requestAttributes.setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
        }

        var simpAttributes = SimpAttributesContextHolder.getAttributes();
        if (simpAttributes != null) {
            simpAttributes.setAttribute(name, value);
        }
    }

    @Override
    public void removeAttribute(String name) {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            requestAttributes.removeAttribute(name, RequestAttributes.SCOPE_SESSION);
        }

        var simpAttributes = SimpAttributesContextHolder.getAttributes();
        if (simpAttributes != null) {
            simpAttributes.removeAttribute(name);
        }
    }
}
