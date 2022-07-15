package com.example.Tensormeet.Repository;
import com.example.Tensormeet.Model.AppUser;
import com.example.Tensormeet.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findBySender(String sender);
    List<Message> findByReceiver(String receiver);
}