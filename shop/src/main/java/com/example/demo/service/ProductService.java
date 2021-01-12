package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    void  saveProductToDB(String name,String model,String price,String color,MultipartFile file,long quantity);
   Product get(long id);
    List<Product> getAllProduct();
   void delete(long id);
   Product getProduct(long id);
   void saveProduct(Product p);
   void editProductInDB(long id,String name,String model,String price,String color,MultipartFile file,long quantity);
}
