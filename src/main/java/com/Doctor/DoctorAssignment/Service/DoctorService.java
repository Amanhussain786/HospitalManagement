package com.Doctor.DoctorAssignment.Service;

import com.Doctor.DoctorAssignment.Enum.DoctorCity;
import com.Doctor.DoctorAssignment.Enum.DoctorSpeciality;
import com.Doctor.DoctorAssignment.Enum.PatientSymptom;
import com.Doctor.DoctorAssignment.Model.Doctor;
import com.Doctor.DoctorAssignment.Model.Patient;
import com.Doctor.DoctorAssignment.Repository.DoctorRepository;
import com.Doctor.DoctorAssignment.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    public String addDoctor(Doctor doctor)
    {
        Doctor d1 = doctorRepository.getDoctorByEmail(doctor.getEmail());
        Doctor d2 = doctorRepository.getDoctorByPhoneNo(doctor.getPhoneNo());

        if (d1 != null || d2 != null) {
            return "Doctor with such Email or Mobile Number already exists!";
        } else {
            doctorRepository.save(doctor);
            return "Doctor Added Successfully";
        }
    }

    //convert city(string)  to enum
    public DoctorCity convertStringToEnum(String str){

        if(str.equals("DELHI"))
            return DoctorCity.DELHI;
        if(str.equals("NOIDA"))
            return DoctorCity.NOIDA;
        if(str.equals("FARIDABAD"))
            return DoctorCity.FARIDABAD;

        return null;
    }

    //function to find doctor speciality from patient symptom
    public DoctorSpeciality getDoctorSpecialityFromPatientSymptom(PatientSymptom patientSymptom){
        if(patientSymptom.equals(PatientSymptom.ARTHRITIS) || patientSymptom.equals(PatientSymptom.BACK_PAIN) ||
                patientSymptom.equals(PatientSymptom.TISSUE_INJURIES))
            return DoctorSpeciality.ORTHOPEDIC;
        else if(patientSymptom.equals(PatientSymptom.DYSMENORRHEA))
            return DoctorSpeciality.GYNOCOLOGY;
        else if(patientSymptom.equals(PatientSymptom.SKIN_INFECTION) || patientSymptom.equals(PatientSymptom.SKIN_BURN))
            return DoctorSpeciality.DERMATOLOGY;
        else if(patientSymptom.equals(PatientSymptom.EAR_PAIN))
            return DoctorSpeciality.ENT_SPETIALIST;

        return null;
    }

    //API to get the List of Doctors with patient ID
    public ResponseEntity<Object> getDoctorsByPatientId(int id)
    {
        Optional<Patient> check = patientRepository.findById(id);

        if(check.isPresent())
        {
            Patient patient1 = check.get();


            DoctorCity patientLocation = convertStringToEnum(patient1.getCity());
            PatientSymptom patientSymptom = patient1.getPatientSymptom();

            List<Doctor> doctors = doctorRepository.findAll();

            List<Doctor> doctors1 = new ArrayList<>();

            for(Doctor d : doctors){
                if(d.getDoctorCity().equals(patientLocation)){
                    doctors1.add(d);
                }
            }

            if(doctors1.isEmpty())
            {
                return new ResponseEntity<>("We are still waiting to expand to your location", HttpStatus.NOT_FOUND);
            }
            else
            {
                DoctorSpeciality doctorSpeciality = getDoctorSpecialityFromPatientSymptom(patientSymptom);

                List<Doctor> doctors2 = new ArrayList<>();

                for(Doctor doctor :  doctors1){
                    if(doctor.getDoctorSpeciality().equals(doctorSpeciality)){
                        doctors2.add(doctor);
                    }
                }

                if(doctors2.isEmpty()){
                    return new ResponseEntity<>("There isn’t any doctor present at your location for your symptom", HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(doctors2, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>("Patient with id = " + id + " not present", HttpStatus.NOT_FOUND);
    }

    //API to delete doctor with ID
    public String deleteDoctorById(int id)
    {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if(doctor.isEmpty())
        {
            return "Doctor of Such ID Does Not Exist !!";
        }
        else
        {
            doctorRepository.deleteById(id);
            return "Doctor Deleted Successfully!";
        }
    }

}
