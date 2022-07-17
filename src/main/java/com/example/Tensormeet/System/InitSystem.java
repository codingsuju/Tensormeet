package com.example.Tensormeet.System;
import com.example.Tensormeet.Model.*;
import com.example.Tensormeet.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitSystem {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ConnectionRepository connectionRepository;
    @Autowired
    ConnectionQueueRepository connectionQueueRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    MessageRepository messageRepository;
    @GetMapping("/init/start")
    public String init(){
        //Create Users
        AppUser u1=new AppUser();
        u1.setRole("ADMIN");
        u1.setUsername("codingsuju");
        u1.setPassword("$2a$10$1vBKG9/27DKC/NBToUPPPevdXcuciv8CyVgWereEIOsMgD5mLk9bK");
        userRepository.save(u1);
        AppUser u2=new AppUser();
        u2.setRole("USER");
        u2.setUsername("suju");
        u2.setPassword("$2a$10$/Khgi0V/csaGDfD.Bn/zfeKhi.u4oA30WO85PYJ8ePKY5um62em1e");
        userRepository.save(u2);
        AppUser u3=new AppUser();
        u3.setRole("USER");
        u3.setUsername("rose");
        u3.setPassword("$2a$10$/Khgi0V/csaGDfD.Bn/zfeKhi.u4oA30WO85PYJ8ePKY5um62em1e");
        userRepository.save(u3);
        AppUser u4=new AppUser();
        u4.setRole("USER");
        u4.setUsername("lisa");
        u4.setPassword("$2a$10$/Khgi0V/csaGDfD.Bn/zfeKhi.u4oA30WO85PYJ8ePKY5um62em1e");
        userRepository.save(u4);

        //Create Posts
        Post p1=new Post();
        p1.setUsername("codingsuju");
        p1.setStatus("Hello world from codingsuju");
        postRepository.save(p1);
        Post p2=new Post();
        p2.setUsername("suju");
        p2.setStatus("Hello world from suju");
        postRepository.save(p2);
        Post p3=new Post();
        p3.setUsername("rose");
        p3.setStatus("Hello world from rose");
        postRepository.save(p3);
        Post p4=new Post();
        p4.setUsername("lisa");
        p4.setStatus("Hello world");
        postRepository.save(p4);
        Post p5=new Post();
        p5.setUsername("codingsuju");
        p5.setStatus("Good Morning");
        postRepository.save(p5);

        //Create Connections
        Connection connection1=new Connection();
        connection1.setUsername1("codingsuju");
        connection1.setUsername2("rose");
        connectionRepository.save(connection1);
        Connection connection2=new Connection();
        connection2.setUsername1("codingsuju");
        connection2.setUsername2("lisa");
        connectionRepository.save(connection2);

        //Create ConnectionQueue
        ConnectionQueue connectionQueue=new ConnectionQueue();
        connectionQueue.setUsername1("codingsuju");
        connectionQueue.setUsername2("suju");
        connectionQueueRepository.save(connectionQueue);

        //Create Message
        Message message1=new Message();
        message1.setSender("codingsuju");
        message1.setReceiver("lisa");
        message1.setText("Hi lisa");
        messageRepository.save(message1);
        Message message2=new Message();
        message2.setSender("lisa");
        message2.setReceiver("codingsuju");
        message2.setText("Hi codingsuju");
        messageRepository.save(message2);
        Message message3=new Message();
        message3.setSender("codingsuju");
        message3.setReceiver("rose");
        message3.setText("Hi rose");
        messageRepository.save(message3);

        //Create Profile
        Profile profile1=new Profile();
        profile1.setUsername("codingsuju");
        profile1.setProfilename("Suju Daimary");
        profile1.setBio("I am Developer");
        profile1.setInterest("Football and Music");
        profileRepository.save(profile1);
        Profile profile2=new Profile();
        profile2.setUsername("rose");
        profile2.setProfilename("Rose Rose");
        profile2.setBio("Singer and Songwriter");
        profile2.setInterest("Music");
        profileRepository.save(profile2);
        Profile profile3=new Profile();
        profile3.setUsername("suju");
        profile3.setProfilename("Sujubkang Daimary");
        profile3.setBio("Footballer");
        profile3.setInterest("Music");
        profileRepository.save(profile3);
        Profile profile4=new Profile();
        profile4.setUsername("lisa");
        profile4.setProfilename("Lisa Lisa");
        profile4.setBio("Singer and Dancer");
        profile4.setInterest("Music and Dance");
        profileRepository.save(profile4);

        return "Success";
    }
    @GetMapping("/init/delete")
    public String delete(){
        userRepository.deleteAll();
        connectionQueueRepository.deleteAll();
        connectionRepository.deleteAll();
        postRepository.deleteAll();
        messageRepository.deleteAll();
        profileRepository.deleteAll();;
        return "Success";
    }
}
