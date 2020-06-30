package com.couponsdeal.doctor.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.couponsdeal.doctor.entity.Doctor;

@Service
public interface DoctorService {

	public Doctor addDoctor(Doctor doctor);

	public List<Doctor> findAllDoctors();

	public Doctor findDoctorByEmail(String email);
	
	public void deleteDoctorByEmail(String email);


}
