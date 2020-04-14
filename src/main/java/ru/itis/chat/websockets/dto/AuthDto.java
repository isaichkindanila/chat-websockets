package ru.itis.chat.websockets.dto;

import lombok.Data;

@Data
public class AuthDto {
    private String username;
    private String password;
}
