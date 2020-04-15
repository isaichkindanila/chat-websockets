package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.dto.MessageDto;
import ru.itis.chat.websockets.dto.SendMessageDto;
import ru.itis.chat.websockets.exceptions.NotFoundException;
import ru.itis.chat.websockets.models.ChatMessage;
import ru.itis.chat.websockets.repositories.interfaces.ChatMessageRepository;
import ru.itis.chat.websockets.repositories.interfaces.ChatRoomRepository;
import ru.itis.chat.websockets.services.interfaces.AuthService;
import ru.itis.chat.websockets.services.interfaces.ChatService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
class ChatServiceImpl implements ChatService {

    private final AuthService authService;
    private final ChatRoomRepository roomRepository;
    private final ChatMessageRepository messageRepository;

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public MessageDto dtoFrom(ChatMessage message) {
        return MessageDto.builder()
                .sender(message.getSender().getUsername())
                .text(message.getText())
                .time(dateFormat.format(message.getTime()))
                .build();
    }

    @Override
    @Transactional
    public MessageDto send(String roomId, SendMessageDto dto) {
        var room = roomRepository.findByToken(roomId).orElseThrow(NotFoundException::new);
        var user = authService.getUser();

        var message = messageRepository.save(ChatMessage.builder()
                .room(room)
                .sender(user)
                .text(dto.getText())
                .time(new Date())
                .build());

        return dtoFrom(message);
    }
}
