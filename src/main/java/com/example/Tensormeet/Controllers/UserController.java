package com.example.Tensormeet.Controllers;

import com.example.Tensormeet.Model.AppUser;
import com.example.Tensormeet.Model.ResponseMessage;
import com.example.Tensormeet.Model.Username;
import com.example.Tensormeet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class UserController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/api/register")
    public ResponseMessage register(@RequestBody AppUser clientuser){
        if(userRepository.findByUsername(clientuser.getUsername())!=null){
            return new ResponseMessage("username not available");
        }
        AppUser user=new AppUser();
        user.setUsername(clientuser.getUsername());
        user.setPassword(clientuser.getPassword());
        user.setRole("USER");
        userRepository.save(user);
        return new ResponseMessage("success");
    }
    @GetMapping("/username")
    public Username getCurrentUser(Principal principal){
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user==null){
            return new Username();
        }
        return new Username(user.getUsername());
    }
    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    @GetMapping("/users")
    public List<Username> getUsers(){
        List<AppUser> appUsers= userRepository.findAll();
        List<Username> users=new ArrayList<>();
        for(AppUser appUser:appUsers){
            users.add(new Username(appUser.getUsername()));
        }
        return users;
    }
}

