package com.example.userloginproject.repository;

import com.example.userloginproject.model.CargoBoxDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoBoxDetailsRepository extends JpaRepository<CargoBoxDetails,Long> {
   // List<CargoBoxDetails> findAllByCargoIdIsDeleted(Long cargoId, boolean b);

    List<CargoBoxDetails> findAllByCargoIdAndIsDeleted(Long cargoId, boolean b);

   /* @Query("SELECT cbd FROM CargoBoxDetails cbd WHERE cbd.cargoId = :cargoId AND cbd.isDeleted = false")
    List<CargoBoxDetails> getAllByCargoBoxIdIsDeleted(@Param("cargoId") Long cargoId);*/

    //List<CargoBoxDetails> getAllByCargoBoxIdIsDeleted(Long cargoId, boolean b);
}
