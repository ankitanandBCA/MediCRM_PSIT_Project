package com.appoinment.Service;

import com.appoinment.DTO.AppoinmentDTO;
import com.appoinment.Exception.AppoinmentIdNotFound;
import com.appoinment.Microservice.HospitalService;
import com.appoinment.Respositry.AppoinmentRepositry;
import com.appoinment.entity.Appoinment;
import com.medicrm.exception.HospitalIdNotFound;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppoinmentServiceImpl implements AppoinmentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AppoinmentRepositry appoinmentRepositry;

    @Autowired
    HospitalService hospitalService;


    // Add Appointment
    @Override
    public AppoinmentDTO add(AppoinmentDTO appoinmentDTO) {

        Appoinment map = modelMapper.map(appoinmentDTO, Appoinment.class);

        try {

            hospitalService.getSingleHospital(map.getHospitalId());

        } catch (FeignException e) {

            throw new HospitalIdNotFound(
                    "Hospital Id not found: " + map.getHospitalId()
            );
        }

        Appoinment save = appoinmentRepositry.save(map);

        return modelMapper.map(save, AppoinmentDTO.class);
    }


    // Get All Appointment
    @Override
    public List<AppoinmentDTO> all() {

        List<Appoinment> all = appoinmentRepositry.findAll();

        return all.stream()
                .map(appoinment ->
                        modelMapper.map(appoinment, AppoinmentDTO.class)
                )
                .toList();
    }


    // Get Single Appointment
    @Override
    public AppoinmentDTO getSingleAppId(Long appId) {

        Appoinment appoinment = appoinmentRepositry.findById(appId)
                .orElseThrow(() ->
                        new AppoinmentIdNotFound(
                                "Appoinment Id Not Found: " + appId
                        )
                );

        return modelMapper.map(appoinment, AppoinmentDTO.class);
    }


    // Update Appointment
    @Override
    public AppoinmentDTO updateApp(
            Long appId,
            AppoinmentDTO appoinmentDTO) {

        Appoinment byId = appoinmentRepositry.findById(appId)
                .orElseThrow(() ->
                        new AppoinmentIdNotFound(
                                "Appointment Id not found: " + appId
                        )
                );

        Appoinment map = modelMapper.map(
                appoinmentDTO,
                Appoinment.class
        );

        byId.setName(map.getName());
        byId.setAge(map.getAge());
        byId.setEmail(map.getEmail());
        byId.setGender(map.getGender());
        byId.setAddress(map.getAddress());
        byId.setHospitalId(map.getHospitalId());
        byId.setPhone(map.getPhone());

        Appoinment save = appoinmentRepositry.save(byId);

        return modelMapper.map(save, AppoinmentDTO.class);
    }


    // Delete Appointment
    @Override
    public String deleteApp(Long appId) {

        if (!appoinmentRepositry.existsById(appId)) {

            throw new AppoinmentIdNotFound(
                    "Appoinment Id not found: " + appId
            );
        }

        appoinmentRepositry.deleteById(appId);

        return "Appoinment Deleted";
    }


    // Find Appointment by Hospital ID


    public List<Appoinment> findyHospital(Long hospitalId) {

        return appoinmentRepositry.findByHospitalId(hospitalId);
    }
}