package com.appoinment.DTO;

import lombok.Data;

@Data
public class AppoinmentDTO {
    private Long appId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private long age;
    private long hospitalId;
}
