package com.blog.repository;

import com.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findAllByName(String name);

    Boolean existsByName(String name);

    List<User> findAllByNameStartingWith(String word);
}
