package com.example.Tensormeet.Repository;

import com.example.Tensormeet.Model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection,Integer> {
    List<Connection> findByUsername1(String username1);
    List<Connection> findByUsername2(String username2);
}
