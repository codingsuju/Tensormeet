package com.example.Tensormeet.Controllers;

import com.example.Tensormeet.Model.AppUser;
import com.example.Tensormeet.Model.ConnectionQueue;
import com.example.Tensormeet.Model.Profile;
import com.example.Tensormeet.Repository.ProfileRepository;
import com.example.Tensormeet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class ProfileController {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/user/{username}/profile")
    public Profile getProfile(@PathVariable String username) {
        return profileRepository.findByUsername(username);
    }
    @PutMapping("/user/{username}/profile")
    public Profile editProfile(@PathVariable String username,@RequestBody Profile profile, Principal principal) {
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            Profile tempProfile=profileRepository.findByUsername(username);
            int id=tempProfile.getId();
            tempProfile=profile;
            tempProfile.setUsername(username);
            tempProfile.setId(id);
            return profileRepository.save(tempProfile);
        }
        else return new Profile();
    }
    @PostMapping("/user/{username}/profile")
    public Profile createProfile(@PathVariable String username,@RequestBody Profile profile,Principal principal) {
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            if(profileRepository.findByUsername(username)!=null){
                return new Profile();
            }
            profile.setUsername(username);
            Profile backendProfile=new Profile();
            backendProfile.setProfilename(profile.getProfilename());
            backendProfile.setBio(profile.getBio());
            backendProfile.setInterest(profile.getInterest());
            backendProfile.setUsername(profile.getUsername());
            return profileRepository.save(backendProfile);
        }
        else return new Profile();
    }
}