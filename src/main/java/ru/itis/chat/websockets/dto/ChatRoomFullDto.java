package ru.itis.chat.websockets.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatRoomFullDto {
    private final String name;
    private final String token;
    private final List<MessageDto> messages;
}
