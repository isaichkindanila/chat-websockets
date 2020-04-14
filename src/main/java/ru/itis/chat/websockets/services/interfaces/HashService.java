package ru.itis.chat.websockets.services.interfaces;

public interface HashService {

    /**
     * Computes hash of a {@code String}.
     */
    String hash(String plain);

    /**
     * Checks if plain {@code String}'s hash matches hashed {@code String}.
     */
    boolean matches(String plain, String hashed);
}
