package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.Users;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private ProductService productService;

    @Autowired
    @Qualifier("productServiceImp")
    public void set(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product-register")
    public String goRegister(Product product) {
        return "add-product";
    }

    @PostMapping("/add")
    public String upload(@RequestParam("image") MultipartFile multipartFile, @RequestParam String name,
                         @RequestParam String model, @RequestParam String price, @RequestParam String color,@RequestParam long quantity) {
        productService.saveProductToDB(name, model, price, color, multipartFile,quantity);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        productService.delete(id);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id,Model model){
      Product p=  productService.getProduct(id);
      model.addAttribute("product",p);
      return "edit-product";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable long id ,@RequestParam("image") MultipartFile multipartFile, @RequestParam String name,
                         @RequestParam String model, @RequestParam String price, @RequestParam String color,@RequestParam long quantity){
        productService.editProductInDB(id,name,model,price,color,multipartFile,quantity);
        return "redirect:/";

    }

    @GetMapping("/acsess-denied")
    public String accessError() {
        return "acsess-denied";
    }

    @GetMapping("/user-register")
    public String goRegister(Users users){
        return "add-user";
    }
}

