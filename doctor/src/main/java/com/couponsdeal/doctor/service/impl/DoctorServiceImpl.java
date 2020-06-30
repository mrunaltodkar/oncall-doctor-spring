package com.couponsdeal.doctor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsdeal.doctor.dao.DoctorDao;
import com.couponsdeal.doctor.entity.Doctor;
import com.couponsdeal.doctor.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao doctorDao;

	@Override
	public Doctor addDoctor(Doctor doctor) {
		return doctorDao.save(doctor);
	}

	@Override
	public List<Doctor> findAllDoctors() {
		return doctorDao.findAll();
	}

	@Override
	public Doctor findDoctorByEmail(String email) {
		return doctorDao.findByEmail(email);
	}

	@Override
	public void deleteDoctorByEmail(String email) {
		doctorDao.deleteByEmail(email);
		
	}
	
}
