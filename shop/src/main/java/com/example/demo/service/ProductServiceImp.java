package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    private ProductRepo productRepo;
    @Autowired
    public void setProductRepo(ProductRepo productRepo){
        this.productRepo=productRepo;
    }

    @Override
    public void saveProductToDB(String name,String model,String price,String color, MultipartFile file,long quantity) {

        Product p=new Product();
        try {
           p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setName(name);
        p.setModel(model);
        p.setPrice(price);
        p.setColor(color);
        p.setQuantity(quantity);
        productRepo.save(p);
    }

    @Override
    public Product get(long id) {
        return productRepo.getOne(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public void delete(long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getProduct(long id) {
     return   productRepo.getOne(id);
    }

    @Override
    public void saveProduct(Product p) {
        productRepo.save(p);
    }

    @Override
    public void editProductInDB(long id, String name, String model, String price, String color, MultipartFile file, long quantity) {

        Product p=new Product();
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setId(id);
        p.setName(name);
        p.setModel(model);
        p.setPrice(price);
        p.setColor(color);
        p.setQuantity(quantity);
        productRepo.save(p);
    }
}
