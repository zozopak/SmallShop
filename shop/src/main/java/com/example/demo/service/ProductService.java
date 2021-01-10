package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    void  saveProductToDB(String name,String model,String price,String color,MultipartFile file);
   Product get(long id);
    List<Product> getAllProduct();
   void delete(long id);
}
