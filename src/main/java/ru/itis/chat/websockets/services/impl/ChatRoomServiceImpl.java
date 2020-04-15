package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.dto.ChatRoomFullDto;
import ru.itis.chat.websockets.dto.ChatRoomIndexDto;
import ru.itis.chat.websockets.dto.CreateChatRoomDto;
import ru.itis.chat.websockets.exceptions.NotFoundException;
import ru.itis.chat.websockets.models.ChatRoom;
import ru.itis.chat.websockets.repositories.interfaces.ChatMessageRepository;
import ru.itis.chat.websockets.repositories.interfaces.ChatRoomRepository;
import ru.itis.chat.websockets.services.interfaces.ChatRoomService;
import ru.itis.chat.websockets.services.interfaces.ChatService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatService chatService;

    private final ChatRoomRepository roomRepository;
    private final ChatMessageRepository messageRepository;

    @Override
    public void createRoom(CreateChatRoomDto dto) {
        roomRepository.save(ChatRoom.builder()
                .token(UUID.randomUUID().toString())
                .name(dto.getName())
                .build());
    }

    @Override
    public List<ChatRoomIndexDto> getRooms() {
        return roomRepository.findAll().stream()
                .map(ChatRoomIndexDto::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ChatRoomFullDto getRoom(String roomId) {
        var room = roomRepository.findByToken(roomId).orElseThrow(NotFoundException::new);
        var messages = messageRepository.findByRoom(room).stream()
                .map(chatService::dtoFrom)
                .collect(Collectors.toList());

        return ChatRoomFullDto.builder()
                .token(room.getToken())
                .name(room.getName())
                .messages(messages)
                .build();
    }
}
