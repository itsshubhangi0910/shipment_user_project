package com.example.userloginproject.repository;

import com.example.userloginproject.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    boolean existsByEmail(String email);

    Company findByEmail(String email);

    Company findByEmailAndIsDeleted(String email, boolean b);
}
