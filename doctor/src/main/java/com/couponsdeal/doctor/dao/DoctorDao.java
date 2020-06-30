package com.couponsdeal.doctor.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.couponsdeal.doctor.entity.Doctor;

@Repository
public interface DoctorDao extends MongoRepository<Doctor, String> {

	Doctor findByEmail(String email);
	void deleteByEmail(String email);

}
