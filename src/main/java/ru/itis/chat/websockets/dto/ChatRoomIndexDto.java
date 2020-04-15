package ru.itis.chat.websockets.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.chat.websockets.models.ChatRoom;

@Data
@Builder
public class ChatRoomIndexDto {
    private final String name;
    private final String token;

    public static ChatRoomIndexDto from(ChatRoom room) {
        return builder()
                .name(room.getName())
                .token(room.getToken())
                .build();
    }
}
