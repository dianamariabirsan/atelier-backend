package com.ubb.licenta.service;

import com.ubb.licenta.model.User;

import java.util.List;

public interface UserService {

    User updateAccount(User user);

    User getByEmail(String email);

    User getClientById(long id);

    boolean register(User user);

    boolean addAdmin(User user);

    List<User> getUsers();
}
