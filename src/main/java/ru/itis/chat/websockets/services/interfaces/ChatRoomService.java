package ru.itis.chat.websockets.services.interfaces;

import ru.itis.chat.websockets.dto.ChatRoomFullDto;
import ru.itis.chat.websockets.dto.ChatRoomIndexDto;
import ru.itis.chat.websockets.dto.CreateChatRoomDto;

import java.util.List;

public interface ChatRoomService {

    void createRoom(CreateChatRoomDto dto);

    List<ChatRoomIndexDto> getRooms();

    ChatRoomFullDto getRoom(String roomId);
}
