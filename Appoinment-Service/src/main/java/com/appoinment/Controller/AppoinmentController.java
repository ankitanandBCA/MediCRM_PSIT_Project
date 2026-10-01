package com.appoinment.Controller;

import com.appoinment.DTO.AppoinmentDTO;
import com.appoinment.Service.AppoinmentServiceImpl;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public List<AppoinmentDTO> allApp()
    {
        return appoinmentService.all();
    }
}
