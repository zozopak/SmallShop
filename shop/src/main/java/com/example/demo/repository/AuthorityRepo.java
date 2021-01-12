package com.example.demo.repository;

import com.example.demo.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authorities,Long> {
    void deleteByUsername(String username);
}
