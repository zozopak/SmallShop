package com.example.demo.controller;

import com.example.demo.model.Authorities;
import com.example.demo.model.Users;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private AuthorityService authorityService;

    @Autowired
    @Qualifier("userServiceImp")
    public void set(UserService userService){
        this.userService=userService;
    }


    @Autowired
    @Qualifier("authorithyServiceImp")
    public void set(AuthorityService authorityService){
        this.authorityService=authorityService;
    }


    @GetMapping("/signup")
    public String goRegister(Users users){
        return "add-user";
    }
    @PostMapping("/add")
    public String addUser( Users users, Model model){
        users.setPassword("{noop}"+(users.getPassword()));
        users.setEnabled((byte) 1);


        userService.saveUser(users);
        Authorities authorities=new Authorities();
        authorityService.SaveAuthority(authorities,users);
       List<Users> usersList= userService.findAll();
        model.addAttribute("users",usersList);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public  String editUser(@PathVariable String id ,Model model){
        Optional<Users> user=userService.getUser(id);
        model.addAttribute("user",user);
        return "update-user";
    }
    @Transactional
   @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id,Model model){
        authorityService.deleteAuthority(id);
        userService.deleteUser(id);
        List<Users>users=userService.findAll();
        model.addAttribute("users",users);
        return "redirect:/users";
   }

   @GetMapping
   public String index(Model model){
        List<Users> users=userService.findAll();
        model.addAttribute("usersList",users);
        return "users";
   }
}
