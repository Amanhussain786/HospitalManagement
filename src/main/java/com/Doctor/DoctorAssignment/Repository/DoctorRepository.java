package com.Doctor.DoctorAssignment.Repository;

import com.Doctor.DoctorAssignment.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

//    @Query(value = "select* from doctor where doctor_city=:doctorCity AND doctor_speciality=:doctorSpeciality",nativeQuery = true)
//    List<Doctor> getSuggestedDoctors(String doctorCity, String doctorSpeciality);

    @Query(value = "select* from doctor where doctor_city=:city",nativeQuery = true)
    List<Doctor> getDoctorsByCity(String city);

    Doctor getDoctorByEmail(String email);

    Doctor getDoctorByPhoneNo(String mobNo);
}
