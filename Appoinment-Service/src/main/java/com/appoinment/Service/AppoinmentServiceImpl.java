package com.appoinment.Service;
import com.appoinment.Exception.AppoinmentIdNotFound;
import com.medicrm.exception.HospitalIdNotFound;
import feign.FeignException;

import com.appoinment.DTO.AppoinmentDTO;

import com.appoinment.Microservice.HospitalService;
import com.appoinment.Respositry.AppoinmentRepositry;
import com.appoinment.entity.Appoinment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppoinmentServiceImpl implements AppoinmentService{

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AppoinmentRepositry appoinmentRepositry;
    @Autowired
    HospitalService hospitalService;




// Add the appoinment in a Particular Hospital

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

    // get all Appoinment
    @Override
    public List<AppoinmentDTO> all() {
        List<Appoinment> all = appoinmentRepositry.findAll();

        return all.stream()
                .map(appoinment -> modelMapper.map(all,AppoinmentDTO.class))
                .toList();
    }

    // get single appoinment
    @Override
    public AppoinmentDTO getSingleAppId(Long appId) {

        Optional<Appoinment> byId = appoinmentRepositry.findById(appId);
        if(byId==null)
        {
            throw new AppoinmentIdNotFound("Appoinment Id Not Found");
        }

        return modelMapper.map(byId,AppoinmentDTO.class);
    }


    // update the code by id


    @Override
    public AppoinmentDTO updateApp(Long appId, AppoinmentDTO appoinmentDTO) {

        Appoinment byId = appoinmentRepositry.findById(appId)
                .orElseThrow(() ->
                        new AppoinmentIdNotFound(
                                "Appointment Id not found: " + appId
                        )
                );

        Appoinment map = modelMapper.map(appoinmentDTO, Appoinment.class);

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

    @Override
    public String deleteApp(Long appId) {
        Optional<Appoinment> byId = appoinmentRepositry.findById(appId);
        if(byId==null)
        {
            throw new AppoinmentIdNotFound("Appoinment id not found");
        }

        appoinmentRepositry.deleteById(appId);
        return "Appoinment Deleted";
    }
}
