🏥 MediCRM Backend

MediCRM is a production-oriented, microservices-based Hospital
Management and Hospital Discovery platform built with Java and Spring
Boot.

The backend is designed not only to manage hospital operations, but also
to help patients and doctors find the right hospital based on doctors,
departments, beds, services and availability.

🎯 Vision

MediCRM connects the complete hospital and patient journey:

Hospital Discovery
      ↓
Doctor / Department Discovery
      ↓
Bed Availability
      ↓
Appointment Booking
      ↓
Consultation
      ↓
Prescription / Lab Test
      ↓
Admission
      ↓
Billing
      ↓
Payment
      ↓
Patient History

The long-term goal is to evolve MediCRM into a Hospital CRM + Hospital
Discovery + Emergency Support Platform.

🛠️ Backend Technology Stack

Technology                    Purpose

☕ Java                       Backend programming
🌱 Spring Boot                Microservice development
🗃️ Spring Data JPA            ORM and database access
🌐 REST API                   Client and service communication
🧩 Microservices              Independent business modules
🚪 Spring Cloud API Gateway   Central API entry point
🧭 Eureka Server              Service discovery
⚖️ Load Balancer              Request distribution
🔗 OpenFeign                  Microservice-to-microservice communication
🐬 MySQL                      Relational database
🧠 ModelMapper                Entity / DTO mapping
📖 Swagger / OpenAPI          API documentation
⚡ Redis                      Planned caching layer
🔔 Notification Service       Planned notifications
📝 Audit Service              Planned activity tracking
🤖 AI Service                 Planned recommendation system
🐙 Git / GitHub               Version control

🏗️ Microservices Architecture

flowchart TB

    Client["🌐 Web / Mobile Client"]

    Gateway["🚪 API Gateway"]

    Eureka["🧭 Eureka Server"]

    Hospital["🏥 Hospital Service"]
    Department["🏢 Department Service"]
    Doctor["👨‍⚕️ Doctor Service"]
    Staff["👨‍💼 Staff Service"]
    User["👤 User Service"]
    Room["🛏️ Room / Bed Service"]
    Medicine["💊 Medicine Service"]

    Patient["🧑 Patient Service"]
    Appointment["📅 Appointment Service"]
    Prescription["💉 Prescription Service"]
    Lab["🧪 Lab Service"]
    Medical["📋 Medical History Service"]
    Billing["💳 Billing Service"]

    Notification["🔔 Notification Service"]
    Audit["📝 Audit Service"]
    AI["🤖 AI Recommendation Service"]
    Analytics["📊 Analytics Service"]

    Redis["⚡ Redis Cache"]
    DB["🐬 MySQL"]

    Client --> Gateway

    Gateway --> Hospital
    Gateway --> Department
    Gateway --> Doctor
    Gateway --> Staff
    Gateway --> User
    Gateway --> Room
    Gateway --> Medicine
    Gateway --> Patient
    Gateway --> Appointment
    Gateway --> Prescription
    Gateway --> Lab
    Gateway --> Medical
    Gateway --> Billing

    Hospital -.-> Eureka
    Department -.-> Eureka
    Doctor -.-> Eureka
    Staff -.-> Eureka
    Room -.-> Eureka
    Patient -.-> Eureka
    Appointment -.-> Eureka
    Prescription -.-> Eureka
    Billing -.-> Eureka

    Appointment --> Notification
    Billing --> Notification

    Services["⚙️ Business Services"] --> Redis
    Services --> DB
    Services --> Audit
    Services --> AI
    Services --> Analytics

🧩 Core Services

Hospital Management

🏥 Hospital Service
🏢 Department Service
👨‍⚕️ Doctor Service
👨‍💼 Staff Service
👤 User Service
🛏️ Room / Bed Service
💊 Medicine Service

Patient Management

🧑 Patient Service
📅 Appointment Service
💉 Prescription Service
🧪 Lab Service
📋 Medical History Service
💳 Billing Service

Advanced Services

🔔 Notification Service
📝 Audit Service
📊 Analytics Service
🤖 AI Recommendation Service

🗄️ Database Architecture

Hospital Side

erDiagram

    HOSPITALS ||--o{ DEPARTMENTS : contains
    HOSPITALS ||--o{ DOCTORS : has
    HOSPITALS ||--o{ STAFFS : employs
    HOSPITALS ||--o{ USERS : manages
    HOSPITALS ||--o{ ROOMS : owns
    HOSPITALS ||--o{ MEDICINES : stores

    DEPARTMENTS ||--o{ DOCTORS : manages

Patient Side

erDiagram

    HOSPITALS ||--o{ PATIENTS : registers

    PATIENTS ||--o{ APPOINTMENTS : books
    DOCTORS ||--o{ APPOINTMENTS : handles

    PATIENTS ||--o{ PRESCRIPTIONS : receives
    DOCTORS ||--o{ PRESCRIPTIONS : creates
    PRESCRIPTIONS ||--o{ PRESCRIPTION_ITEMS : contains
    MEDICINES ||--o{ PRESCRIPTION_ITEMS : prescribed

    PATIENTS ||--o{ LAB_TESTS : takes
    PATIENTS ||--o{ ROOM_ALLOCATIONS : receives
    PATIENTS ||--o{ MEDICAL_HISTORY : has
    PATIENTS ||--o{ EMERGENCY_CONTACTS : has
    PATIENTS ||--o{ INSURANCE : owns

    PATIENTS ||--o{ BILLS : receives
    BILLS ||--o{ BILL_ITEMS : contains

🔐 Hospital-Wise Data Isolation

Hospital-specific data is associated with hospital_id.

Hospital 1
 ├── Departments
 ├── Doctors
 ├── Staff
 ├── Patients
 ├── Rooms / Beds
 └── Medicines

Hospital 2
 ├── Departments
 ├── Doctors
 ├── Staff
 ├── Patients
 ├── Rooms / Beds
 └── Medicines

Example APIs:

GET /api/doctor/hospital/{hospitalId}

GET /api/staff/hospital/{hospitalId}

GET /api/room/hospital/{hospitalId}

GET /api/medicine/hospital/{hospitalId}

This design supports hospital-level data separation.

⭐ Core Features

1. 🔎 Hospital Discovery

Patients and doctors can search for hospitals and inspect:

Hospital details

Departments

Doctors

Available beds

Hospital services

Emergency support

Contact information

User
 ↓
Search Hospital
 ↓
Hospital Service
 ↓
Check Doctors
 ↓
Check Beds
 ↓
Check Services
 ↓
Suitable Hospital

2. 👨‍⚕️ Doctor Discovery

Users can find doctors based on:

Hospital

Department

Specialization

Experience

Consultation fee

Availability

Example:

🏥 Hospital A

Cardiology
 ├── Dr. Rahul Kumar
 ├── Dr. Amit Sharma
 └── Dr. Neha Singh

3. 🛏️ Real-Time Bed Management

Beds should support operational states:

AVAILABLE
OCCUPIED
RESERVED
CLEANING
MAINTENANCE

Example:

Room 101

BED-01 → AVAILABLE
BED-02 → OCCUPIED
BED-03 → CLEANING
BED-04 → RESERVED

Bed Lifecycle

Patient Discharge
      ↓
   CLEANING
      ↓
Cleaning Complete
      ↓
  AVAILABLE

This makes bed availability more realistic than simply storing a single
count.

4. 👨‍⚕️ Doctor Availability

Doctors can have availability states:

AVAILABLE
BUSY
ON_LEAVE
OFFLINE
EMERGENCY_DUTY

Example:

Dr. Rahul Kumar
Cardiology

10:00 - 12:00  🟢 Available
12:00 - 01:00  🔴 Busy
04:00 - 06:00  🟢 Available

5. 📅 Smart Appointment Booking

Appointments are based on the doctor's consultation schedule.

flowchart LR

    A["🧑 Patient"] --> B["🏥 Hospital"]
    B --> C["🏢 Department"]
    C --> D["👨‍⚕️ Doctor"]
    D --> E["🕐 Consultation Slots"]
    E --> F["📅 Book Slot"]
    F --> G["✅ Appointment"]

Example:

Doctor Consultation
10:00 AM - 01:00 PM

15-minute slots

10:00 ✅
10:15 ✅
10:30 ❌
10:45 ✅
11:00 ✅

The backend should prevent double booking of the same slot.

6. 🚑 Emergency Mode

A dedicated emergency workflow can combine:

Hospital
+
Doctor Availability
+
Bed Availability
+
Emergency Facility
+
Location

Emergency Flow

flowchart LR

    A["🚑 Emergency"] --> B["📍 Location"]
    B --> C["🏥 Find Hospitals"]

    C --> D["👨‍⚕️ Doctor Availability"]
    C --> E["🛏️ Bed Availability"]
    C --> F["🚨 Emergency Facility"]

    D --> G["✅ Suitable Hospital"]
    E --> G
    F --> G

The goal is to reduce the time required to identify a hospital that can
handle the patient's requirement.

Emergency recommendations are intended as an information and routing
feature, not as a substitute for professional medical judgment or
emergency services.

7. 🏥 Hospital Comparison

Patients can compare hospitals using available operational information.

                 Hospital A   Hospital B   Hospital C

Cardiology           ✅           ✅           ❌
Doctors              5            2            0
Available Beds      12            4           18
Emergency            ✅           ❌            ✅
Lab                  ✅           ✅            ✅

This makes hospital discovery more useful than a simple hospital
directory.

8. 🤖 AI Hospital Recommendation

The planned AI layer can recommend hospitals based on structured
requirements.

Example input:

Problem: Chest pain
Location: Kanpur
Emergency: Yes

The system can use available backend data to surface relevant:

🏥 Hospitals
👨‍⚕️ Departments / Doctors
🛏️ Bed Availability
🚨 Emergency Facilities
📍 Location

The AI should assist with finding relevant services, not diagnose
medical conditions.

9. 🧑‍⚕️ Complete Patient Timeline

A patient can have a complete journey:

flowchart LR

    A["👤 Registration"] -->
    B["📅 Appointment"] -->
    C["👨‍⚕️ Consultation"] -->
    D["💊 Prescription"] -->
    E["🧪 Lab Test"] -->
    F["🛏️ Admission"] -->
    G["💳 Billing"] -->
    H["💰 Payment"]

Example timeline:

01 Oct
10:00 AM → Appointment

01 Oct
10:30 AM → Consultation

01 Oct
11:00 AM → Lab Test

01 Oct
02:00 PM → Prescription

02 Oct
10:00 AM → Discharge

10. 💊 Medicine Inventory

Medicine inventory supports:

Medicine Name
Category
Unit
Unit Price
Stock Quantity
Reorder Level
Status

Stock Alert

Stock > Reorder Level
        ↓
    NORMAL

Stock <= Reorder Level
        ↓
   ⚠️ LOW STOCK

Stock = 0
        ↓
 🔴 OUT OF STOCK

11. 🧪 Lab Test Management

Lab workflow:

flowchart LR

    A["👨‍⚕️ Doctor"] -->
    B["🧪 Test Request"] -->
    C["Sample Collection"] -->
    D["Processing"] -->
    E["Report Generated"] -->
    F["👨‍⚕️ Doctor / 🧑 Patient"]

Possible statuses:

REQUESTED
SAMPLE_COLLECTED
PROCESSING
COMPLETED
CANCELLED

12. 💳 Smart Billing

Billing can combine multiple hospital services:

Consultation
      +
Lab Tests
      +
Medicines
      +
Room Charges
      +
Doctor Charges
      +
Other Services
      ↓
    BILL

Example:

Consultation       ₹500
Lab Test           ₹800
Medicine         ₹1,200
Room             ₹2,000
------------------------
Subtotal         ₹4,500
Discount           ₹200
Tax                ₹300
------------------------
Total            ₹4,600

13. 🔔 Notification Service

A dedicated Notification Service can handle:

Appointment confirmation

Appointment reminder

Appointment cancellation

Doctor availability changes

Bed allocation

Bill generation

Payment confirmation

Architecture:

Appointment Service
        ↓
Notification Service
        ↓
 ┌──────┼──────┐
 ↓      ↓      ↓
Email   SMS   In-App

14. 📝 Audit Log Service

For production environments, important actions can be recorded.

WHO
WHAT
WHEN

Example:

Doctor Rahul
Updated Patient #102
01 Oct 2026 10:32 AM

Another example:

Hospital Admin
Changed BED-102
OCCUPIED → AVAILABLE

Planned service:

AUDIT-SERVICE

15. 📊 Hospital Analytics

Hospital administrators can see:

Total Patients
Today's Appointments
Available Beds
Occupied Beds
Total Doctors
Today's Revenue

Possible analytics:

Patients per Month
Appointments per Month
Revenue
Bed Occupancy
Doctor Utilization
Medicine Consumption

Planned:

ANALYTICS-SERVICE

16. 📱 Patient Portal

Patients can access:

My Profile
My Appointments
My Doctors
My Prescriptions
My Lab Reports
My Medical History
My Bills
My Payments

This creates a complete digital patient experience.

17. 🔐 Role-Based Access Control

Future security architecture can support:

SUPER_ADMIN
HOSPITAL_ADMIN
DOCTOR
STAFF
RECEPTIONIST
PATIENT

Example permissions:

Doctor
 ├── Patients
 ├── Appointments
 ├── Prescriptions
 └── Medical History

Receptionist
 ├── Patients
 ├── Appointments
 └── Billing

Hospital Admin
 ├── Doctors
 ├── Staff
 ├── Rooms
 ├── Beds
 └── Reports

🔄 Microservice Communication

MediCRM uses OpenFeign for internal communication.

Example:

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

Example Feign client:

@FeignClient(name = "HOSPITAL-SERVICE")
public interface HospitalService {

    @GetMapping("/api/hospital/{hospitalId}")
    Object getSingleHospital(
            @PathVariable("hospitalId") Long hospitalId
    );
}

🧭 Eureka Service Discovery

                  🧭 EUREKA SERVER
                         │
          ┌──────────────┼──────────────┐
          ↓              ↓              ↓
   HOSPITAL-SERVICE  DOCTOR-SERVICE  ROOM-SERVICE
          │              │              │
          └──────────────┼──────────────┘
                         ↓
                  Service Discovery

Services register themselves with Eureka and can communicate using
service names instead of fixed host addresses.

🚪 API Gateway

The API Gateway provides a common entry point.

Frontend
   │
   ↓
API Gateway
   │
   ├── /api/hospital/**
   ├── /api/department/**
   ├── /api/doctor/**
   ├── /api/staff/**
   ├── /api/room/**
   ├── /api/patient/**
   ├── /api/appointment/**
   ├── /api/prescription/**
   ├── /api/lab/**
   └── /api/billing/**

⚖️ Load Balancing

When multiple instances of a service are running:

                 API Gateway
                      │
                      ↓
                Load Balancer
                  /       \
                 /         \
                ↓           ↓
        Doctor Service   Doctor Service
          Instance 1       Instance 2

Requests can be distributed across available service instances.

⚡ Redis Caching -- Planned

Frequently accessed data can be cached.

Potential cache candidates:

Hospital List
Hospital Details
Doctor Availability
Department List
Bed Availability
Medicine Information

Flow:

Request
   ↓
Redis Cache?
  /   \
Yes    No
 ↓      ↓
Data   MySQL

Redis should be used carefully for availability-sensitive information so
stale data does not create incorrect operational decisions.

🏗️ Backend Layer Structure

Each microservice follows a layered architecture:

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
│     └── Request / Response
│
├── Exception
│     └── Custom Exceptions
│
├── Microservice
│     └── Feign Clients
│
└── Config
      └── Configuration

📁 Suggested Backend Repository Structure

MediCRM-Backend/
│
├── Eureka-Server/
│
├── API-Gateway/
│
├── Hospital-Service/
│
├── Department-Service/
│
├── Doctor-Service/
│
├── Staff-Service/
│
├── User-Service/
│
├── Room-Service/
│
├── Medicine-Service/
│
├── Patient-Service/
│
├── Appointment-Service/
│
├── Prescription-Service/
│
├── Lab-Service/
│
├── Medical-History-Service/
│
├── Billing-Service/
│
├── Notification-Service/
├── Audit-Service/
├── Analytics-Service/
└── AI-Recommendation-Service/

🔄 Complete MediCRM Flow

flowchart TB

    User["🧑 Patient / Doctor"]

    Search["🔎 Hospital Search"]

    Hospital["🏥 Hospital"]
    Doctor["👨‍⚕️ Doctor"]
    Bed["🛏️ Bed"]
    Appointment["📅 Appointment"]

    Consultation["👨‍⚕️ Consultation"]
    Prescription["💊 Prescription"]
    Lab["🧪 Lab Test"]
    Admission["🛏️ Admission"]
    Billing["💳 Billing"]
    Payment["💰 Payment"]

    User --> Search

    Search --> Hospital
    Hospital --> Doctor
    Hospital --> Bed

    Doctor --> Appointment
    Bed --> Appointment

    Appointment --> Consultation
    Consultation --> Prescription
    Consultation --> Lab
    Consultation --> Admission

    Prescription --> Billing
    Lab --> Billing
    Admission --> Billing

    Billing --> Payment

🚀 Production-Level Roadmap

Phase 1 -- Core Backend

✅ Hospital
✅ Department
✅ Doctor
✅ Staff
✅ Room / Bed
✅ Medicine
✅ Patient
✅ Appointment
✅ Prescription
✅ Billing

Phase 2 -- Operational Features

🔄 Doctor Availability
🔄 Real-Time Bed Status
🔄 Smart Appointment Slots
🔄 Lab Workflow
🔄 Patient Timeline
🔄 Medicine Stock Alerts
🔄 Hospital Comparison

Phase 3 -- Production Features

🔄 Notification Service
🔄 Audit Log Service
🔄 Analytics Service
🔄 Patient Portal
🔄 Role-Based Access
🔄 Redis Caching

Phase 4 -- Next-Level Platform

🤖 AI Hospital Recommendation
🚑 Emergency Mode
📍 Location-Based Hospital Discovery
📊 Advanced Hospital Analytics
📱 Mobile Application
☁️ Cloud Deployment
🐳 Docker / Containerization

🎯 What Makes MediCRM Different?

Traditional Hospital Management Systems mainly focus on internal CRUD
operations.

MediCRM aims to combine:

                 MediCRM
                    │
       ┌────────────┼────────────┐
       ↓            ↓            ↓
 Hospital CRM   Patient Care   Discovery
       │            │            │
       ↓            ↓            ↓
 Doctors        Appointments   Hospitals
 Staff          Prescription   Doctors
 Rooms          Lab Tests      Beds
 Medicines      Billing        Emergency

The key product idea is:

Find the right hospital by combining hospital information, doctor
availability, bed availability and required services, then continue
the patient's journey through appointment, consultation, treatment,
admission and billing.

📌 Key Features Summary

Feature                      Status / Purpose

🏥 Hospital Management       Core
🏢 Department Management     Core
👨‍⚕️ Doctor Management         Core
👨‍💼 Staff Management          Core
🛏️ Room / Bed Management     Core
💊 Medicine Management       Core
🧑 Patient Management        Core
📅 Appointment Management    Core
💉 Prescription Management   Core
🧪 Lab Management            Core
📋 Medical History           Core
💳 Billing                   Core
🔎 Hospital Discovery        Key Feature
👨‍⚕️ Doctor Discovery          Key Feature
🛏️ Bed Availability          Key Feature
🚑 Emergency Mode            Next-Level
🏥 Hospital Comparison       Next-Level
🕐 Smart Appointment Slots   Next-Level
🤖 AI Recommendation         Future
🔔 Notification Service      Future
📝 Audit Service             Future
📊 Analytics Service         Future
⚡ Redis                     Future
📱 Patient Portal            Future
🔐 RBAC / Security           Future
☁️ Cloud Deployment          Future

👨‍💻 Backend Development Philosophy

MediCRM follows these principles:

Modular microservices

Clean layered architecture

REST-first APIs

Hospital-wise data isolation

Service discovery through Eureka

Centralized routing through API Gateway

Load-balanced service instances

OpenFeign for internal service communication

JPA for persistence

MySQL for relational data

Centralized exception handling

Scalable architecture for future AI, analytics and notifications

🏁 Final Architecture

                         🌐 CLIENT
                            │
                            ▼
                     🚪 API GATEWAY
                            │
                            ▼
                     ⚖️ LOAD BALANCER
                            │
          ┌─────────────────┼─────────────────┐
          │                 │                 │
          ▼                 ▼                 ▼
      🏥 Hospital       👨‍⚕️ Doctor        🧑 Patient
       Service           Service           Service
          │                 │                 │
          ├───────┬─────────┼─────────┬───────┤
          ▼       ▼         ▼         ▼       ▼
       🛏️ Room  👨‍💼 Staff  📅 Appointment  💊 Prescription
          │                           │
          ▼                           ▼
       💊 Medicine                 🧪 Lab
                                      │
                                      ▼
                                   💳 Billing

              🧭 EUREKA SERVER
                    │
             Service Discovery

        ⚡ Redis        📝 Audit
           │               │
           └───────┬───────┘
                   ▼
              🐬 MySQL

🚧 Project Status

MediCRM Backend is under active development.

The architecture is being developed incrementally from core
hospital-management services toward a scalable platform containing
hospital discovery, doctor/bed availability, smart appointments,
emergency support, analytics, notifications and AI-assisted
recommendations.

👨‍💻 Project Focus

Backend

Java • Spring Boot • Spring Data JPA • REST API • Microservices • API
Gateway • Eureka Server • Load Balancing • OpenFeign • MySQL

Product Focus

Hospital Management + Hospital Discovery + Doctor Availability + Bed
Availability + Patient Journey + Emergency Support
