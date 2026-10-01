package com.appoinment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.PrimitiveIterator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Appoinment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private long age;
    private long hospitalId;
}
