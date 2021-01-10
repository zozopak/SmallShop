package com.example.demo.service;

import com.example.demo.model.Authorities;
import com.example.demo.model.Users;
import com.example.demo.repository.AuthorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorithyServiceImp implements AuthorityService {
     private AuthorityRepo authorityRepo;
      @Autowired
     public void set(AuthorityRepo authorityRepo){
         this.authorityRepo=authorityRepo;
     }

    @Override
    public void SaveAuthority(Authorities authorities, Users users) {
        authorities.setUsername(users.getUsername());
        authorities.setAuthority("ROLE_USER");
        authorityRepo.save(authorities);
    }

}
