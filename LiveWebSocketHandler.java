package com.sakshee.portfolio.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakshee.portfolio.model.ContactMessage;
import com.sakshee.portfolio.repository.ContactMessageRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LiveWebSocketHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private final ObjectMapper mapper = new ObjectMapper();
    private final ContactMessageRepository repository;

    public LiveWebSocketHandler(ContactMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        broadcast(Map.of("type", "presence", "online", sessions.size()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JsonNode node = mapper.readTree(message.getPayload());
        String text = node.path("message").asText("").trim();
        String name = node.path("name").asText("Visitor").trim();
        if (text.isEmpty()) return;

        String safeName = name.isEmpty() ? "Visitor" : name;
        ContactMessage saved = repository.save(new ContactMessage(safeName, text));

        broadcast(Map.of(
            "type", "chat",
            "id", UUID.randomUUID().toString(),
            "name", safeName,
            "message", saved.getMessage(),
            "time", saved.getCreatedAt().toString()
        ));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        broadcast(Map.of("type", "presence", "online", sessions.size()));
    }

    private void broadcast(Map<String, Object> payload) throws Exception {
        TextMessage outgoing = new TextMessage(mapper.writeValueAsString(payload));
        for (WebSocketSession client : sessions) {
            if (client.isOpen()) client.sendMessage(outgoing);
        }
    }
}
