package com.appoinment.Respositry;

import com.appoinment.entity.Appoinment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppoinmentRepositry extends JpaRepository<Appoinment,Long> {

    List<Appoinment> findByHospitalId(Long hospitalId);
}
