package com.example.userloginproject.repository;

import com.example.userloginproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT u FROM User as u",nativeQuery = false)
    Page<User> getAllUser(Pageable pageable);
}
