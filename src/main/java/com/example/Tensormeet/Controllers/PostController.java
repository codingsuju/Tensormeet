package com.example.Tensormeet.Controllers;

import com.example.Tensormeet.Model.AppUser;
import com.example.Tensormeet.Model.Connection;
import com.example.Tensormeet.Model.Post;
import com.example.Tensormeet.Repository.ConnectionRepository;
import com.example.Tensormeet.Repository.PostRepository;
import com.example.Tensormeet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins ="*",maxAge=3600)
public class PostController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ConnectionRepository connectionRepository;
    @GetMapping("/user/{username}/newsfeed")
    public List<Post> getPost(@PathVariable String username, Principal principal){
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            List<Post> newsFeed=new ArrayList<>();
            List<String> friends =new ArrayList<>();
            for(Connection connection:connectionRepository.findByUsername1(username)){
                friends.add(connection.getUsername2());
            }
            for(Connection connection:connectionRepository.findByUsername2(username)){
                friends.add(connection.getUsername1());
            }
            for(String friendname:friends){
               newsFeed.addAll(postRepository.findByUsername(friendname));
            }
            newsFeed.addAll(postRepository.findByUsername(username));
            Collections.sort(newsFeed,(Post a,Post b)->{
                int res=0;
                if(a.getId()<b.getId()){
                    res=-1;
                }
                else if(a.getId()>b.getId()){
                    res=1;
                }
                return res;
            });
            return newsFeed;
        }
        else throw new AuthorizationServiceException("Access denied");
    }
    @GetMapping("/user/{username}/post")
    public List<Post> getUserPost(@PathVariable String username){
        return postRepository.findByUsername(username);
    }
    @PostMapping("/user/{username}/post")
    public Post createPost(@PathVariable String username,@RequestBody Post userpost, Principal principal){
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            Post post=new Post();
            post.setUsername(username);
            post.setStatus(userpost.getStatus());
            return postRepository.save(post);
        }
        return new Post();
    }
}
