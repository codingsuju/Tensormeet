package com.example.Tensormeet.Controllers;

import com.example.Tensormeet.Model.AppUser;
import com.example.Tensormeet.Model.Profile;
import com.example.Tensormeet.Model.ResponseMessage;
import com.example.Tensormeet.Model.Username;
import com.example.Tensormeet.Repository.ProfileRepository;
import com.example.Tensormeet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;
    @PostMapping("/api/register")
    public AppUser register(@RequestBody AppUser clientuser){
        if(userRepository.findByUsername(clientuser.getUsername())!=null){
            return new AppUser();
        }
        AppUser user=new AppUser();
        user.setUsername(clientuser.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(clientuser.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        Profile profile=new Profile();
        profile.setBio("User at Tensormeet");
        profile.setInterest("using Tensormeet");
        profile.setUsername(clientuser.getUsername());
        profile.setProfilename("Tensormeet user");
        profileRepository.save(profile);
        return user;
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

