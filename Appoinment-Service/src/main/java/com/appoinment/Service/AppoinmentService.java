package com.appoinment.Service;

import com.appoinment.DTO.AppoinmentDTO;

import java.util.List;

public interface AppoinmentService
{
      AppoinmentDTO add(AppoinmentDTO appoinmentDTO);

      List<AppoinmentDTO> all();

      AppoinmentDTO getSingleAppId(Long appId);

      AppoinmentDTO updateApp(Long appId,AppoinmentDTO appoinmentDTO);

      String deleteApp(Long appId);
}
