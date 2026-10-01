package com.appoinment.Controller;

import com.appoinment.DTO.AppoinmentDTO;
import com.appoinment.Service.AppoinmentServiceImpl;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appoinment")
public class AppoinmentController {

    @Autowired
    private AppoinmentServiceImpl appoinmentService;

    @PostMapping("/add")
    public AppoinmentDTO add(@RequestBody AppoinmentDTO appoinmentDTO)
    {
        return appoinmentService.add(appoinmentDTO);
    }
}
