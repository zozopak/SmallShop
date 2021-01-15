package com.example.demo.service;

import com.example.demo.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCarts(String username);
    List<Cart> getCarts(long id);
    void addCart(Cart cart);
    void deleteCart(String username,long productID);
}

