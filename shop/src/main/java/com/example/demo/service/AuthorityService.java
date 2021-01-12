package com.example.demo.service;

import com.example.demo.model.Authorities;
import com.example.demo.model.Users;

public interface AuthorityService {
    void SaveAuthority(Authorities authorities, Users users);
    void deleteAuthority( String username);
}
