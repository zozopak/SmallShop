package com.example.demo.service;

import com.example.demo.model.Users;

import java.util.List;

public interface UserService {
    List<Users> findAll();
  Users getUser(String id);
    void deleteUser(String  id);
    void saveUser(Users users);
}
