package com.example.demo.controller;

import com.example.demo.model.Authorities;
import com.example.demo.model.Cart;
import com.example.demo.model.Users;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private AuthorityService authorityService;
    private CartService cartService;


    @Autowired
    @Qualifier("userServiceImp")
    public void set(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    @Qualifier("authorithyServiceImp")
    public void set(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Autowired
    @Qualifier("cartServiceImp")
    public void set(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Users user = userService.getUser(id);

        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, Users user) {

        userService.saveUser(user);
        return "redirect:/users";
    }

    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id, Model model) {
        List<Cart> cartList=cartService.getAll();
        for(Cart c:cartList){
            if(c.getUsername().equals(id))
                cartService.deleteCartByUsername(id);
        }
        authorityService.deleteAuthority(id);
        userService.deleteUser(id);
        List<Users> users = userService.findAll();
        model.addAttribute("users", users);
        return "redirect:/users";
    }

    @GetMapping
    public String index(Model model) {
        List<Users> users = userService.findAll();
        model.addAttribute("usersList", users);

        return "users";
    }
}
