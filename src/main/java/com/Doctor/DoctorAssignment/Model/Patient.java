package com.Doctor.DoctorAssignment.Model;

import com.Doctor.DoctorAssignment.Enum.PatientSymptom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Size(min = 3)
    private String name;

    @Column(nullable = false)
    @Size(max = 20)
    private String city;

    @Email
    @Column(unique = true)
    private String email;

    @Size(min = 10)
    @Column(unique = true)
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    PatientSymptom patientSymptom;
}
