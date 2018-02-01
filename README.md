# HospitalManager
HospitalManager - java learning app

The app is design to manage hospital (workers and duties).

Requirements:
- there are three types of workers: Administrator, Doctor and Nurse.
- the doctors and nurses can have duties.
- duties are set with the following rules:
    - one worker can have only one duty in day
    - worker cannot have duty day by day
    - worker can have max 10 duties with one month
    - is not allowed to have two doctors with the same specialization in one day
- Administrators can:
    - list all workers,
    - add new workers,
    - add duties
- Doctors and Nurses can:
    - list all workers,
    - list duties
- all data are serialized into json file