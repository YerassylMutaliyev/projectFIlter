package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Page<User> findAll(Pageable pageable);


    List<User> findByPhotoNameLike(String photoName);


    List<User> findByPhotoNameContainingIgnoreCase(String photoName);


    @Query("SELECT t FROM User t WHERE t.photoName LIKE %:photoName% AND t.photoName = :photoName")
    List<User> findByPhotoName(String photoName);
}

