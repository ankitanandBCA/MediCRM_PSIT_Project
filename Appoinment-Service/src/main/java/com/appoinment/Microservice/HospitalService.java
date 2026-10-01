package com.appoinment.Microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOSPITAL-SERVICE")
public interface HospitalService {

    @GetMapping("/api/hospital/{hospitalId}")
    Object getSingleHospital(@PathVariable("hospitalId") Long hospitalId);
}
