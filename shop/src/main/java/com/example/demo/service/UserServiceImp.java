package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
   private UsersRepo usersRepo;

   @Autowired
   public void setUserRepo(UsersRepo usersRepo){
       this.usersRepo = usersRepo;
   }


    @Override
    public List<Users> findAll() {
       return usersRepo.findAll();
    }

    @Override
    public Users getUser(String id) {
        return usersRepo.getOne(id);
    }

    @Override
    public void deleteUser(String id) {
      usersRepo.deleteById(id);
    }

    @Override
    public void saveUser(Users users) {
        users.setPassword("{noop}"+(users.getPassword()));
       usersRepo.save(users);
    }
}
