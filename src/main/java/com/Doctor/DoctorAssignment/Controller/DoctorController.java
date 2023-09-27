package com.Doctor.DoctorAssignment.Controller;

import com.Doctor.DoctorAssignment.Model.Doctor;
import com.Doctor.DoctorAssignment.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/add_doctor")
    public String addDoctor(@RequestBody Doctor doctor)
    {
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/get_doctors_by_patientId")
    public ResponseEntity<Object> getDoctorsByPatientId(@RequestParam("id") int id)
    {
        return doctorService.getDoctorsByPatientId(id);
    }

    @DeleteMapping("/delete_doctor_by_id")
    public String deleteDoctorById(@RequestParam("id") int id)
    {
        return doctorService.deleteDoctorById(id);
    }
}
