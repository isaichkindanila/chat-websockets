package ru.itis.chat.websockets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.itis.chat.websockets.dto.MessageDto;
import ru.itis.chat.websockets.dto.SendMessageDto;
import ru.itis.chat.websockets.services.interfaces.AuthService;
import ru.itis.chat.websockets.services.interfaces.ChatService;

@Controller
@AllArgsConstructor
public class ChatStompController {

    private final ChatService chatService;
    private final AuthService authService;

    @MessageMapping("/chat/{room}")
    @SendTo("/topic/chat/{room}")
    public MessageDto sendMessage(@DestinationVariable String room, SendMessageDto dto) {
        authService.assertAuthenticated();
        return chatService.send(room, dto);
    }
}
