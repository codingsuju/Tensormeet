package com.example.Tensormeet.Controllers;

import com.example.Tensormeet.Model.AppUser;
import com.example.Tensormeet.Model.Message;
import com.example.Tensormeet.Model.Username;
import com.example.Tensormeet.Repository.MessageRepository;
import com.example.Tensormeet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class MessageController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;
    @GetMapping("/user/{username}/chats")
    public List<Username> getChats(@PathVariable String username, Principal principal){
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            Set<String> chats=new HashSet<>();
            for(Message message:messageRepository.findBySender(username)){
                chats.add(message.getReceiver());
            }
            for(Message message:messageRepository.findByReceiver(username)){
                chats.add(message.getSender());
            }
            List<Username> receivers=new ArrayList<>();
            for(String receiver:chats){
                receivers.add(new Username(receiver));
            }
            return receivers;
        }
        else throw new AuthorizationServiceException("Access Denied");
    }
    @PostMapping("/user/{username}/messages")
    public List<Message> getChats(@PathVariable String username,@RequestBody Message r_message, Principal principal){
        String friendname=r_message.getReceiver();
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            List<Message> messages=new ArrayList<>();
            for(Message message:messageRepository.findBySender(username)){
                if(message.getReceiver().equals(friendname)){
                    messages.add(message);
                }
            }
            for(Message message:messageRepository.findByReceiver(username)){
                if(message.getSender().equals(friendname)){
                    messages.add(message);
                }
            }
            Collections.sort(messages,(Message a,Message b)->{
                int res=0;
                if(a.getId()<b.getId()){
                    res=-1;
                }
                else if(a.getId()>b.getId()){
                    res=1;
                }
                return res;
            });
            return messages;
        }
        else throw new AuthorizationServiceException("Access Denied");
    }
    @PostMapping("/user/{username}/message")
    public Message sendMessage(@PathVariable String username, @RequestBody Message message, Principal principal){
        AppUser user=userRepository.findByUsername(principal.getName());
        if(user.getUsername().equals(username)){
            AppUser friend=userRepository.findByUsername(message.getReceiver());
            if(friend==null){
                return new Message();
            }
            else {
                Message m = new Message();
                m.setSender(username);
                m.setReceiver(message.getReceiver());
                m.setText(message.getText());
                return messageRepository.save(m);
            }
        }
        else throw new AuthorizationServiceException("Access Denied");
    }
}
