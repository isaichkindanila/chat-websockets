package ru.itis.chat.websockets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.chat.websockets.dto.CreateChatRoomDto;
import ru.itis.chat.websockets.services.interfaces.AuthService;
import ru.itis.chat.websockets.services.interfaces.ChatRoomService;

@Controller
@AllArgsConstructor
public class ChatController {

    private final AuthService authService;
    private final ChatRoomService roomService;

    @GetMapping("/chat")
    public String getRooms(ModelMap map) {
        authService.assertAuthenticated();
        map.put("rooms", roomService.getRooms());
        return "rooms";
    }

    @PostMapping("/chat")
    public String createRoom(CreateChatRoomDto dto) {
        authService.assertAuthenticated();
        roomService.createRoom(dto);
        return "redirect:/chat";
    }

    @GetMapping("/chat/{room}")
    public String getChat(@PathVariable String room, ModelMap map) {
        authService.assertAuthenticated();
        map.put("room", roomService.getRoom(room));
        return "chat";
    }
}
