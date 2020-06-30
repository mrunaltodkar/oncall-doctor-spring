package com.couponsdeal.doctor.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Doctor {
	private String email;
	private String password;
	private String doctorName;
	private String qualification;
	private String speciality;
	private String experience;
	private int fees;
	private String location;
	
	public Doctor() {
		super();
	}

	public Doctor(String email, String password, String doctorName, String qualification, String speciality,
			String experience, int fees, String location) {
		super();
		this.email = email;
		this.password = password;
		this.doctorName = doctorName;
		this.qualification = qualification;
		this.speciality = speciality;
		this.experience = experience;
		this.fees = fees;
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}
	
	
}
