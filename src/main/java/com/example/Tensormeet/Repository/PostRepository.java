package com.example.Tensormeet.Repository;
import com.example.Tensormeet.Model.AppUser;
import com.example.Tensormeet.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUsername(String username);
}