package com.appoinment.Respositry;

import com.appoinment.entity.Appoinment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppoinmentRepositry extends JpaRepository<Appoinment,Long> {

    void findByHospitalId(Long hospitalId);
}
