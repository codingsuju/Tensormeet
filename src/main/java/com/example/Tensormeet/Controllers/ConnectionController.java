package com.example.Tensormeet.Controllers;

import com.example.Tensormeet.Model.AppUser;
import com.example.Tensormeet.Model.Connection;
import com.example.Tensormeet.Model.ConnectionQueue;
import com.example.Tensormeet.Model.Username;
import com.example.Tensormeet.Repository.ConnectionQueueRepository;
import com.example.Tensormeet.Repository.ConnectionRepository;
import com.example.Tensormeet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class ConnectionController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConnectionRepository connectionRepository;
    @Autowired
    ConnectionQueueRepository connectionQueueRepository;

    @PostMapping("/user/{username}/add")
    public ConnectionQueue addFriend(@PathVariable String username, @RequestBody Connection r_connection, Principal principal){
        String friendname=r_connection.getUsername2();
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            AppUser friend=userRepository.findByUsername(friendname);
            if(friend==null){
                return new ConnectionQueue();
            }
            else{
                List<Connection> connectionList1=connectionRepository.findByUsername1(username);
                for(Connection connection:connectionList1){
                    if(connection.getUsername2().equals(friendname)){
                        return new ConnectionQueue();
                    }
                }
                List<Connection> connectionList2=connectionRepository.findByUsername2(username);
                for(Connection connection:connectionList2){
                    if(connection.getUsername1().equals(friendname)){
                        return new ConnectionQueue();
                    }
                }
                List<ConnectionQueue> connectionQueueList1=connectionQueueRepository.findByUsername1(username);
                for(ConnectionQueue connectionQueue:connectionQueueList1){
                    if(connectionQueue.getUsername2().equals(friendname)){
                        return new ConnectionQueue();
                    }
                }
                List<ConnectionQueue> connectionQueueList2=connectionQueueRepository.findByUsername2(username);
                for(ConnectionQueue connectionQueue:connectionQueueList2){
                    if(connectionQueue.getUsername1().equals(friendname)){
                        return new ConnectionQueue();
                    }
                }
                ConnectionQueue connectionQueue=new ConnectionQueue();
                connectionQueue.setUsername1(username);
                connectionQueue.setUsername2(friendname);
                return connectionQueueRepository.save(connectionQueue);
            }
        }
        else throw new AuthorizationServiceException("Acces Denied");
    }
    @PostMapping("/user/{username}/accept")
    public Connection confirmRequest(@PathVariable String username,@RequestBody Connection r_connection, Principal principal){
        String sendername=r_connection.getUsername1();
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            AppUser friend=userRepository.findByUsername(sendername);
            if(friend==null){
                return new Connection();
            }
            else{
                List<ConnectionQueue> connectionQueues=connectionQueueRepository.findByUsername2(username);
                ConnectionQueue tempConnectionQueue=null;
                for(ConnectionQueue c:connectionQueues){
                    if(c.getUsername1().equals(sendername)){
                        tempConnectionQueue=c;
                    }
                }
                if(tempConnectionQueue==null){
                    return new Connection();
                }
                Connection connection=new Connection();
                connection.setUsername1(tempConnectionQueue.getUsername1());
                connection.setUsername2(tempConnectionQueue.getUsername2());
                connectionQueueRepository.delete(tempConnectionQueue);
                return connectionRepository.save(connection);
            }
        }
        else throw new AuthorizationServiceException("Acces Denied");
    }
    @PostMapping("/user/{username}/cancel")
    public ConnectionQueue cancelRequest(@PathVariable String username,@RequestBody Connection r_connection, Principal principal){
        String sendername=r_connection.getUsername1();
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            AppUser friend=userRepository.findByUsername(sendername);
            if(friend==null){
                return new ConnectionQueue();
            }
            else{
                for(ConnectionQueue c:connectionQueueRepository.findByUsername2(username)){
                    if(c.getUsername1().equals(sendername)){
                        connectionQueueRepository.delete(c);
                        return c;
                    }
                }
            }
        }
        else throw new AuthorizationServiceException("Acces Denied");
        return new ConnectionQueue();
    }
    @PostMapping("/user/{username}/unsend")
    public ConnectionQueue unsend(@PathVariable String username,@RequestBody Connection r_connection, Principal principal){
        String person=r_connection.getUsername2();
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            AppUser friend=userRepository.findByUsername(person);
            if(friend==null){
                return new ConnectionQueue();
            }
            else{
                for(ConnectionQueue c:connectionQueueRepository.findByUsername1(username)){
                    if(c.getUsername2().equals(person)){
                        connectionQueueRepository.delete(c);
                        return c;
                    }
                }
            }
        }
        else throw new AuthorizationServiceException("Acces Denied");
        return new ConnectionQueue();
    }
    @PostMapping("/user/{username}/remove")
    public Connection removeFriend(@PathVariable String username,@RequestBody Connection r_connection, Principal principal){
        String person=r_connection.getUsername2();
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            AppUser friend=userRepository.findByUsername(person);
            if(friend==null){
                return new Connection();
            }
            else{
                for(Connection c:connectionRepository.findByUsername1(username)){
                    if(c.getUsername2().equals(person)){
                        connectionRepository.delete(c);
                        return c;
                    }
                }
                for(Connection c:connectionRepository.findByUsername2(username)){
                    if(c.getUsername1().equals(person)){
                        connectionRepository.delete(c);
                        return c;
                    }
                }
            }
        }
        else throw new AuthorizationServiceException("Acces Denied");
        return new Connection();
    }
    @GetMapping("/user/{username}/sent_requests")
    public List<ConnectionQueue> viewAllSentRequest(@PathVariable String username,Principal principal){
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            return connectionQueueRepository.findByUsername1(username);
        }
        else throw new AuthorizationServiceException("Access denied");
    }
    @GetMapping("/user/{username}/requests")
    public List<ConnectionQueue> viewAllRequest(@PathVariable String username,Principal principal){
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            return connectionQueueRepository.findByUsername2(username);
        }
        else throw new AuthorizationServiceException("Access denied");
    }
    @GetMapping("/user/{username}/friends")
    List<Username> getFriends(@PathVariable String username){
        List<Username> friends=new ArrayList<>();
        for(Connection connection:connectionRepository.findByUsername1(username)){
            friends.add(new Username(connection.getUsername2()));
        }
        for(Connection connection:connectionRepository.findByUsername2(username)){
            friends.add(new Username(connection.getUsername1()));
        }
        return friends;
    }
}
