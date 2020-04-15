package ru.itis.chat.websockets.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.models.ChatRoom;
import ru.itis.chat.websockets.repositories.interfaces.ChatRoomRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
class ChatRoomRepositoryJpaImpl implements ChatRoomRepository {

    private static final String FIND_BY_TOKEN =
            "select room from ChatRoom room where room.token = :token";

    private static final String FIND_ALL =
            "select room from ChatRoom room";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ChatRoom> findByToken(String token) {
        var query = entityManager.createQuery(FIND_BY_TOKEN, ChatRoom.class);
        query.setParameter("token", token);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ChatRoom> findAll() {
        return entityManager.createQuery(FIND_ALL, ChatRoom.class).getResultList();
    }

    @Override
    public ChatRoom save(ChatRoom room) {
        return entityManager.merge(room);
    }
}
