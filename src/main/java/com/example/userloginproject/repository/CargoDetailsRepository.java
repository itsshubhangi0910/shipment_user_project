package com.example.userloginproject.repository;

import com.example.userloginproject.model.CargoDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoDetailsRepository extends JpaRepository<CargoDetails,Long> {
    List<CargoDetails> findAllByIsDeleted(boolean b);
}
