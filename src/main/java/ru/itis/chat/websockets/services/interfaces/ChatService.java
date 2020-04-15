package ru.itis.chat.websockets.services.interfaces;

import ru.itis.chat.websockets.dto.MessageDto;
import ru.itis.chat.websockets.dto.SendMessageDto;
import ru.itis.chat.websockets.models.ChatMessage;

public interface ChatService {

    MessageDto dtoFrom(ChatMessage message);

    MessageDto send(String roomId, SendMessageDto dto);
}
