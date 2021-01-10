package com.example.demo.service;

import com.example.demo.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<Users> findAll();
    Optional<Users> getUser(long id);
    void deleteUser(long  id);
    void saveUser(Users users);
}
