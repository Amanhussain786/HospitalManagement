package com.Doctor.DoctorAssignment.Service;

import com.Doctor.DoctorAssignment.Model.Patient;
import com.Doctor.DoctorAssignment.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public String addPatient(Patient patient)
    {
        Patient p1 = patientRepository.getPatientByEmail(patient.getEmail());
        Patient p2 = patientRepository.getPatientByPhoneNo(patient.getPhoneNo());

        if (p1 != null || p2 != null) {
            return "Patient with such Email or Mobile Number already exists!";
        } else {
            patientRepository.save(patient);
            return "Patient Added Successfully";
        }

    }

    public String deletePatientById(int id)
    {
        Optional<Patient> patient = patientRepository.findById(id);

        if(patient.isEmpty())
        {
            return "Patient of Such ID Does Not Exist!";
        }
        else
        {
            patientRepository.deleteById(id);
            return "Patient Deleted Successfully!";
        }
    }
}
