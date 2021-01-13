package com.example.demo.service;

import com.example.demo.model.Authorities;
import com.example.demo.model.Users;
import com.example.demo.repository.AuthorityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void deleteAuthority( String username) {
        authorityRepo.deleteByUsername(username);
    }

    @Override
    public Authorities findAuthorities(String username) {
      return authorityRepo.findAuthoritiesByUsername(username);
    }

    @Override
    public void saveAuthorithy(Authorities authorities) {
        authorityRepo.save(authorities);
    }

    @Override
    public List<Authorities> findAll() {
      return   authorityRepo.findAll();
    }


}
