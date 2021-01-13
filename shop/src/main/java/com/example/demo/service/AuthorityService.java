package com.example.demo.service;

import com.example.demo.model.Authorities;
import com.example.demo.model.Users;

import java.util.List;

public interface AuthorityService {
    void SaveAuthority(Authorities authorities, Users users);
    void deleteAuthority( String username);
    Authorities findAuthorities(String username);
    void saveAuthorithy(Authorities authorities);
    List<Authorities> findAll();
}
