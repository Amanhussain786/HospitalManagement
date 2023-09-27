package com.Doctor.DoctorAssignment.Model;

import com.Doctor.DoctorAssignment.Enum.DoctorCity;
import com.Doctor.DoctorAssignment.Enum.DoctorSpeciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Size(min = 3)
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    DoctorCity doctorCity;

    @Email
    @Column(unique = true)
    private String email;

    @Size(min = 10)
    @Column(unique = true)
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    DoctorSpeciality doctorSpeciality;
}
