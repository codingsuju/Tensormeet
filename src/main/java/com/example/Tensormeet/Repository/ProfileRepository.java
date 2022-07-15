package com.example.Tensormeet.Repository;
import com.example.Tensormeet.Model.AppUser;
import com.example.Tensormeet.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile findByUsername(String username);
}