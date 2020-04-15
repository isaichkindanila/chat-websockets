package ru.itis.chat.websockets.repositories.interfaces;

import ru.itis.chat.websockets.models.ChatMessage;
import ru.itis.chat.websockets.models.ChatRoom;

import java.util.List;

public interface ChatMessageRepository {

    ChatMessage save(ChatMessage message);

    List<ChatMessage> findByRoom(ChatRoom room);
}
