package com.example.store.Repository.AuthRepository;

import com.example.store.Model.Authentification.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("SELECT u.email FROM User u WHERE u.username = :username")
    Optional<String> findEmailByName(String username);







}
