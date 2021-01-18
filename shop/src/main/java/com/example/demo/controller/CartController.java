package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepo;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductService productService;
    private UserService userService;
    private CartService cartService;

    @Autowired
    @Qualifier("userServiceImp")
    public void set(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier("cartServiceImp")
    public void set(CartService cartService) {
        this.cartService = cartService;
    }


    @Autowired
    @Qualifier("productServiceImp")
    public void set(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getIndex(Model model, Principal principal) {
        List<Product> p = new ArrayList<>();
        List<Cart> carts = cartService.getCarts(principal.getName());
        for (Cart cart : carts) {
            long productID = cart.getProductID();
            Product product = productService.getProduct(productID);
            p.add(product);

        }


        model.addAttribute("p", p);
        return "cart";
    }

    @GetMapping("/{id}")
    public String getCart(@PathVariable(name = "id") long id, Model model, Principal principal) {
        List<Product> p = new ArrayList<>();
        Cart c = new Cart();
        c.setProductID(id);
        c.setUsername(principal.getName());
        cartService.addCart(c);
        List<Cart> carts = cartService.getCarts(principal.getName());
        for (Cart cart : carts) {
            long productID = cart.getProductID();
            Product product = productService.getProduct(productID);
            p.add(product);

        }


        model.addAttribute("p", p);
        return "cart";
    }

    @Transactional
    @GetMapping("/payment/{id}")
    public String getPayment(@PathVariable long id, Principal principal) {

        Product p = productService.getProduct(id);
        if (p.getQuantity() == 1)
            productService.delete(id);
        else
            p.setQuantity((p.getQuantity()) - 1);
        productService.saveProduct(p);

        cartService.deleteCart(principal.getName(), id);

        return "payment";
    }
    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteCart(@PathVariable long id, Principal principal) {

        cartService.deleteCart(principal.getName(), id);
        return "redirect:/cart";
    }
}