package com.ubb.licenta.controller;

import com.ubb.licenta.converter.BaseConverter;
import com.ubb.licenta.dto.AccountDto;
import com.ubb.licenta.dto.ProductDto;
import com.ubb.licenta.dto.UserDto;
import com.ubb.licenta.model.Product;
import com.ubb.licenta.model.User;
import com.ubb.licenta.service.UserService;
import com.ubb.licenta.service.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private BaseConverter<User, UserDto> converterUser;

    @Autowired
    private BaseConverter<User, AccountDto> converterAccount;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = service.getUsers();
        List<UserDto> userDtos = converterUser.convertModelsToDtos(users);
        if (userDtos == null) {
            log.info("Unable to find any users");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        User user = converterUser.convertDtoToModel(userDto);
        boolean registered = service.register(user);

        if (!registered) {
            return new ResponseEntity<>(
                    sessionManager.getErrorMessage("Email already registered or incorrect"),
                    HttpStatus.BAD_REQUEST);
        }

        log.info("A user just registered to the platform.");
        return new ResponseEntity<>(converterUser.convertModelToDto(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody UserDto userDto,
            HttpSession session) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();

        boolean persistedSession = sessionManager.persistSession(email, password, session);
        if (!persistedSession) {
            return new ResponseEntity<>(
                    sessionManager.getErrorMessage("Incorrect email or password"),
                    HttpStatus.UNAUTHORIZED);
        }

        String json = sessionManager.getJsonMessageAsString(session,
                converterUser.convertModelToDto(service.getByEmail(email)));

        log.info("User {} logged in.", email);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("session-id") String sessionId) {
        String email = sessionManager.invalidateSession(sessionId);
        log.info("User {} logged out.", email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/save-account-settings/")
    public ResponseEntity<?> saveAccountSettings(
            @ModelAttribute AccountDto accountDto,
            @RequestHeader("session-id") String sessionId) {

        if (sessionManager.sessionNotValid(sessionId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = sessionManager.getUserFromSession(sessionId);

        if (checkAccount(accountDto)
                && (sessionManager.authenticateUser(user.getEmail(), accountDto.getPassword()) != null)) {
            log.info("Passwords do not match.");
            return new ResponseEntity<>(
                    sessionManager.getErrorMessage("Passwords do not match."),
                    HttpStatus.BAD_REQUEST);
        }

        User newUser = converterAccount.convertDtoToModel(accountDto);
        newUser.setEmail(user.getEmail());
        service.updateAccount(newUser);

        log.info("User {} saved changes in account settings.", user.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean checkAccount(AccountDto accountDto) {
        if (accountDto.getPassword() == null) {
            return false;
        }

        if (accountDto.getNewPassword() == null) {
            return false;
        }

        if (accountDto.getRewrittenPassword() == null) {
            return false;
        }

        return Objects.equals(accountDto.getNewPassword(), accountDto.getRewrittenPassword());
    }
}
