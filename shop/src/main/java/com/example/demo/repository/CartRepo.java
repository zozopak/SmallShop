package com.example.demo.repository;

import com.example.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    List<Cart> getCartsByUsername(String username);
    List<Cart> getCartsByProductID(long id);
    void deleteByUsernameAndProductID(String username,long productID);
}
