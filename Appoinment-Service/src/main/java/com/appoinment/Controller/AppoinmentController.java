package com.appoinment.Controller;

import com.appoinment.DTO.AppoinmentDTO;
import com.appoinment.Service.AppoinmentServiceImpl;
import com.appoinment.entity.Appoinment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appoinment")
public class AppoinmentController {

    @Autowired
    private AppoinmentServiceImpl appoinmentService;

    // Add Appointment
    @PostMapping("/add")
    public AppoinmentDTO add(@RequestBody AppoinmentDTO appoinmentDTO) {
        return appoinmentService.add(appoinmentDTO);
    }

    // Get All Appointment
    @GetMapping("/all")
    public List<AppoinmentDTO> allApp() {
        return appoinmentService.all();
    }

    // Get Single Appointment
    @GetMapping("/all/{appId}")
    public AppoinmentDTO getSingleData(@PathVariable Long appId) {
        return appoinmentService.getSingleAppId(appId);
    }

    // Update Appointment
    @PutMapping("/ups/{appId}")
    public AppoinmentDTO updateAdd(
            @PathVariable Long appId,
            @RequestBody AppoinmentDTO appoinmentDTO) {

        return appoinmentService.updateApp(appId, appoinmentDTO);
    }

    // Delete Appointment
    @DeleteMapping("/dels/{appId}")
    public String deleteAppoinment(@PathVariable Long appId) {
        return appoinmentService.deleteApp(appId);
    }

    // Get Appointment by Hospital ID
    @GetMapping("/hospital/{hospitalId}")
    public List<Appoinment> findHospital(@PathVariable Long hospitalId) {

        return appoinmentService.findyHospital(hospitalId);
    }
}