package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService{

    private CartRepo cartRepo;

    @Autowired
    public void setCartRepo(CartRepo cartRepo){
        this.cartRepo=cartRepo;
    }

    @Override
    public List<Cart> getCarts(String username) {
        return cartRepo.getCartsByUsername(username);
    }

    @Override
    public List<Cart> getCarts(long id) {
        return cartRepo.getCartsByProductID(id);
    }

    @Override
    public void addCart(Cart cart) {
        cartRepo.save(cart);
    }

    @Override
    public void deleteCart(String username, long productID) {
        cartRepo.deleteByUsernameAndProductID(username,productID);
    }

    @Override
    public List<Cart> getAll() {
        return cartRepo.findAll();
    }

    @Override
    public void deleteCartByUsername(String username) {
        cartRepo.deleteByUsername(username);
    }


}
