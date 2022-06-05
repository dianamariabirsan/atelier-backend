package com.ubb.licenta.service;

import com.ubb.licenta.model.User;
import com.ubb.licenta.model.enums.Role;
import com.ubb.licenta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User updateAccount(User user) {
        return repository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return repository.findUserByEmail(email);
    }

    @Override
    public User getClientById(long id) {
        return repository.findUserById(id);
    }

    @Override
    public boolean register(User user) {
        if (user == null || getByEmail(user.getEmail()) != null) {
            return false;
        }

        user.setRole(Role.CLIENT);
        repository.save(user);
        return true;
    }

    @Override
    public boolean addAdmin(User user) {
        if (user == null || getByEmail(user.getEmail()) != null) {
            return false;
        }

        user.setRole(Role.ADMIN);
        repository.save(user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }
}
