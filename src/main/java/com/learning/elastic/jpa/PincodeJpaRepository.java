package com.learning.elastic.jpa;

import com.learning.elastic.model.PincodeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PincodeJpaRepository extends JpaRepository<PincodeMaster, Integer> {

    @Query("FROM PincodeMaster p WHERE p.zipcode = :zipcode")
    List<PincodeMaster> findByZipCodefromDb(@Param("zipcode") String pincode);
}
