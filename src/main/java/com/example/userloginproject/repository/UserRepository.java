package com.example.userloginproject.repository;

import com.example.userloginproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT u FROM User as u",nativeQuery = false)
    Page<User> getAllUser(Pageable pageable);

    boolean existsByEmail(String email);

    boolean existsByMobileNo(String mobileNo);

    boolean existsByEmailIgnoreCaseAndIsDeleted(String email, boolean b);

    boolean existsByMobileNoAndUserIdNotIn(String mobileNo, List<Long> userIds);

    boolean existsByEmailAndUserIdNotIn(String email, List<Long> userIds);
}
