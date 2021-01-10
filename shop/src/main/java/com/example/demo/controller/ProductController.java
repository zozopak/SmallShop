package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    @Qualifier("productServiceImp")
    public void set(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model){
        List<Product> products=productService.getAllProduct();
        model.addAttribute("products",products);
        return "products";
    }
    @GetMapping("/signup")
    public String goRegister(Product product){
        return "add-product";
    }

    @PostMapping("/add")
    public String upload( @RequestParam("image") MultipartFile multipartFile,@RequestParam String name,
                          @RequestParam String quantity, @RequestParam String price,@RequestParam String color) {
       productService.saveProductToDB(name,quantity,price,color,multipartFile);
       return "redirect:/products";
    }


}