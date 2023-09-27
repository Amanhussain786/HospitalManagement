package com.Doctor.DoctorAssignment.Repository;

import com.Doctor.DoctorAssignment.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Patient getPatientByEmail(String email);
    Patient getPatientByPhoneNo(String phoneNo);
}
