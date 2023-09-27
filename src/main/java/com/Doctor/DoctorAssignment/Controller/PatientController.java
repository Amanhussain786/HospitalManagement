package com.Doctor.DoctorAssignment.Controller;

import com.Doctor.DoctorAssignment.Model.Patient;
import com.Doctor.DoctorAssignment.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping("/add_patient")
    public String addPatient(@RequestBody Patient patient)
    {
        return patientService.addPatient(patient);
    }

    @DeleteMapping("/delete_patient_by_id")
    public String deletePatientById(@RequestParam("id") int id)
    {
        return patientService.deletePatientById(id);
    }
}
