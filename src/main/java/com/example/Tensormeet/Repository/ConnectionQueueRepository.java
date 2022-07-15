package com.example.Tensormeet.Repository;

import com.example.Tensormeet.Model.Connection;
import com.example.Tensormeet.Model.ConnectionQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionQueueRepository extends JpaRepository<ConnectionQueue,Integer> {
    List<ConnectionQueue> findByUsername1(String username1);
    List<ConnectionQueue> findByUsername2(String username2);
}
