package ru.itis.chat.websockets.repositories.interfaces;

import ru.itis.chat.websockets.models.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository {

    Optional<ChatRoom> findByToken(String token);

    List<ChatRoom> findAll();

    ChatRoom save(ChatRoom room);
}
