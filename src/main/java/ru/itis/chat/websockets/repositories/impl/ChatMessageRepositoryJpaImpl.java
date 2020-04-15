package ru.itis.chat.websockets.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.models.ChatMessage;
import ru.itis.chat.websockets.models.ChatRoom;
import ru.itis.chat.websockets.repositories.interfaces.ChatMessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
class ChatMessageRepositoryJpaImpl implements ChatMessageRepository {

    public static final String FIND_BY_ROOM =
            "select message from ChatMessage message " +
            "join ChatRoom room on message.room = room " +
            "where room.id = :id " +
            "order by message.time";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ChatMessage save(ChatMessage message) {
        return entityManager.merge(message);
    }

    @Override
    public List<ChatMessage> findByRoom(ChatRoom room) {
        var query = entityManager.createQuery(FIND_BY_ROOM, ChatMessage.class);
        query.setParameter("id", room.getId());

        return query.getResultList();
    }
}
