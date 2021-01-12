package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductService productService;

    @Autowired
    @Qualifier("productServiceImp")
    public void set(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public String getCart(@PathVariable(name = "id") long id, Model model) {
        Product product = productService.get(id);
        List<Product> p = new ArrayList<>();
        p.add(product);
        model.addAttribute("p", p);
        return "cart";
    }

    @GetMapping("/payment/{id}")
    public String getPayment(@PathVariable long id){
       Product p= productService.getProduct(id);
       if(p.getQuantity()==1)
        productService.delete(id);
        else
            p.setQuantity((p.getQuantity())-1);
        productService.saveProduct(p);
        return "payment";
    }
}
