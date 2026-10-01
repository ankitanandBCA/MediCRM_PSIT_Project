🏥 MediCRM -- Hospital Management System

MediCRM is a microservices-based Hospital Management System
designed to connect hospitals, doctors, patients, appointments, beds,
medicines, prescriptions, emergency services and billing in one
centralized platform.

The system focuses on making hospital discovery and patient care easier
by helping users find the right hospital, discover available doctors
and beds, book appointments according to consultation time, and handle
emergency requirements efficiently.

🚀 Project Overview

MediCRM is developed using a Spring Boot Microservices Architecture.
The backend is divided into independent services so that each major
hospital-management module can be developed, deployed and scaled
separately.

🎯 Main Goals

🔎 Easily find the right hospital for a doctor or patient.

👨‍⚕️ Find which doctors are available in a particular hospital.

🛏️ Check whether beds are available in a hospital.

🚑 Find a suitable hospital during an emergency.

📅 Book appointments according to a doctor's consultation time.

💊 Manage prescriptions and medicines.

🧪 Manage lab tests and medical history.

🏥 Manage rooms, admissions and room allocations.

💳 Manage billing, bill items and payments.

🔐 Keep hospital data isolated using hospital_id.

🏗️ System Architecture

flowchart TB

    Client["🌐 Web / Frontend"]

    Gateway["🚪 API Gateway"]

    Eureka["🧭 Eureka Server"]

    LB["⚖️ Load Balancer"]

    Hospital["🏥 Hospital Service"]
    Department["🏢 Department Service"]
    Doctor["👨‍⚕️ Doctor Service"]
    Staff["👨‍💼 Staff Service"]
    User["👤 User Service"]
    Room["🛏️ Room Service"]
    Medicine["💊 Medicine Service"]

    Patient["🧑‍🦽 Patient Service"]
    Appointment["📅 Appointment Service"]
    Prescription["💉 Prescription Service"]
    Lab["🧪 Lab Test Service"]
    Medical["📋 Medical History Service"]
    Billing["💳 Billing Service"]

    Client --> Gateway
    Gateway --> LB

    LB --> Hospital
    LB --> Department
    LB --> Doctor
    LB --> Staff
    LB --> User
    LB --> Room
    LB --> Medicine
    LB --> Patient
    LB --> Appointment
    LB --> Prescription
    LB --> Lab
    LB --> Medical
    LB --> Billing

    Eureka -. Service Discovery .-> Hospital
    Eureka -. Service Discovery .-> Department
    Eureka -. Service Discovery .-> Doctor
    Eureka -. Service Discovery .-> Staff
    Eureka -. Service Discovery .-> Room
    Eureka -. Service Discovery .-> Patient
    Eureka -. Service Discovery .-> Appointment
    Eureka -. Service Discovery .-> Prescription
    Eureka -. Service Discovery .-> Billing

🛠️ Backend Technology Stack

Technology             Purpose

☕ Java                Backend programming language
🌱 Spring Boot         Microservice development
🗃️ Spring Data JPA     Database interaction / ORM
🌐 REST API            Communication with frontend and services
🔗 Microservices       Independent business modules
🚪 API Gateway         Single entry point for APIs
🧭 Eureka Server       Service discovery
⚖️ Load Balancer       Distributes requests between service instances
🔄 OpenFeign           Communication between microservices
🐬 MySQL               Relational database
🧩 ModelMapper         Entity ↔ DTO mapping
📖 Swagger / OpenAPI   API documentation
🐙 Git / GitHub        Version control

🗄️ Database Design

The database is divided into two major areas:

Part 1 -- Hospital Management

Hospital
   │
   ├── Departments
   │      └── Doctors
   │
   ├── Staff
   │
   ├── Users
   │
   ├── Rooms
   │
   └── Medicines

Part 2 -- Patient Management

Patient
   │
   ├── Appointments
   │       └── Doctor
   │
   ├── Prescriptions
   │       └── Prescription Items
   │              └── Medicines
   │
   ├── Lab Tests
   │
   ├── Room Allocations
   │
   ├── Medical History
   │
   ├── Emergency Contacts
   │
   ├── Insurance
   │
   └── Bills
          └── Bill Items

🧩 Core Database Entities

Hospital Side

hospitals

departments

doctors

staffs

users

rooms

medicines

Patient Side

patients

appointments

prescriptions

prescription_items

lab_tests

room_allocations

medical_history

emergency_contacts

insurance

bills

bill_items

🔗 Important Relationships

erDiagram

    HOSPITALS ||--o{ DEPARTMENTS : contains
    HOSPITALS ||--o{ DOCTORS : has
    HOSPITALS ||--o{ STAFFS : employs
    HOSPITALS ||--o{ ROOMS : owns
    HOSPITALS ||--o{ MEDICINES : stores
    HOSPITALS ||--o{ PATIENTS : registers

    DEPARTMENTS ||--o{ DOCTORS : manages

    PATIENTS ||--o{ APPOINTMENTS : books
    DOCTORS ||--o{ APPOINTMENTS : handles

    PATIENTS ||--o{ PRESCRIPTIONS : receives
    DOCTORS ||--o{ PRESCRIPTIONS : creates
    PRESCRIPTIONS ||--o{ PRESCRIPTION_ITEMS : contains

    MEDICINES ||--o{ PRESCRIPTION_ITEMS : prescribed

    PATIENTS ||--o{ LAB_TESTS : takes
    PATIENTS ||--o{ ROOM_ALLOCATIONS : allocated
    PATIENTS ||--o{ MEDICAL_HISTORY : has
    PATIENTS ||--o{ EMERGENCY_CONTACTS : has
    PATIENTS ||--o{ INSURANCE : owns

    PATIENTS ||--o{ BILLS : receives
    BILLS ||--o{ BILL_ITEMS : contains

⭐ Key Features

1. 🔎 Find the Right Hospital

MediCRM helps users discover hospitals based on their requirements.

A patient or doctor can find:

Hospital information

Hospital location/details

Available departments

Doctors associated with the hospital

Available rooms/beds

Hospital services

Example Flow

User
 ↓
Search Hospital
 ↓
Hospital Service
 ↓
Find Hospital
 ↓
Check Doctors + Beds + Services
 ↓
Display Suitable Hospital

2. 👨‍⚕️ Find Doctor + 🛏️ Bed Availability

A major feature of MediCRM is connecting doctor availability with
hospital availability.

Users can check:

Hospital
   │
   ├── 👨‍⚕️ Doctors
   │      ├── Doctor Name
   │      ├── Specialization
   │      ├── Experience
   │      └── Consultation Fee
   │
   └── 🛏️ Rooms / Beds
          ├── Total Beds
          ├── Occupied Beds
          └── Available Beds

This helps a patient understand:

Which hospital has the required doctor and whether a bed is
available there.

3. 🚑 Emergency Hospital Discovery

During an emergency, finding a suitable hospital quickly is important.

MediCRM is designed to support emergency hospital discovery by combining
hospital information with:

🏥 Hospital availability

👨‍⚕️ Doctor availability

🛏️ Bed availability

🩺 Medical services

📍 Hospital information

Emergency Flow

flowchart LR

    A["🚑 Emergency"] --> B["🔎 Find Hospital"]
    B --> C["🏥 Check Hospital"]
    C --> D["👨‍⚕️ Check Doctor"]
    C --> E["🛏️ Check Bed"]
    C --> F["🩺 Check Services"]

    D --> G["✅ Suitable Hospital"]
    E --> G
    F --> G

    G --> H["📞 Contact / Visit Hospital"]

The goal is to reduce the time required to identify a hospital that can
handle the patient's requirement.

4. 📅 Appointment Booking According to Consultation Time

MediCRM supports appointment booking based on a doctor's consultation
schedule.

Appointment Flow

Patient
   ↓
Select Hospital
   ↓
Select Department
   ↓
Select Doctor
   ↓
Check Consultation Time
   ↓
Select Available Slot
   ↓
Book Appointment
   ↓
Appointment Confirmed

This avoids unnecessary waiting and helps organize doctor consultations.

🧑‍⚕️ Patient Journey

flowchart LR

    A["👤 Registration"] -->
    B["📅 Appointment"] -->
    C["👨‍⚕️ Consultation"] -->
    D["💊 Prescription"] -->
    E["🧪 Lab Test"] -->
    F["🛏️ Admission"] -->
    G["💳 Billing"] -->
    H["💰 Payment"]

Patient Journey

Registration -- Patient creates/registers an account.

Appointment -- Patient books an appointment.

Consultation -- Doctor consults the patient.

Prescription -- Doctor creates prescription.

Lab Test -- Required tests are generated.

Admission -- Patient can be admitted when required.

Billing -- Hospital generates the bill.

Payment -- Payment is completed.

🏥 Hospital Management Flow

flowchart TB

    H["🏥 Hospital"]

    H --> D["🏢 Departments"]
    H --> DOC["👨‍⚕️ Doctors"]
    H --> S["👨‍💼 Staff"]
    H --> R["🛏️ Rooms"]
    H --> M["💊 Medicines"]
    H --> U["👤 Users"]

    D --> DOC
    R --> B["🛏️ Bed Availability"]

🔄 Microservice Communication

MediCRM uses REST APIs and OpenFeign for communication between services.

Example:

Appointment Service
        │
        │ hospitalId
        ↓
   Hospital Service
        │
        ↓
 Hospital Validation

For example, before creating an appointment, the Appointment Service can
communicate with the Hospital Service to verify that the supplied
hospitalId exists.

Similarly, services can communicate using service names registered with
Eureka.

Appointment Service
        │
        ↓
    OpenFeign
        │
        ↓
 Eureka Server
        │
        ↓
 HOSPITAL-SERVICE

🧭 Service Discovery

The project uses Eureka Server for service discovery.

                 🧭 Eureka Server
                       │
        ┌──────────────┼──────────────┐
        ↓              ↓              ↓
 HOSPITAL-SERVICE  DOCTOR-SERVICE  ROOM-SERVICE
        │              │              │
        └──────────────┼──────────────┘
                       ↓
                Service Discovery

Instead of hardcoding service IP addresses, microservices can
communicate using registered service names.

Example:

@FeignClient(name = "HOSPITAL-SERVICE")

🚪 API Gateway

The API Gateway acts as the common entry point for client requests.

Frontend
   │
   ↓
API Gateway
   │
   ├── /api/hospital/**
   ├── /api/doctor/**
   ├── /api/staff/**
   ├── /api/room/**
   ├── /api/patient/**
   ├── /api/appointment/**
   ├── /api/prescription/**
   └── /api/billing/**

The Gateway routes requests to the appropriate microservice.

⚖️ Load Balancing

Load balancing is used when multiple instances of a microservice are
running.

                 API Gateway
                      │
                      ↓
                Load Balancer
                  /       \
                 /         \
                ↓           ↓
        Doctor Service   Doctor Service
          Instance 1       Instance 2

This helps distribute requests between available service instances.

🏨 Multi-Hospital Data Isolation

A key design principle of MediCRM is hospital-based data isolation.

Most hospital-related entities contain:

hospital_id

Example:

Hospital 1
 ├── Doctors
 ├── Staff
 ├── Patients
 ├── Rooms
 └── Medicines

Hospital 2
 ├── Doctors
 ├── Staff
 ├── Patients
 ├── Rooms
 └── Medicines

Data can therefore be filtered using hospital_id.

GET /api/doctor/hospital/{hospitalId}

GET /api/staff/hospital/{hospitalId}

GET /api/room/hospital/{hospitalId}

This design helps keep different hospitals' operational data separated.

🔐 Backend Design Principles

RESTful API architecture

Microservice-based modules

Database access using Spring Data JPA

Service discovery using Eureka

API routing through API Gateway

Load balancing between service instances

OpenFeign for inter-service communication

Hospital-wise data isolation using hospital_id

DTO-based API layer where required

Centralized exception handling

Swagger/OpenAPI documentation

📁 Typical Microservice Structure

Each service follows a clean layered structure:

SERVICE
│
├── Controller
│     └── REST APIs
│
├── Service
│     └── Business Logic
│
├── Repository
│     └── Database Operations
│
├── Entity
│     └── JPA Entity
│
├── DTO
│     └── Request / Response Objects
│
├── Exception
│     └── Custom Exceptions
│
├── Microservice
│     └── Feign Clients
│
└── Config
      └── Configuration

📊 Project Highlights

Feature                 Description

🏥 Hospital Discovery   Find suitable hospitals
👨‍⚕️ Doctor Discovery     Find doctors by hospital/department
🛏️ Bed Availability     Check available and occupied beds
🚑 Emergency Support    Identify suitable hospital resources
📅 Appointment          Book consultation slots
💊 Prescription         Manage prescriptions and medicines
🧪 Lab Tests            Manage patient tests and reports
🛏️ Admission            Manage room allocations
📋 Medical History      Maintain patient history
💳 Billing              Generate bills and bill items
🔄 Microservices        Independent backend services
🧭 Eureka               Service discovery
🚪 API Gateway          Central API routing
⚖️ Load Balancer        Request distribution
🔗 OpenFeign            Service-to-service communication

🎯 Future Scope

MediCRM can be extended with:

🤖 AI-assisted hospital recommendation

🧠 AI-based symptom/department guidance

📍 Location-based hospital discovery

🚑 Emergency ambulance integration

🔔 Appointment notifications

📱 SMS/Email notifications

💳 Online payment integration

📊 Hospital analytics dashboard

📈 Doctor availability analytics

🧪 Digital lab reports

🩸 Blood/donor management

☁️ Cloud deployment and containerization

🏁 Final Architecture Summary

                        ┌──────────────────┐
                        │     FRONTEND     │
                        └────────┬─────────┘
                                 │
                                 ▼
                        ┌──────────────────┐
                        │   API GATEWAY    │
                        └────────┬─────────┘
                                 │
                                 ▼
                        ┌──────────────────┐
                        │  LOAD BALANCER   │
                        └────────┬─────────┘
                                 │
              ┌──────────────────┼──────────────────┐
              │                  │                  │
              ▼                  ▼                  ▼
       Hospital Service    Doctor Service     Patient Service
              │                  │                  │
              ├──────────┬───────┘                  │
              │          │                          │
              ▼          ▼                          ▼
        Room Service  Staff Service          Appointment Service
              │                                      │
              └──────────────┬───────────────────────┘
                             │
                             ▼
                    Prescription / Lab /
                    Medical / Billing
                             │
                             ▼
                           MySQL

                    ▲
                    │
              Eureka Server
            Service Discovery

💡 Core Idea

MediCRM connects hospital infrastructure with the complete patient
journey --- from finding the right hospital and doctor to appointment,
consultation, prescription, lab testing, admission, billing and
payment.

The system is designed around hospital-wise data management,
microservice architecture, and easy access to hospital resources
such as doctors, rooms and beds.

👨‍💻 Backend Focus

Java + Spring Boot + Spring Data JPA + REST API + Microservices + API
Gateway + Eureka Server + Load Balancing + OpenFeign + MySQL

📌 Project Status

🚧 MediCRM is under active development.

New microservices and hospital-management modules are being added
incrementally.
