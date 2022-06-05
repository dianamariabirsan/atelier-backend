package com.ubb.licenta.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubb.licenta.dto.UserDto;
import com.ubb.licenta.model.User;
import com.ubb.licenta.model.enums.Role;
import com.ubb.licenta.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SessionManager {
    private static final String EMAIL_SESSION_ATTRIBUTE = "email";
    private static final String ROLE_SESSION_ATTRIBUTE = "role";
    private static final String MESSAGE = "message";

    private final Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    @Autowired
    private UserRepository userRepository;

    public boolean sessionNotValid(String sessionId) {
        HttpSession session = sessionMap.get(sessionId);

        try {
            return session == null || session.getAttribute(EMAIL_SESSION_ATTRIBUTE) == null;
        } catch (IllegalStateException ignore) {
            return true;
        }
    }

    public boolean isAdmin(String sessionId) {
        HttpSession session = sessionMap.get(sessionId);
        String email = (String) session.getAttribute(EMAIL_SESSION_ATTRIBUTE);
        User user = userRepository.findUserByEmail(email);
        return Role.ADMIN.equals(user.getRole());
    }

    public User getUserFromSession(String sessionId) {
        String email = (String) sessionMap.get(sessionId).getAttribute(EMAIL_SESSION_ATTRIBUTE);
        log.info("Got email from session: {}", email);

        return userRepository.findUserByEmail(email);
    }

    public String getErrorMessage(String errorMessage) {
        return new JSONObject().put(MESSAGE, errorMessage).toString();
    }

    public String getJsonForFrontEndAsString(String key, String value) {
        return new JSONObject().put(key, value).toString();
    }

    public String getJsonMessageAsString(HttpSession session, UserDto userDto) {
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode login = factory.objectNode();
        login.put("session_id", session.getId());

        JsonNode node = mapper.valueToTree(userDto);
        login.set("user", node);

        return login.toString();
    }

    public boolean persistSession(String email, String password, HttpSession session) {
        User userDb = authenticateUser(email, password);

        if (userDb == null) {
            return false;
        }

        session.setAttribute(EMAIL_SESSION_ATTRIBUTE, email);
        session.setAttribute(ROLE_SESSION_ATTRIBUTE, userDb.getRole());
        sessionMap.put(session.getId(), session);
        return true;
    }

    public User authenticateUser(String email, String password) {
        User userDb = userRepository.findUserByEmail(email);
        if (userDb == null || !userDb.getPassword().equalsIgnoreCase(password)) {
            return null;
        }

        return userDb;
    }

    public String invalidateSession(String sessionId) {
        HttpSession session = sessionMap.remove(sessionId);
        String email = (String) session.getAttribute(EMAIL_SESSION_ATTRIBUTE);
        session.removeAttribute(EMAIL_SESSION_ATTRIBUTE);
        session.removeAttribute(ROLE_SESSION_ATTRIBUTE);
        session.invalidate();
        return email;
    }
}
